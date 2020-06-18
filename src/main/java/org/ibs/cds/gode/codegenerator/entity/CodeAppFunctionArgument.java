package org.ibs.cds.gode.codegenerator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeAppFunctionArgument {
    private String name;
    private List<CodeAppFunctionArgumentBody> body;
}
