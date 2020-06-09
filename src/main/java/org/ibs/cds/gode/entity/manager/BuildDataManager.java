package org.ibs.cds.gode.entity.manager;

import org.ibs.cds.gode.entity.repo.BuildDataRepository;
import org.ibs.cds.gode.entity.type.BuildData;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildDataManager extends StoredStateEntityManager<BuildData, BuildData, Long, BuildDataRepository> {

    @Autowired
    public BuildDataManager(BuildDataRepository repo) {
        super(repo);
    }

    @Override
    public BuildData transformEntity(BuildData entity) {
        return entity;
    }

    @Override
    public BuildData transformView(BuildData entity) {
        return entity;
    }

    public BuildData findLatestBuild(String appName, Long appVersion){
        return repo.findLatestBuild(appName, appVersion);
    }

    public PagedData<BuildData> findBuild(String appName, Long appVersion, PageContext context){
        return repo.findBuild(appName, appVersion, context);
    }

    @Override
    public BuildData doSave(BuildData entity) {
        BuildData data = this.findLatestBuild(entity.getApp().getName(), entity.getApp().getVersion());
        if (data != null) {
            data.setLatest(false);
            this.repo.save(data);
        }
        entity.setLatest(true);
        return super.doSave(entity);
    }
}
