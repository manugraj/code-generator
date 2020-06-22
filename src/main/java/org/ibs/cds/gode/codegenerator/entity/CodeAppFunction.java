package org.ibs.cds.gode.codegenerator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.ibs.cds.gode.entity.type.AppFunction;
import org.ibs.cds.gode.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeAppFunction {

    private AppFunction function;
    private CodeAppFunctionArgument input;
    private CodeAppFunctionArgument output;
    private String name;
    private String methodName;

    public CodeAppFunction(AppFunction function) {
        this.function = function;
        String methodName = function.getMethodName();
        if(methodName != null){
           this.name=StringUtils.capitalize(methodName);
           this.methodName = methodName;
            processInputOutput();
        }
    }

    private void processInputOutput() {
        List<CodeAppFunctionArgumentBody> inputList = CollectionUtils.isEmpty(this.function.getInput()) ? List.of() : this.function.getInput().stream().map(k -> new CodeAppFunctionArgumentBody(k.getArgumentName(),
                k.getEntity().getName().concat("View"),
                PathPackage.DEFAULT__ENTITY_VIEW_DIRECTORY.getValue())).collect(Collectors.toList());
        List<CodeAppFunctionArgumentBody> outputList = CollectionUtils.isEmpty(this.function.getOutput()) ? List.of() : this.function.getOutput().stream().map(k -> new CodeAppFunctionArgumentBody(k.getArgumentName(),
                k.getEntity().getName().concat("View"),
                PathPackage.DEFAULT__ENTITY_VIEW_DIRECTORY.getValue())).collect(Collectors.toList());
        this.input = new CodeAppFunctionArgument(this.name.concat("Request"), inputList);
        this.output = new CodeAppFunctionArgument(this.getName().concat("Response"), outputList);
    }
}
