package org.ibs.cds.gode.codegenerator.entity;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.artefact.Buildable;
import org.ibs.cds.gode.codegenerator.config.CodeGenerationComponent;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.codegenerator.spec.StoreName;
import org.ibs.cds.gode.entity.type.RelationshipStorePolicy;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CodeEntityRelationship implements Buildable, CodeGenerationComponent {

    private StoreName storeName;
    private List<CodeField> fields;
    private CodeEntityRelationshipNode startNode;
    private CodeEntityRelationshipNode endNode;
    private List<CodeField> viewFields;
    private String viewName;
    private String name;

    public CodeEntityRelationship(RelationshipStorePolicy storePolicy, BuildModel buildModel) {
        StoreName relationshipStore = storePolicy.getStoreName();
        this.storeName = relationshipStore == null ? StoreName.NEO4J : relationshipStore;
        this.fields = storePolicy.getStateSchema().getFields().stream().map(k->new CodeField(k, buildModel)).collect(Collectors.toList());
        this.viewFields = storePolicy.getViewSchema().getFields().stream().map(k->new CodeField(k, buildModel)).collect(Collectors.toList());
        this.fields.addAll(this.viewFields);
        this.name = storePolicy.getStateSchema().getName();
        String name = storePolicy.getViewSchema().getName();
        this.viewName = name == null ? this.name.concat("View") : name;
    }

    @Override
    public ComponentName getComponentName() {
        return ComponentName.RELATIONSHIP;
    }
}
