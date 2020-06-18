package org.ibs.cds.gode.codegenerator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.ibs.cds.gode.codegenerator.artefact.Buildable;
import org.ibs.cds.gode.codegenerator.config.CodeGenerationComponent;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.codegenerator.spec.UsageSpec;
import org.ibs.cds.gode.entity.type.App;
import org.ibs.cds.gode.entity.type.RawEntitySpec;
import org.ibs.cds.gode.entity.type.Specification;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class CodeApp extends Specification implements Buildable, CodeGenerationComponent {
    private Set<CodeEntity> entities;
    private CodeAppFunctionNode appFunction;
    private UsageSpec usage;
    private App model;
    private BuildModel buildModel;
    private Set<CodeEntity> dependencies;
    private boolean secure;
    private boolean systemQueue;

    public CodeApp(App model, BuildModel buildModel) {
        Map<String, RawEntitySpec> storedEntities = resolveStoredEntities(buildModel);
        this.buildModel = buildModel;
        this.model = model;
        this.entities = model.getEntities().stream().map(entity -> new CodeEntity(entity, buildModel,storedEntities)).collect(Collectors.toSet());
        this.appFunction = new CodeAppFunctionNode(model, buildModel);
        this.usage = model.getUsage();
        this.dependencies = model.getDependencies().stream().map(entity -> new CodeEntity(entity, buildModel,storedEntities)).collect(Collectors.toSet());
        this.setName(model.getName());
        this.setDescription(model.getDescription());
        this.setVersion(model.getVersion());
        this.secure = buildModel.isSecure();
        this.systemQueue = buildModel.isSystemQueue();
    }

    @NotNull
    public Map<String, RawEntitySpec> resolveStoredEntities(BuildModel buildModel) {
        return buildModel.getEntityStorePref() == null ? Collections.emptyMap() : buildModel.getEntityStorePref().stream()
                .filter(k-> k.getStoredEntity() != null)
                .map(k->k.getStoredEntity())
                .collect(Collectors.toMap(s->s.getName(), s->s));
    }

    @Override
    public ComponentName getComponentName() {
        return ComponentName.APP;
    }

    @JsonIgnore
    public List<CodeObjectField> getObjectFields() {
        List<CodeEntityView> entities = new ArrayList<>();
        entities.addAll(this.entities);
        entities.addAll(dependencies);
        return entities.stream().flatMap(e -> e.getFields().stream())
                .map(CodeEntityField::getObjectField).filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableList());
    }
}
