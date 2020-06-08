package org.ibs.cds.gode.codegenerator.config;

import lombok.Getter;

public interface CodeGenerationComponent {

    enum ComponentName{
        APP("app"),
        ENTITY("entity");

        private @Getter final String configName;

        ComponentName(String configName) {
            this.configName = configName;
        }
    }

    ComponentName getComponentName();
}
