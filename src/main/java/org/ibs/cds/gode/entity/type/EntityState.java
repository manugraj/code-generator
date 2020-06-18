package org.ibs.cds.gode.entity.type;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class EntityState extends ManagedEntity {
    private boolean volatileEntity;
    @Embedded
    private OperationLevel opsLevel;
    @OneToOne(cascade = {CascadeType.ALL})
    private EntityStateStore entityStateStore;

}
