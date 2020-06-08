package org.ibs.cds.gode.codegenerator.config;

import org.apache.commons.lang3.tuple.Pair;
import org.ibs.cds.gode.codegenerator.bind.ArtifactPackaging;
import org.ibs.cds.gode.codegenerator.exception.CodeGenerationFailure;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.codegenerator.spec.ProgLanguage;

import java.io.File;
import java.util.Set;
import java.util.StringJoiner;

public interface BuildComponentConfiguration {
    String CONFIG_PATH = "config";
    String TEMPLATE_PATH = "template";
    String CONFIG_FILE = "configuration.yml";
    Set<ComponentConfiguration> getConfiguration();
    ProgLanguage getLanguage();
    ArtifactPackaging getPackingSystem();

    static Pair<Class<? extends BuildComponentConfiguration>,String> getConfiguration(BuildModel model){
        ProgLanguage language = model.getProgLanguage();
        ArtifactPackaging packaging = model.getArtifactPackaging();
        StringJoiner path = new StringJoiner(File.separator);
        if(language == ProgLanguage.JAVA && packaging == ArtifactPackaging.MAVEN){
            return Pair.of(JavaMavenBuildComponentConfiguration.class,path.add(CONFIG_PATH).add(language.toString()).add(packaging.toString()).add(CONFIG_FILE).toString());
        }
        throw CodeGenerationFailure.LANGUAGE_NOT_FOUND.provide(language.toString().concat("/").concat(packaging.toString()));
    }

    default String getComponentConfigFile(){
        StringJoiner path = new StringJoiner(File.separator);
        return path.add(CONFIG_PATH).add(getLanguage().toString()).add(getPackingSystem().toString()).add(CONFIG_FILE).toString();
    }

    default String getComponentTemplatePath(){
        StringJoiner path = new StringJoiner(File.separator);
        return path.add(TEMPLATE_PATH).add(getLanguage().toString()).add(getPackingSystem().toString()).toString();
    }
}
