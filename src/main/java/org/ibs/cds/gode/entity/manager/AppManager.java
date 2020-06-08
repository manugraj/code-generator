package org.ibs.cds.gode.entity.manager;

import org.ibs.cds.gode.entity.repo.AppRepository;
import org.ibs.cds.gode.entity.type.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppManager extends StoredStateEntityManager<App,App, Long, AppRepository> {

    @Autowired
    public AppManager(AppRepository repo) {
        super(repo);
    }

    @Override
    public App transformEntity(App entity) {
        return entity;
    }

    @Override
    public App transformView(App entity) {
        return entity;
    }

    public App find(String name,Long version){
        return this.repo.findByNameAndVersion(name, version);
    }
}
