package org.ibs.cds.gode.entity.type;

import lombok.Data;
import org.ibs.cds.gode.entity.store.StoreEntity;
import org.ibs.cds.gode.entity.store.StoreType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class AppFunction extends StoreEntity<Long> implements Serializable {
    private @Id @GeneratedValue Long fid;
    private String methodName;

    @OneToMany
    private Set<EntitySpec> input;
    @OneToMany
    private Set<EntitySpec> output;
    @OneToMany
    private Set<EntitySpec> dependencies;

    public AppFunction() {
        this.input = new HashSet<>();
        this.output = new HashSet<>();
        this.dependencies = new HashSet<>();
    }

    @Override
    public StoreType getStoreType() {
        return StoreType.JPA;
    }
}
