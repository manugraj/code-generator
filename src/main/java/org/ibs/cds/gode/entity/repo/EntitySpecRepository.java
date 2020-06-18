package org.ibs.cds.gode.entity.repo;

import org.ibs.cds.gode.entity.store.repo.JPAEntityRepository;
import org.ibs.cds.gode.entity.type.StatefulEntitySpec;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntitySpecRepository extends JPAEntityRepository<StatefulEntitySpec, Long, EntitySpecRepo> {

    public EntitySpecRepository(EntitySpecRepo repo) {
        super(repo);
    }

    public List<StatefulEntitySpec> findAll(){
        return repo.findAll();
    }
}
