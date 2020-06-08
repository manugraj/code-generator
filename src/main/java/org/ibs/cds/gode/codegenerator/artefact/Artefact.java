package org.ibs.cds.gode.codegenerator.artefact;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Artefact {
    private final String type;
    private final String packageName;

    public String getFQN(){
        return packageName.concat(".").concat(type);
    }
}
