package org.ibs.cds.gode.entity.type;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class AppFunction extends ManagedEntity implements Serializable {
    private String methodName;
    @ManyToMany
    @JoinTable(name = "FunctionInput")
    private List<EntitySpec> input;

    @ManyToMany
    @JoinTable(name = "FunctionOutput")
    private List<EntitySpec> output;

    public AppFunction() {
        this.input = new ArrayList<>();
        this.output = new ArrayList<>();
    }

}
