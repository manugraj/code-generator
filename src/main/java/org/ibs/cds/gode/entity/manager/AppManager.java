package org.ibs.cds.gode.entity.manager;

import org.ibs.cds.gode.entity.repo.AppRepository;
import org.ibs.cds.gode.entity.type.App;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public <T> List<T> findTransform(Function<App,T> transformer){
        List<App> all = repo.findAll();
        return all == null ? Collections.emptyList() : all.stream().map(transformer).collect(Collectors.toList());
    }
}
