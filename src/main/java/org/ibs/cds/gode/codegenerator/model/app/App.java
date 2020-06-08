package org.ibs.cds.gode.codegenerator.model.app;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.model.entity.Entity;
import org.ibs.cds.gode.codegenerator.spec.Specification;
import org.ibs.cds.gode.codegenerator.spec.UsageSpec;

import java.util.HashSet;
import java.util.Set;

@Data
public class App extends Specification {
    private UsageSpec usage;
    private Set<Entity> entities;
    private Set<AppFunction> functions;

    public App() {
       this.entities = new HashSet<>();
       this.functions = new HashSet<>();
    }
}
