package org.ibs.cds.gode.codegenerator.entity;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.artefact.Buildable;
import org.ibs.cds.gode.codegenerator.config.CodeGenerationComponent;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.entity.type.Specification;

import java.io.File;

@Data
public class CodeAdminApp extends Specification implements Buildable, CodeGenerationComponent {

    public CodeAdminApp(CodeApp app, BuildModel buildModel) {
        super();
        setName(app.getName().concat("-Monitor"));
        setVersion(app.getVersion());
        setDescription("Monitor application for ".concat(app.getName()));
        this.buildModel = buildModel;
        this.appName = app.getName();
    }

    private final BuildModel buildModel;
    private final String appName;

    @Override
    public ComponentName getComponentName() {
        return ComponentName.ADMIN;
    }
}
