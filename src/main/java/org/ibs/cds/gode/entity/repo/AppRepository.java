package org.ibs.cds.gode.entity.repo;

import org.ibs.cds.gode.entity.store.repo.JPAEntityRepository;
import org.ibs.cds.gode.entity.type.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppRepository extends JPAEntityRepository<App, Long, AppRepo> {

    @Autowired
    public AppRepository(AppRepo appRepo) {
        super(appRepo);
    }

    public App findByNameAndVersion(String name, Long version) {
        return repo.findByNameAndVersion(name, version);
    }
}
