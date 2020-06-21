package org.ibs.cds.gode.codegenerator.api;

import lombok.Data;

@Data
public class RelationshipBrief implements Brief{

    private Long artifactId;
    private String relationship;
    private String from;
    private String roleFrom;
    private String roleTo;
    private String to;
    private Long version;


    public String label(){
       return relationship != null && version != null ?
        this.relationship.concat(" version.").concat(version.toString()):
        null;
    }
}
