package org.ibs.cds.gode.codegenerator.entity;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.artefact.Buildable;
import org.ibs.cds.gode.codegenerator.config.CodeGenerationComponent;
import org.ibs.cds.gode.entity.type.App;
import org.ibs.cds.gode.entity.type.AppFunction;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.entity.type.Specification;
import org.ibs.cds.gode.codegenerator.spec.UsageSpec;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class CodeApp extends Specification implements Buildable, CodeGenerationComponent {
    private Set<CodeEntity> entities;
    private Set<AppFunction> appFunctions;
    private UsageSpec usage;
    private App model;
    private BuildModel buildModel;

    public CodeApp(App model, BuildModel buildModel) {
        this.model = model;
        this.entities = model.getEntities().stream().map(entity -> new CodeEntity(entity, buildModel)).collect(Collectors.toSet());
        this.appFunctions = new HashSet<>(model.getFunctions());
        this.usage = model.getUsage();
        this.buildModel = buildModel;
    }

    @Override
    public ComponentName getComponentName() {
        return ComponentName.APP;
    }
}
