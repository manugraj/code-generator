package org.ibs.cds.gode.entity.manager;

import org.ibs.cds.gode.entity.repo.EntitySpecRepository;
import org.ibs.cds.gode.entity.type.EntitySpec;
import org.springframework.stereotype.Service;

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
}
