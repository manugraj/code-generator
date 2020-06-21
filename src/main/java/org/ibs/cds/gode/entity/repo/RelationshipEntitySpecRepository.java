package org.ibs.cds.gode.entity.repo;

import org.ibs.cds.gode.entity.store.repo.JPAEntityRepository;
import org.ibs.cds.gode.entity.type.RelationshipEntitySpec;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipEntitySpecRepository extends JPAEntityRepository<RelationshipEntitySpec, Long, RelationshipEntitySpecRepo> {

    public RelationshipEntitySpecRepository(RelationshipEntitySpecRepo repo) {
        super(repo);
    }

    public List<RelationshipEntitySpec> findAll(){
        return repo.findAll();
    }
}
