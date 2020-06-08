package org.ibs.cds.gode.codegenerator.config;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComponentConfiguration {
    public CodeGenerationComponent.ComponentName type;
    public String vmLocation;
}
