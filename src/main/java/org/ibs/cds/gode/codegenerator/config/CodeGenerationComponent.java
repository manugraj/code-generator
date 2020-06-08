package org.ibs.cds.gode.codegenerator.config;

public interface CodeGenerationComponent {

    enum ComponentName{
        APP,
        ENTITY,
        ADMIN;
    }

    ComponentName getComponentName();
}
