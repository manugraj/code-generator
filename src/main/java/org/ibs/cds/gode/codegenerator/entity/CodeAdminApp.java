package org.ibs.cds.gode.codegenerator.entity;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.artefact.Buildable;
import org.ibs.cds.gode.codegenerator.config.CodeGenerationComponent;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.entity.type.Specification;

@Data
public class CodeAdminApp extends Specification implements Buildable, CodeGenerationComponent {

    public CodeAdminApp(CodeApp app, BuildModel buildModel) {
        this.setName(app.getName().concat("Monitor"));
        this.setVersion(app.getVersion());
        this.setDescription("Monitor application for ".concat(app.getName()));
        this.buildModel = buildModel;
    }

    private final BuildModel buildModel;

    @Override
    public ComponentName getComponentName() {
        return ComponentName.ADMIN;
    }
}
