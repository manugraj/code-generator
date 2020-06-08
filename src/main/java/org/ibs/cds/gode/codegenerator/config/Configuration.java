package org.ibs.cds.gode.codegenerator.config;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Configuration {
    public String type;
    public String vmLocation;
    public Boolean binding;
}
