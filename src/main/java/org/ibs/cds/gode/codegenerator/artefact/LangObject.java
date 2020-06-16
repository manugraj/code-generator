package org.ibs.cds.gode.codegenerator.artefact;

import org.ibs.cds.gode.codegenerator.spec.ProgLanguage;
import org.ibs.cds.gode.util.Assert;

public interface LangObject<T extends Artefact> {

    default String getFQN() {
        Assert.notNull(getArtefact());
        return getArtefact().getFQN();
    }



    T getArtefact();

    String getName();

    ProgLanguage getLanguage();
}
