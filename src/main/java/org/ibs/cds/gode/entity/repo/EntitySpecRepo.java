package org.ibs.cds.gode.entity.repo;

import org.ibs.cds.gode.entity.store.MarkJPARepo;
import org.ibs.cds.gode.entity.store.repo.JPAEntityRepo;
import org.ibs.cds.gode.entity.type.StatefulEntitySpec;
import org.springframework.stereotype.Repository;

@Repository
@MarkJPARepo
public interface EntitySpecRepo extends JPAEntityRepo<StatefulEntitySpec, Long> {
}
