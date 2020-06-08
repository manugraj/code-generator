package org.ibs.cds.gode.codegenerator.model.deploy;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.codegenerator.bind.ArtifactPackaging;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class LocalDeployment {
    private Long port;
    private List<DatabaseDeployment> databases = new ArrayList<>();
    private ArtifactPackaging packaging;
}
