package org.ibs.cds.gode.entity.type;

import lombok.Data;
import org.ibs.cds.gode.entity.relationship.RelationshipType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class RelationshipEntitySpec extends Specification {
    private RelationshipType type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="RStartNode")
    private RelationshipNode startNode;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="REndNode")
    private RelationshipNode endNode;
    @OneToMany(cascade = CascadeType.ALL)
    private List<EntityField> fields;

    public RelationshipEntitySpec() {
        this.fields = new ArrayList<>();
    }
}
