package org.ibs.cds.gode.codegenerator.spec;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class IdentifiedObject implements Serializable {
    private String name;
    private String description;
}
