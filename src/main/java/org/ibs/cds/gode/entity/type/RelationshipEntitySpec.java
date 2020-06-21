package org.ibs.cds.gode.entity.type;

import lombok.Data;
import org.ibs.cds.gode.entity.relationship.RelationshipType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class RelationshipEntitySpec extends Specification {
    private RelationshipType type;
    @OneToOne(cascade = CascadeType.PERSIST)
    private RelationshipNode startNode;
    @OneToOne(cascade = CascadeType.PERSIST)
    private RelationshipNode endNode;
    @OneToMany(cascade = CascadeType.ALL)
    private List<EntityField> fields;

    public RelationshipEntitySpec() {
        this.fields = new ArrayList<>();
    }
}
