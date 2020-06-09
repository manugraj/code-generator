package org.ibs.cds.gode.entity.type;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.spec.UsageSpec;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class App extends Specification {
    private @Embedded UsageSpec usage;
    @ManyToMany
    @JoinTable(name = "AppEntities")
    private List<EntitySpec> entities;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "AppFunctions")
    private List<AppFunction> functions;
    @ManyToMany
    @JoinTable(name = "AppDependencies")
    private List<EntitySpec> dependencies;

    public App() {
       this.entities = new ArrayList<>();
       this.functions = new ArrayList<>();
       this.dependencies = new ArrayList<>();
    }
}
