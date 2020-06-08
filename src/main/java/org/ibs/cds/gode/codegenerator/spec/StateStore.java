package org.ibs.cds.gode.codegenerator.spec;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StateStore {
    private StoreName database;
    private boolean cached;
    private boolean asyncStoreSync;
}
