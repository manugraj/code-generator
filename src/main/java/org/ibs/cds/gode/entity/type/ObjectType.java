package org.ibs.cds.gode.entity.type;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ObjectType extends ManagedIdentifiedObject {
    @OneToMany(cascade = CascadeType.ALL)
    private List<Field> fields;
    public ObjectType() {
        this.fields = new ArrayList<>();
    }
}
