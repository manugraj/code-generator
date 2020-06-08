package org.ibs.cds.gode.codegenerator.config;

public interface CodeGenerator {

    BuildConfiguration getConfiguration();
    <T extends CodeGenerationComponent> boolean addBuildableComponent(T component);
    boolean addInput(String name, Object input);
    boolean run(String... args);
}
