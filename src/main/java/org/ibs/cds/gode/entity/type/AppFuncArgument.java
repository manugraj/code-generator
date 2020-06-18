package org.ibs.cds.gode.entity.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppFuncArgument extends ManagedEntity{
    @OneToOne
    private EntitySpec entity;
    private String argumentName;
}
