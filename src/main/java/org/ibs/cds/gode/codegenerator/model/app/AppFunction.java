package org.ibs.cds.gode.codegenerator.model.app;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.spec.Specification;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class AppFunction implements Serializable {
    private String methodName;
    private Set<Specification> input;
    private Set<Specification> output;
    private Set<Specification> dependencies;

    public AppFunction() {
        this.input = new HashSet<>();
        this.output = new HashSet<>();
        this.dependencies = new HashSet<>();
    }
}
