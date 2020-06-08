package org.ibs.cds.gode.codegenerator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.codegenerator.spec.StoreName;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorePolicy {
    private StoreName storeName;
    private boolean cached;
    private boolean asyncStoreSync;
    private boolean volatileEntity;

    public static StorePolicy noPolicy(){
        return new StorePolicy(null, false, false, true);
    }
}
