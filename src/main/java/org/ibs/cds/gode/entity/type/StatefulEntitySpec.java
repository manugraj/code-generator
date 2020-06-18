package org.ibs.cds.gode.entity.type;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
public class StatefulEntitySpec extends RawEntitySpec {
    @OneToOne(cascade = CascadeType.ALL)
    private EntityState state;
}
