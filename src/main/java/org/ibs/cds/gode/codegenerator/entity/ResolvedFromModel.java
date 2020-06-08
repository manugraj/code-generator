package org.ibs.cds.gode.codegenerator.entity;

import org.ibs.cds.gode.codegenerator.model.build.BuildModel;

public interface ResolvedFromModel<T,R> {
    T getModel();
    BuildModel getBuildModel();
    R process(T t, BuildModel buildModel);
}
