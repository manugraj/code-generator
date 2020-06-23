package org.ibs.cds.gode.codegenerator.model.build;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.status.BinaryStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JavaMavenBuildComplete implements BuildComplete{
    private BinaryStatus status;
    private String editUrl;
}
