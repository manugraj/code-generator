package org.ibs.cds.gode.codegenerator.spec;

import lombok.Getter;

public enum Level {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    @Getter private final int value;

    Level(int value){
        this.value = value;
    }
}
