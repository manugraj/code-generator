package org.ibs.cds.gode.entity.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.codegenerator.spec.Level;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OperationLevel implements Serializable {
    private Level read;
    private Level write;
    private Level relativeRead;
    private Boolean transactional;
    private Boolean asyncRead;

    @JsonIgnore
    public String digest(){
        return read.toString().substring(0,1) + write.toString().substring(0,1) + relativeRead.toString().substring(0,1) + transactional.toString().substring(0,1);
    }
}
