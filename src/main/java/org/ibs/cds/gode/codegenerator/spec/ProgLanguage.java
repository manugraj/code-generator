package org.ibs.cds.gode.codegenerator.spec;

public enum ProgLanguage {
    JAVA;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
