package org.ibs.cds.gode.codegenerator.model.entity.field;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.codegenerator.spec.IdentifiedObject;

@Data
@NoArgsConstructor
public class Field extends IdentifiedObject {
    private FieldType type;
}
