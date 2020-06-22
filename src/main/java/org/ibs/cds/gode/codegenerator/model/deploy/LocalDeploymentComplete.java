package org.ibs.cds.gode.codegenerator.model.deploy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.status.BinaryStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalDeploymentComplete implements DeploymentComplete{
    private String appDocsUrl;
    private String monitorUrl;
    private BinaryStatus status;
}
