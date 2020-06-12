package org.ibs.cds.gode.codegenerator.api;

import lombok.Data;

@Data
public class EntityBrief implements Brief{

    private Long artifactId;
    private String entityName;
    private Long version;


    public String label(){
       return entityName != null && version != null ?
        this.entityName.concat(" version.").concat(version.toString()):
        null;
    }
}
