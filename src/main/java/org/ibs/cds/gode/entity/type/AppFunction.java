package org.ibs.cds.gode.entity.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.ibs.cds.gode.entity.generic.DataMap;
import org.ibs.cds.gode.entity.store.StoreEntity;
import org.ibs.cds.gode.entity.store.StoreType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class AppFunction extends StoreEntity<Long> implements Serializable {
    private @Id @GeneratedValue Long fid;
    private String methodName;
    @OneToMany
    private List<EntitySpec> input;
    @OneToMany
    private List<EntitySpec> output;

    public AppFunction() {
        this.input = new ArrayList<>();
        this.output = new ArrayList<>();
    }

    @Override @JsonIgnore
    public StoreType getStoreType() {
        return StoreType.JPA;
    }
}
