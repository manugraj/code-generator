package org.ibs.cds.gode.codegenerator.entity;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.ibs.cds.gode.codegenerator.exception.CodeGenerationFailure;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.entity.type.EntitySpec;
import org.ibs.cds.gode.entity.type.EntityState;
import org.ibs.cds.gode.entity.type.EntityStateStore;
import org.ibs.cds.gode.entity.type.EntityStorePolicy;
import org.ibs.cds.gode.util.Assert;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

@Data
public class CodeEntityStorePolicy implements ResolvedFromModel<EntitySpec, StorePolicy> {
    private BuildModel buildModel;
    private EntitySpec model;
    private StorePolicy policy;

    public CodeEntityStorePolicy(EntitySpec entitySpec, BuildModel buildModel) {
        this.policy = process(entitySpec, buildModel);
        this.model = entitySpec;
        this.buildModel = buildModel;
    }

    public boolean isAvailable() {
        return this.policy != null && !this.policy.isVolatileEntity();
    }

    public boolean hasDatabase(){
        return isAvailable() && this.policy.getStoreName() != null;
    }

    public boolean isCached(){
        return isAvailable() && this.policy.isCached();
    }

    public boolean isAsync(){
        return  isAvailable() && this.policy.isAsyncStoreSync();
    }

    public StorePolicy process(EntitySpec spec, BuildModel buildModel) {
        Assert.notNull(spec);
        List<EntityStorePolicy> entityStorePref = buildModel.getEntityStorePref();
        if (CollectionUtils.isEmpty(entityStorePref)) getStorePolicy(spec.getState());
        return entityStorePref.stream()
                .filter(s -> s.getEntity().getName().equals(spec.getName()) && s.getEntity().getVersion().equals(spec.getVersion()))
                .findAny()
                .map(s -> getStorePolicy(s.getState()))
                .orElse(getStorePolicy(spec.getState()));
    }

    @Nullable
    private StorePolicy getStorePolicy(EntityState state) {
        if (state != null) {
            if (state.isVolatileEntity()) return StorePolicy.noPolicy();
            EntityStateStore pref = state.getEntityStateStore();
            if(pref != null){
                return new StorePolicy(pref.getStoreName(), pref.isCached(), pref.isAsyncStoreSync(), false);
            }
        }
        if(state.getOpsLevel() != null){
            return new PersistenceDecision(state.getOpsLevel()).getStorePolicy();
        }
        throw CodeGenerationFailure.SYSTEM_ERROR.provide("Store policy cannot be resolved");
    }
}
