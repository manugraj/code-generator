package org.ibs.cds.gode.codegenerator.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.codegenerator.spec.StateStore;
import org.ibs.cds.gode.codegenerator.spec.OperationLevel;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class EntityState implements Serializable {
    private boolean volatileEntity;
    private boolean stateSync;
    private OperationLevel opsLevel;
    private StateStore stateStorePref;
}
