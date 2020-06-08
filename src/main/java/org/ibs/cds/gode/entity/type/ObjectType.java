package org.ibs.cds.gode.entity.type;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class ObjectType extends IdentifiedObject {
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Field> fields;
    public ObjectType() {
        this.fields = new HashSet<>();
    }
}
