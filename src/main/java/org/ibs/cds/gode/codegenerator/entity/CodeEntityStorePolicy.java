package org.ibs.cds.gode.codegenerator.entity;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.codegenerator.model.entity.EntityState;
import org.ibs.cds.gode.codegenerator.spec.*;
import org.ibs.cds.gode.util.Assert;

@Data
public class CodeEntityStorePolicy implements ResolvedFromModel<EntityState,StorePolicy>{
    private BuildModel buildModel;
    private EntityState model;
    private StorePolicy policy;

    public CodeEntityStorePolicy(EntityState stateModel, BuildModel buildModel) {
        this.model = model;
        this.policy = process(model, buildModel);
        this.buildModel = buildModel;
    }

    public StorePolicy process(EntityState model, BuildModel buildModel){
        Assert.notNull(model);
        if(model.getStateStorePref() != null){
           StateStore pref = model.getStateStorePref();
           return new StorePolicy(pref.getDatabase(), pref.isCached(), pref.isAsyncStoreSync());
        }
        return new PersistenceDecision(model.getOpsLevel()).getStorePolicy();
    }
}
