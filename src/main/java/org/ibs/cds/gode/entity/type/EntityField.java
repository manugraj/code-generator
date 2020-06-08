package org.ibs.cds.gode.entity.type;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class EntityField extends Field {

    @ElementCollection
    private Set<FieldProperty> properties;
    private Specification relationship;
    @OneToOne(cascade = CascadeType.ALL)
    private ObjectType objectType;

    public EntityField() {
        this.properties = new HashSet<>();
    }
}
