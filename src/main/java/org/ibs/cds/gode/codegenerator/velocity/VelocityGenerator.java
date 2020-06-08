package org.ibs.cds.gode.codegenerator.velocity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.VelocityContext;
import org.ibs.cds.gode.codegenerator.config.BuildConfiguration;
import org.ibs.cds.gode.codegenerator.config.CodeGenerationComponent;
import org.ibs.cds.gode.codegenerator.config.CodeGenerator;

@Data
@RequiredArgsConstructor
public class VelocityGenerator implements CodeGenerator {

    private final BuildConfiguration buildConfiguration;
    private VelocityContext context;

    @Override
    public BuildConfiguration getConfiguration() {
        return null;
    }

    @Override
    public <T extends CodeGenerationComponent> boolean addBuildableComponent(T component) {
        context.put(component.getComponentName().toString(), component);
        return false;
    }

    @Override
    public boolean addInput(String name, Object input) {
        return false;
    }

    @Override
    public boolean run(String... args) {
        return false;
    }
}
