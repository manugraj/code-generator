package org.ibs.cds.gode.entity.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.codegenerator.spec.StoreName;
import org.ibs.cds.gode.entity.store.IStoreType;
import org.ibs.cds.gode.entity.store.StoreEntity;
import org.ibs.cds.gode.entity.store.StoreType;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class EntityStateStore extends StoreEntity<Long> {
    private @Id @GeneratedValue Long ssId;
    private @Enumerated(EnumType.STRING)
    StoreName storeName;
    private boolean cached;
    private boolean asyncStoreSync;

    @Override @JsonIgnore
    public StoreType getStoreType() {
        return StoreType.JPA;
    }
}
