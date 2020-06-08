package org.ibs.cds.gode.codegenerator.config;

import org.ibs.cds.gode.entity.type.Specification;

public interface CodeGeneratorEngine<T extends Specification & CodeGenerationComponent> {

    EngineConfiguration getConfiguration();

    boolean addToContext(String name, Object input);

    boolean run(T components);

    default String getProcessPath(T component){
       return getConfiguration().getProcessPath().getAbsolutePath().concat(component.getVersion().toString()).concat(component.getComponentName().toString()).concat(component.getName()).concat(".yml");
    }

}
