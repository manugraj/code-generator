package org.ibs.cds.gode.codegenerator.spec;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UsageSpec implements Serializable {
    private Level availability;
    private boolean distributes;
    private Level scalability;
}
