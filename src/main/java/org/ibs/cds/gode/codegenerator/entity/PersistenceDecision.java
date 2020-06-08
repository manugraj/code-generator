package org.ibs.cds.gode.codegenerator.entity;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.ibs.cds.gode.codegenerator.spec.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class PersistenceDecision {

    public PersistenceDecision(OperationLevel operationLevel) {
        this.operationLevel = operationLevel;
        this.storePolicy = getStorePolicy(operationLevel);
    }

    private OperationLevel operationLevel;
    private StorePolicy storePolicy;

    private StorePolicy getStorePolicy(OperationLevel operationLevel){
        StoreName.CASSANDRA.digest();
        Map<Boolean,List<StoreName>> data = Arrays.stream(StoreName.values())
                .sorted((first,second) -> first.getWriteLevel().getValue())
                .collect(Collectors.partitioningBy(s->operationLevel.digest().equals(s.digest())));
        List<StoreName> perfectSuggestion = data.get(true);
        if(CollectionUtils.isEmpty(perfectSuggestion)){
            return bestFitStorePolicy(data.get(false), operationLevel);
        }
        return new StorePolicy(perfectSuggestion.get(0), false, false);
    }

    private StorePolicy bestFitStorePolicy(List<StoreName> storeNames, OperationLevel operationLevel) {
        Level readLevel = operationLevel.getRead();
        Level writeLevel = operationLevel.getWrite();
        Level relativeRead = operationLevel.getRelativeRead();
        boolean transactional = operationLevel.getTransactional();
        boolean cacheToStoreAsyncWrite = Objects.requireNonNullElse(operationLevel.getAsyncRead(), false);

        StoreName neo4j = StoreName.NEO4J;
        if(relativeRead.getValue() >= neo4j.getRealtiveRead().getValue()){
            if(readLevel.getValue() > neo4j.getReadLevel().getValue() || writeLevel.getValue() > neo4j.getWriteLevel().getValue()){
                return new StorePolicy(neo4j, true , cacheToStoreAsyncWrite);
            }
            return new StorePolicy(neo4j, false , false);
        }

        List<StoreName> finalCandidates = cacheLessStoreCongurence(storeNames, readLevel, writeLevel, relativeRead, transactional);

        if(CollectionUtils.isNotEmpty(finalCandidates)){
            return new StorePolicy(finalCandidates.get(0), false, false);
        }
        return new StorePolicy(StoreName.POSTGRE_SQL, true, cacheToStoreAsyncWrite);

    }

    @NotNull
    private List<StoreName> cacheLessStoreCongurence(List<StoreName> storeNames, Level readLevel, Level writeLevel, Level relativeRead, boolean transactional) {
        Map<Boolean,List<StoreName>> readWriteCongruenceWithRelativeCheck= storeNames.stream()
                .filter(s->s.getTransactional() == transactional)
                .filter(s -> s.getWriteLevel().getValue() >= writeLevel.getValue() && s.getReadLevel().getValue() >= readLevel.getValue())
                .sorted(Comparator.comparingInt(s->s.getWriteLevel().getValue()))
                .collect(Collectors.partitioningBy(s -> s.getRealtiveRead().getValue() >= relativeRead.getValue()));
        List<StoreName> finalCandidates = readWriteCongruenceWithRelativeCheck.get(true);
        finalCandidates.addAll(readWriteCongruenceWithRelativeCheck.get(false));
        return finalCandidates;
    }
}

