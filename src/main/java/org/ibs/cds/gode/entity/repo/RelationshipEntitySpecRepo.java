package org.ibs.cds.gode.entity.repo;

import org.ibs.cds.gode.entity.store.MarkJPARepo;
import org.ibs.cds.gode.entity.store.repo.JPAEntityRepo;
import org.ibs.cds.gode.entity.type.RelationshipEntitySpec;
import org.springframework.stereotype.Repository;

@Repository
@MarkJPARepo
public interface RelationshipEntitySpecRepo extends JPAEntityRepo<RelationshipEntitySpec, Long> {
}
