package org.ibs.cds.gode.codegenerator.config;

import org.apache.commons.lang3.tuple.Pair;
import org.ibs.cds.gode.codegenerator.bind.ArtifactPackaging;
import org.ibs.cds.gode.codegenerator.exception.CodeGenerationFailure;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.codegenerator.spec.ProgLanguage;

import java.io.File;
import java.util.Set;
import java.util.StringJoiner;

public interface BuildConfiguration {
    String CONFIG_PATH = "config";
    Set<Configuration> getConfiguration();
    ProgLanguage getLanguage();
    ArtifactPackaging getPackingSystem();

    static Pair<Class<? extends BuildConfiguration>,String> getBuildConfiguration(BuildModel model){
        ProgLanguage language = model.getProgLanguage();
        ArtifactPackaging packaging = model.getArtifactPackaging();
        StringJoiner path = new StringJoiner(File.separator);
        if(language == ProgLanguage.JAVA && packaging == ArtifactPackaging.MAVEN){
            return Pair.of(JavaMavenBuildConfiguration.class,path.add(CONFIG_PATH).add(language.toString()).add(packaging.toString()).toString());
        }
        throw CodeGenerationFailure.LANGUAGE_NOT_FOUND.provide(language.toString().concat("/").concat(packaging.toString()));
    }
}
