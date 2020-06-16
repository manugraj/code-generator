package org.ibs.cds.gode.entity.manager;

import org.ibs.cds.gode.entity.cache.repo.CacheableEntityRepo;
import org.ibs.cds.gode.entity.repo.AppRepository;
import org.ibs.cds.gode.entity.repo.RepoType;
import org.ibs.cds.gode.entity.store.repo.StoreEntityRepo;
import org.ibs.cds.gode.entity.type.App;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AppManager extends EntityManager<App,App, Long> {


    @Autowired
    public AppManager(AppRepository storeEntityRepo) {
        super(storeEntityRepo, null);
    }

    public App find(String name, Long version){
        AppRepository repo = repository.get();
        return repo.findByNameAndVersion(name, version);
    }

    public <T> List<T> findTransform(Function<App,T> transformer){
        AppRepository repo = repository.get();
        List<App> all = repo.findAll();
        return all == null ? Collections.emptyList() : all.stream().map(transformer).collect(Collectors.toList());
    }

    @Override
    public Optional<App> transformEntity(Optional<App> app) {
        return app;
    }

    @Override
    public Optional<App> transformView(Optional<App> entity) {
        return entity;
    }
}
