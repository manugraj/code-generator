package org.ibs.cds.gode.entity.type;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.codegenerator.spec.StoreName;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@Entity
public class RelationshipStorePolicy extends ManagedEntity {
    @OneToOne
    private RelationshipEntitySpec viewSchema;
    private StoreName storeName;
    @OneToOne(cascade = CascadeType.PERSIST)
    private RelationshipEntitySpec stateSchema;
}
