package org.ibs.cds.gode.codegenerator.entity;

import lombok.Data;

@Data
public class CodeEntityRelationshipNode {
    private String role;
    private String entityName;
    private String entityViewName;
    private CodeField idField;
}
