package org.ibs.cds.gode.codegenerator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;
import org.ibs.cds.gode.codegenerator.entity.CodeApp;
import org.ibs.cds.gode.codegenerator.exception.CodeGenerationFailure;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Data
public class CodeGenerationConfig {

    private String outputPath;

    public BuildConfiguration getBuildConfiguration(CodeApp app) {
        return getBuildConfiguration(app.getBuildModel());
    }

    private BuildConfiguration getBuildConfiguration(BuildModel model) {
        try {
            Pair<Class<? extends BuildConfiguration>, String> pathAndClass = BuildConfiguration.getBuildConfiguration(model);
            ObjectMapper ymlMapper = new ObjectMapper(new YAMLFactory());
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            return ymlMapper.readValue(resolver.getResource(pathAndClass.getRight()).getURL(), pathAndClass.getKey());
        } catch (Exception e) {
            throw CodeGenerationFailure.SYSTEM_ERROR.provide(e);
        }

    }
}
