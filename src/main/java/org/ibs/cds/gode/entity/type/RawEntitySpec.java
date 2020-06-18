package org.ibs.cds.gode.entity.type;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class RawEntitySpec extends Specification {
    @OneToOne(cascade = CascadeType.ALL)
    private IdField idField;
    @OneToMany(cascade = CascadeType.ALL)
    private List<EntityField> fields;

    public RawEntitySpec() {
        this.fields = new ArrayList<>();
    }
}
