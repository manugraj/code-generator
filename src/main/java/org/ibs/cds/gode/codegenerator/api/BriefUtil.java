package org.ibs.cds.gode.codegenerator.api;

import org.ibs.cds.gode.entity.type.App;
import org.ibs.cds.gode.entity.type.RelationshipEntitySpec;
import org.ibs.cds.gode.entity.type.StatefulEntitySpec;

public class BriefUtil {

    public static AppBrief toBrief(App app){
        AppBrief brief = new AppBrief();
        brief.setAppName(app.getName());
        brief.setArtifactId(app.getArtifactId());
        brief.setVersion(app.getVersion());
        return brief;
    }

    public static EntityBrief toBrief(StatefulEntitySpec entity){
        EntityBrief brief = new EntityBrief();
        brief.setEntityName(entity.getName());
        brief.setArtifactId(entity.getArtifactId());
        brief.setVersion(entity.getVersion());
        return brief;
    }

    public static RelationshipBrief toBrief(RelationshipEntitySpec entity){
        RelationshipBrief brief = new RelationshipBrief();
        brief.setRelationship(entity.getName());
        brief.setArtifactId(entity.getArtifactId());
        brief.setVersion(entity.getVersion());
        brief.setFrom(entity.getStartNode().getEntity().getName());
        brief.setRoleFrom(entity.getStartNode().getRole());
        brief.setTo(entity.getEndNode().getEntity().getName());
        brief.setRoleTo(entity.getEndNode().getRole());
        return brief;
    }
}
