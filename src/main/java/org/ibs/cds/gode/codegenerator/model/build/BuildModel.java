package org.ibs.cds.gode.codegenerator.model.build;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.bind.ArtifactPackaging;
import org.ibs.cds.gode.codegenerator.spec.ProgLanguage;
import org.ibs.cds.gode.entity.type.EntityStorePolicy;
import org.ibs.cds.gode.entity.type.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class BuildModel {
    private ProgLanguage progLanguage;
    private ArtifactPackaging artifactPackaging;
    private List<EntityStorePolicy> entityStorePref;
    private Specification app;
    private boolean secure;
    private boolean systemQueue;

    public BuildModel() {
        this.entityStorePref = new ArrayList<>();
    }
}
