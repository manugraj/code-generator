package org.ibs.cds.gode.codegenerator.model.deploy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.entity.type.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JpaStoreProperty {
    private String propertyName;
    private String property;
    private FieldType type;
}
