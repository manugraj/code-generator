package org.ibs.cds.gode.codegenerator.entity;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.artefact.Buildable;
import org.ibs.cds.gode.codegenerator.config.CodeGenerationComponent;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.entity.type.RawEntitySpec;
import org.ibs.cds.gode.entity.type.StatefulEntitySpec;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class CodeEntity extends CodeEntityView implements Buildable, CodeGenerationComponent {

    private RawEntitySpec model;
    private CodeEntityStorePolicy storePolicy;
    private CodeEntityView view;

    public CodeEntity(StatefulEntitySpec userProvided, BuildModel buildModel, Map<String, RawEntitySpec> storedEntities) {
        super(userProvided, buildModel);
        storePolicy = new CodeEntityStorePolicy(userProvided, this.getBuildModel());
        this.model =  storedEntities.getOrDefault(userProvided.getName(), userProvided);
        this.setFields(this.model.getFields().stream().map(field -> new CodeEntityField(field, buildModel)).collect(Collectors.toList()));
        this.setIdField(new CodeEntityField(this.model.getIdField(), buildModel));
        this.view = new CodeEntityView(userProvided, buildModel);
    }

    @Override
    public ComponentName getComponentName() {
        return ComponentName.ENTITY;
    }


}
