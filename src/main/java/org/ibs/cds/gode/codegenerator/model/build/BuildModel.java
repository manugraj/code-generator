package org.ibs.cds.gode.codegenerator.model.build;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.bind.ArtifactPackaging;
import org.ibs.cds.gode.codegenerator.spec.ProgLanguage;
import org.ibs.cds.gode.entity.type.EntityState;
import org.ibs.cds.gode.entity.type.EntityStorePolicy;
import org.ibs.cds.gode.entity.type.Specification;

import java.util.HashSet;
import java.util.Set;

@Data
public class BuildModel {
    private ProgLanguage progLanguage;
    private ArtifactPackaging artifactPackaging;
    private Set<EntityStorePolicy> entityStorePref;
    private Specification app;

    public BuildModel() {
        this.entityStorePref = new HashSet<>();
    }
}
