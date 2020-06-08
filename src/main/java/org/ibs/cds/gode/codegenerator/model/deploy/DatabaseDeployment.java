package org.ibs.cds.gode.codegenerator.model.deploy;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.codegenerator.spec.StoreName;

@Data
@NoArgsConstructor
public class DatabaseDeployment {
    private StoreName storeName;
    private String driverName;
    private String username;
    private String databaseUrl;
    private String password;
}
