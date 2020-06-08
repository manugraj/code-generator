package org.ibs.cds.gode.entity.manager;

import org.ibs.cds.gode.entity.repo.AppRepo;
import org.ibs.cds.gode.entity.type.App;
import org.ibs.cds.gode.entity.manager.SStateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppManager extends SStateEntityManager<App,App, Long, AppRepo> {

    @Autowired
    public AppManager(AppRepo repo) {
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
