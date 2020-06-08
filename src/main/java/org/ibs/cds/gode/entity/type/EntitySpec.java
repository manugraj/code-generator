package org.ibs.cds.gode.entity.type;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class EntitySpec extends Specification {
    @OneToMany(cascade = CascadeType.ALL)
    private Set<EntityField> fields;

    @OneToOne(cascade = {CascadeType.ALL})
    private EntityState state;

    public EntitySpec() {
        this.fields = new HashSet<>();
    }
}
