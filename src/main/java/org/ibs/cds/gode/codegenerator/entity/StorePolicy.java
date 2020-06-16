package org.ibs.cds.gode.codegenerator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    public boolean hasStore(){
        return storeName != null;
    }
}
