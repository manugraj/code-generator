package org.ibs.cds.gode.entity.type;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class EntityStorePolicy extends ManagedEntity{
    @OneToOne
    private StatefulEntitySpec entity;
    @OneToOne(cascade = CascadeType.ALL)
    private EntityState state;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<EntityField> additionalFields;
}
