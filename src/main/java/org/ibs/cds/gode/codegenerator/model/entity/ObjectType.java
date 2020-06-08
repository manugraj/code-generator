package org.ibs.cds.gode.codegenerator.model.entity;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.model.entity.field.Field;
import org.ibs.cds.gode.codegenerator.spec.IdentifiedObject;

import java.util.HashSet;
import java.util.Set;

@Data
public class ObjectType extends IdentifiedObject {
    private Set<Field> fields;

    public ObjectType() {
        this.fields = new HashSet<>();
    }
}
