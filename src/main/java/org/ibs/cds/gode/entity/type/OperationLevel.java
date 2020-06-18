package org.ibs.cds.gode.entity.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.codegenerator.spec.Level;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OperationLevel implements Serializable {
    @Column(name = "readLevel")
    private Level read;
    @Column(name = "writeLevel" )
    private Level write;
    private Level relativeRead;
    private Boolean transactional;
    private Boolean asyncRead;

    @JsonIgnore
    public String digest(){
        return read.toString().substring(0,1) + write.toString().substring(0,1) + relativeRead.toString().substring(0,1) + transactional.toString().substring(0,1);
    }
}
