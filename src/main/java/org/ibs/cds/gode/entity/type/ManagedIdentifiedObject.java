package org.ibs.cds.gode.entity.type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import java.util.UUID;

public class ManagedIdentifiedObject extends IdentifiedObject {
    public ManagedIdentifiedObject() {
        this.artifactId = UUID.randomUUID().getLeastSignificantBits();
    }

    @JsonIgnore
    public Long getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Long artifactId) {
        this.artifactId = artifactId;
    }

    @Id
    private Long artifactId;
}
