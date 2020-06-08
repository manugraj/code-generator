package org.ibs.cds.gode.entity.repo;

import org.ibs.cds.gode.entity.type.App;
import org.ibs.cds.gode.entity.store.repo.JPAEntityRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepo extends JPAEntityRepo<App, Long> {

    App findByNameAndVersion(String name, Long version);

}
