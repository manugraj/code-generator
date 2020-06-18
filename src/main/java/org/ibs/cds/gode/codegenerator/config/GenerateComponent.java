package org.ibs.cds.gode.codegenerator.config;

import lombok.Data;

@Data
public class GenerateComponent {
    private String name;
    private String path;
    private String template;
    private boolean buildable;
    private String key;
}
