package org.ibs.cds.gode.codegenerator.model.deploy;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.ibs.cds.gode.codegenerator.config.CodeGenerationComponent;
import org.ibs.cds.gode.codegenerator.entity.CodeApp;

import java.util.function.Function;

@Data
@RequiredArgsConstructor
public class Action {
    private final CodeGenerationComponent.ComponentName componentName;
    private final String property;
    private final Function<LocalDeploymentRequirement, Function<CodeApp,String>> valueFunction;

    public String getValue(LocalDeploymentRequirement requirement, CodeApp app){
        return valueFunction == null ? requirement.getValue() : valueFunction.apply(requirement).apply(app);
    }

    public static Action of(CodeGenerationComponent.ComponentName componentName, String property, Function<LocalDeploymentRequirement, Function<CodeApp,String>> valueFunction){
        return new Action(componentName, property, valueFunction);
    }

    public static Action of(CodeGenerationComponent.ComponentName componentName, String property){
        return new Action(componentName, property, null);
    }

}
