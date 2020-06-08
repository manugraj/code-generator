package org.ibs.cds.gode.codegenerator.model.entity;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.model.entity.field.Field;
import org.ibs.cds.gode.codegenerator.model.entity.field.FieldProperty;
import org.ibs.cds.gode.codegenerator.spec.Specification;

import java.util.HashSet;
import java.util.Set;

@Data
public class EntityField extends Field {
    private Set<FieldProperty> properties;
    private Specification relationship;
    private ObjectType objectType;

    public EntityField() {
        this.properties = new HashSet<>();
    }
}
