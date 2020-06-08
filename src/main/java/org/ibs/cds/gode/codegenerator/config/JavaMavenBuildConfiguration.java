package org.ibs.cds.gode.codegenerator.config;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.bind.ArtifactPackaging;
import org.ibs.cds.gode.codegenerator.spec.ProgLanguage;

import java.util.Set;

@Data
public class JavaMavenBuildConfiguration implements BuildConfiguration {
    private Set<Configuration> configuration;

    @Override
    public ProgLanguage getLanguage() {
        return ProgLanguage.JAVA;
    }

    @Override
    public ArtifactPackaging getPackingSystem() {
        return ArtifactPackaging.MAVEN;
    }
}
