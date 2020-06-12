package org.ibs.cds.gode.codegenerator.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface Brief {

    @JsonProperty(value="label")
    public String label();
}
