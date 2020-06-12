package org.ibs.cds.gode.entity.manager;

import org.ibs.cds.gode.entity.repo.EntitySpecRepository;
import org.ibs.cds.gode.entity.type.App;
import org.ibs.cds.gode.entity.type.EntitySpec;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EntitySpecManager extends StoredStateEntityManager<EntitySpec,EntitySpec, Long, EntitySpecRepository> {


    public EntitySpecManager(EntitySpecRepository repo) {
        super(repo);
    }

    @Override
    public EntitySpec transformEntity(EntitySpec entity) {
        return entity;
    }

    @Override
    public EntitySpec transformView(EntitySpec entity) {
        return entity;
    }

    public <T> List<T> findTransform(Function<EntitySpec,T> transformer){
        List<EntitySpec> all = repo.findAll();
        return all == null ? Collections.emptyList() : all.stream().map(transformer).collect(Collectors.toList());
    }
}
