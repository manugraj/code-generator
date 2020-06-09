package org.ibs.cds.gode.entity.type;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.codegenerator.spec.StoreName;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@Data
@Entity
public class EntityStateStore extends ManagedEntity{
    private @Enumerated(EnumType.STRING)
    StoreName storeName;
    private boolean cached;
    private boolean asyncStoreSync;

}
