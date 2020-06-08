package org.ibs.cds.gode.codegenerator.model.deploy;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.codegenerator.spec.Specification;

@Data
@NoArgsConstructor
public class DeploymentModel {
    private Specification app;
    private LocalDeployment localDeployment;
}
