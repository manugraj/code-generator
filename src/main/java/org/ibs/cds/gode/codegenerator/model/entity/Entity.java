package org.ibs.cds.gode.codegenerator.model.entity;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.spec.Specification;

import java.util.HashSet;
import java.util.Set;

@Data
public class Entity extends Specification {
    private Set<EntityField> fields;
    private EntityState state;

    public Entity() {
        this.fields = new HashSet<>();
    }
}
