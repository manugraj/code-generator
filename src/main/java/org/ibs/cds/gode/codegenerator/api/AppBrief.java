package org.ibs.cds.gode.codegenerator.api;

import lombok.Data;

@Data
public class AppBrief implements Brief {
    private Long artifactId;
    private String appName;
    private Long version;


    public String label(){
        return appName != null && version != null ?
                this.appName.concat(" version.").concat(version.toString()):
                null;
    }
}
