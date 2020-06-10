package org.ibs.cds.gode.codegenerator.model.deploy;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.entity.type.Specification;

import java.util.HashMap;
import java.util.Map;

@Data
public class DeploymentModel {
    private Specification app;
    private DeploymentModelType type;
    private Map<String,String> localDeploymentRequired;

    public DeploymentModel() {
        this.localDeploymentRequired = new HashMap();
    }
}
