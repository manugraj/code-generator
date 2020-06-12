package org.ibs.cds.gode.entity.repo;

import org.ibs.cds.gode.entity.store.repo.JPAEntityRepository;
import org.ibs.cds.gode.entity.type.App;
import org.ibs.cds.gode.entity.type.EntitySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntitySpecRepository extends JPAEntityRepository<EntitySpec, Long, EntitySpecRepo> {

    public EntitySpecRepository(EntitySpecRepo repo) {
        super(repo);
    }

    public List<EntitySpec> findAll(){
        return repo.findAll();
    }
}
