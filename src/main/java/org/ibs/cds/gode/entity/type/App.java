package org.ibs.cds.gode.entity.type;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.spec.UsageSpec;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class App extends Specification {
    private @Embedded UsageSpec usage;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<EntitySpec> entities;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<AppFunction> functions;

    public App() {
       this.entities = new HashSet<>();
       this.functions = new HashSet<>();
    }
}
