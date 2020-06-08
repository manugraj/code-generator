package org.ibs.cds.gode.entity.type;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.entity.store.StoreEntity;
import org.ibs.cds.gode.entity.store.StoreType;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@NoArgsConstructor
@MappedSuperclass
public class IdentifiedObject  extends StoreEntity<Long> implements Serializable {
    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    @Id @GeneratedValue
    private Long iid;
    private String name;
    private String description;

    @Override
    public StoreType getStoreType() {
        return StoreType.JPA;
    }
}
