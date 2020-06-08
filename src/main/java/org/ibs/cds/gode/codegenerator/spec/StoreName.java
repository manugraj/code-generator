package org.ibs.cds.gode.codegenerator.spec;


import lombok.Getter;
import org.ibs.cds.gode.entity.store.StoreType;


public enum StoreName {
    MONGODB(StoreType.MONGODB, Level.HIGH, Level.MEDIUM, Level.LOW, true),
    MYSQL(StoreType.JPA, Level.LOW, Level.LOW, Level.LOW, true),
    ORACLE_DB(StoreType.JPA, Level.HIGH, Level.MEDIUM, Level.LOW, true),
    DB2(StoreType.JPA, Level.MEDIUM, Level.LOW, Level.LOW, true),
    POSTGRE_SQL(StoreType.JPA, Level.HIGH, Level.MEDIUM, Level.LOW, true),
    MARIA_DB(StoreType.JPA, Level.LOW, Level.LOW, Level.LOW, true),
    CASSANDRA(StoreType.CASSANDRA, Level.HIGH, Level.HIGH, Level.LOW, false),
    NEO4J(StoreType.NEO4J, Level.HIGH, Level.MEDIUM, Level.HIGH, true);

    private final @Getter
    StoreType storeType;
    private final @Getter
    Level readLevel;
    private final @Getter
    Level writeLevel;
    private final @Getter
    Level realtiveRead;
    private final @Getter
    Boolean transactional;
    StoreName(StoreType storeType, Level readLevel, Level writeLevel, Level realtiveRead, Boolean transactional) {
        this.storeType = storeType;
        this.readLevel = readLevel;
        this.writeLevel = writeLevel;
        this.realtiveRead = realtiveRead;
        this.transactional = transactional;
    }

    public String digest() {
        return readLevel.toString().substring(0, 1) + writeLevel.toString().substring(0, 1) + readLevel.toString().substring(0, 1) + transactional.toString().substring(0, 1);
    }

}
