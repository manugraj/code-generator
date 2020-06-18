package org.ibs.cds.gode.codegenerator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeAppFunctionArgumentBody {

    private String argumentName;
    private String type;
    private String typePackage;
}
