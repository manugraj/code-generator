package org.ibs.cds.gode.codegenerator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.entity.type.FieldType;
import org.ibs.cds.gode.entity.type.RawEntitySpec;
import org.ibs.cds.gode.entity.type.Specification;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CodeEntityView extends Specification {

    private CodeEntityField idField;
    private List<CodeEntityField> fields;
    private BuildModel buildModel;
    private RawEntitySpec model;

    public CodeEntityView(RawEntitySpec entity, BuildModel buildModel) {
        this.model = entity;
        this.buildModel = buildModel;
        fields = entity.getFields().stream().map(field -> new CodeEntityField(field, buildModel)).collect(Collectors.toList());
        idField = new CodeEntityField(entity.getIdField(), buildModel);
        this.buildModel = buildModel;
        this.setName(entity.getName());
        this.setDescription(entity.getDescription());
        this.setVersion(entity.getVersion());
    }

    @JsonIgnore
    public List<CodeObjectField> getObjectFields(){
        return this.getFields() == null ? Collections.emptyList() : this.getFields().stream()
                .filter(k->k.getField().getType() == FieldType.OBJECT)
                .map(k->k.getObjectField()).collect(Collectors.toList());
    }

}
