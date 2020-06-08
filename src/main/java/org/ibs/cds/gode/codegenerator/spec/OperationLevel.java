package org.ibs.cds.gode.codegenerator.spec;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OperationLevel {
    private Level read;
    private Level write;
    private Level relativeRead;
    private Boolean transactional;
    private Boolean asyncRead;

    public String digest(){
        return read.toString().substring(0,1) + write.toString().substring(0,1) + relativeRead.toString().substring(0,1) + transactional.toString().substring(0,1);
    }
}
