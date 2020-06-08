package org.ibs.cds.gode.codegenerator.entity;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.artefact.Buildable;
import org.ibs.cds.gode.codegenerator.config.CodeGenerationComponent;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.codegenerator.model.entity.Entity;
import org.ibs.cds.gode.codegenerator.spec.Specification;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CodeEntity extends Specification implements Buildable, CodeGenerationComponent {

    private List<CodeEntityField> fields;
    private Entity model;
    private CodeEntityStorePolicy storePolicy;
    private BuildModel buildModel;

    public CodeEntity(Entity entity, BuildModel buildModel) {
        fields = entity.getFields().stream().map(field -> new CodeEntityField(field, buildModel)).collect(Collectors.toList());
        storePolicy = new CodeEntityStorePolicy(entity.getState(), this.buildModel);
        this.setName(entity.getName());
        this.setDescription(entity.getDescription());
        this.setVersion(entity.getVersion());
        this.buildModel = buildModel;
    }

    @Override
    public ComponentName getComponentName() {
        return ComponentName.ENTITY;
    }
}
