package org.ibs.cds.gode.entity.type;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@MappedSuperclass
public class IdentifiedObject  extends MinionEntity {

    private String name;
    private String description;

}
