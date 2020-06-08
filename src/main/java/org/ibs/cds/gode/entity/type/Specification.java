package org.ibs.cds.gode.entity.type;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@MappedSuperclass
public class Specification extends IdentifiedObject {

    private Long version;

}
