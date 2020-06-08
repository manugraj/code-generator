package org.ibs.cds.gode.codegenerator.bind;

public enum ArtifactPackaging {
    MAVEN;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
