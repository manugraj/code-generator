package org.ibs.cds.gode.codegenerator.model.build;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.codegenerator.bind.ArtifactPackaging;
import org.ibs.cds.gode.codegenerator.spec.EntityStorePolicy;
import org.ibs.cds.gode.codegenerator.spec.ProgLanguage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class BuildModel {
    private ProgLanguage progLanguage;
    private ArtifactPackaging artifactPackaging;
    private Set<EntityStorePolicy> entityStorePref;

    public BuildModel() {
        this.entityStorePref = new HashSet<>();
    }
}
