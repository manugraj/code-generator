package org.ibs.cds.gode.codegenerator.artefact;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;

@RequiredArgsConstructor
@Data
public class Artefact {
    private final String type;
    private final String packageName;

    public String getFQN(){
        if(StringUtils.isBlank(this.packageName)) return type;
        return packageName.concat(".").concat(type);
    }
}
