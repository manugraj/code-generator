package org.ibs.cds.gode.codegenerator.config;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.exception.CodeGenerationFailure;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.codegenerator.spec.YamlReadWriteUtil;
import org.ibs.cds.gode.entity.type.Specification;

import java.io.File;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Data
public class EngineConfiguration {

    private final Map<CodeGenerationComponent.ComponentName, ComponentConfiguration> componentConfiguration;
    private BuildComponentConfiguration buildConfiguration;
    private BuildModel model;

    public EngineConfiguration(BuildModel model) {
        this.model = model;
        this.buildConfiguration = getBuildComponentConfiguration(model);
        this.componentConfiguration = buildConfiguration.getConfiguration().stream()
                                    .collect(Collectors.toUnmodifiableMap(s->s.getType(), s->s));
    }

    private BuildComponentConfiguration getBuildComponentConfiguration(BuildModel model) {
        try {
            return YamlReadWriteUtil.readResource(BuildComponentConfiguration.getComponentConfigFile(model), BuildComponentConfiguration.getConfiguration(model));
        } catch (Exception e) {
            throw CodeGenerationFailure.SYSTEM_ERROR.provide(e);
        }
    }

    public String getComponentConfigFile(){
        return BuildComponentConfiguration.getComponentConfigFile(model);
    }

    public String getTemplatePath(CodeGenerationComponent codeGenerationComponent){
        return BuildComponentConfiguration.getComponentTemplatePath(model, codeGenerationComponent);
    }

    public String getCodeGenPath() {
        return "apps";
    }

    public File getProcessPath() {
        return new File(".process");
    }
}
