package org.ibs.cds.gode.entity.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.entity.store.StoreEntity;
import org.ibs.cds.gode.entity.store.StoreType;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class EntityState extends StoreEntity<Long> implements Serializable {
    private @Id @GeneratedValue Long esId;
    private boolean volatileEntity;
    private boolean stateSync;
    private OperationLevel opsLevel;
    @OneToOne(cascade = {CascadeType.ALL})
    private EntityStateStore entityStateStore;

    @Override @JsonIgnore
    public StoreType getStoreType() {
        return StoreType.JPA;
    }
}
