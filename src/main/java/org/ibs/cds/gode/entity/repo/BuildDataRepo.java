package org.ibs.cds.gode.entity.repo;

import org.ibs.cds.gode.entity.store.MarkJPARepo;
import org.ibs.cds.gode.entity.store.repo.JPAEntityRepo;
import org.ibs.cds.gode.entity.type.BuildData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@MarkJPARepo
public interface BuildDataRepo extends JPAEntityRepo<BuildData, Long> {

    BuildData findByAppNameAndAppVersionAndLatestTrue(String appName,Long appVersion);
    Page<BuildData> findByAppNameAndAppVersion(String appName,Long appVersion, Pageable pageable);
}
