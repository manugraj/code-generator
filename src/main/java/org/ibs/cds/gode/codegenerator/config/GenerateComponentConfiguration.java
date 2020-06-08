package org.ibs.cds.gode.codegenerator.config;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GenerateComponentConfiguration {
    private List<GenerateComponent> configuration;

    public GenerateComponentConfiguration() {
        this.configuration = new ArrayList<>();
    }
}
