package org.ibs.cds.gode.entity.type;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EntityStorePolicy extends Specification {
    private EntityState state;
}
