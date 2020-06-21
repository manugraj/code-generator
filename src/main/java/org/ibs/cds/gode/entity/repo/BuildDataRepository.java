package org.ibs.cds.gode.entity.repo;

import org.ibs.cds.gode.entity.store.repo.JPAEntityRepository;
import org.ibs.cds.gode.entity.type.BuildData;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildDataRepository extends JPAEntityRepository<BuildData, Long, BuildDataRepo> {
    @Autowired
    public BuildDataRepository(BuildDataRepo repo) {
        super(repo);
    }

    public BuildData findLatestBuild(String appName, Long appVersion){
        return repo.findByAppNameAndAppVersionAndLatestTrue(appName, appVersion);
    }

    public PagedData<BuildData> findBuild(String appName, Long appVersion, PageContext context){
        return PageUtils.getData(pc->repo.findByAppNameAndAppVersion(appName, appVersion,pc),context);
    }

    public BuildData findLatestBuild(Long appId){
        return repo.findByAppArtifactIdAndLatestTrue(appId);
    }

    public PagedData<BuildData> findBuild(Long appId, PageContext context){
        return PageUtils.getData(pc->repo.findByAppArtifactId(appId,pc),context);
    }
}
