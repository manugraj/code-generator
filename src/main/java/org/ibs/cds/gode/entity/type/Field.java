package org.ibs.cds.gode.entity.type;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@Entity
public class Field extends IdentifiedObject {
    private FieldType type;
}
