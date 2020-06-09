package org.ibs.cds.gode.entity.type;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@Entity
public class EntityStorePolicy extends ManagedEntity{
    @OneToOne
    private EntitySpec entity;
    @OneToOne(cascade = CascadeType.ALL)
    private EntityState state;
}
