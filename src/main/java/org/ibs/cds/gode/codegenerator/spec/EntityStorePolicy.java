package org.ibs.cds.gode.codegenerator.spec;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.codegenerator.model.entity.EntityState;

@Data
@NoArgsConstructor
public class EntityStorePolicy extends IdentifiedObject {
    private EntityState state;
}
