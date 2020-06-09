package org.ibs.cds.gode.entity.type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public class ManagedEntity extends MinionEntity{

    public ManagedEntity() {
        this.artifactId = UUID.randomUUID().getLeastSignificantBits();
    }

    @JsonIgnore
    public Long getArtifactId() {
        return artifactId;
    }

    @Id
    private Long artifactId;
}
