package org.ibs.cds.gode.codegenerator.api;

import org.ibs.cds.gode.entity.type.App;
import org.ibs.cds.gode.entity.type.EntitySpec;

public class BriefUtil {

    public static AppBrief toBrief(App app){
        AppBrief brief = new AppBrief();
        brief.setAppName(app.getName());
        brief.setArtifactId(app.getArtifactId());
        brief.setVersion(app.getVersion());
        return brief;
    }

    public static EntityBrief toBrief(EntitySpec entity){
        EntityBrief brief = new EntityBrief();
        brief.setEntityName(entity.getName());
        brief.setArtifactId(entity.getArtifactId());
        brief.setVersion(entity.getVersion());
        return brief;
    }
}
