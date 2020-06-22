package org.ibs.cds.gode.entity.type;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.codegenerator.spec.StoreName;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class RelationshipStorePolicy extends ManagedEntity {
    @OneToOne
    private RelationshipEntitySpec relationship;
    private StoreName storeName;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<EntityField> additionalFields;
}
