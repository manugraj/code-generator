package org.ibs.cds.gode.entity.type;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class AppFunction extends ManagedEntity implements Serializable {
    private String methodName;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "FunctionInput")
    private List<AppFuncArgument> input;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "FunctionOutput")
    private List<AppFuncArgument> output;

    public AppFunction() {
        this.input = new ArrayList<>();
        this.output = new ArrayList<>();
    }

}
