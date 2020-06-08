package org.ibs.cds.gode.codegenerator.artefact;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.spec.ProgLanguage;

@Data
public class JavaObject extends AnnotatedArtefact implements LangObject<JavaObject>{
    private final String name;

    public JavaObject(String name, String type, String packageName, Artefact... annotations) {
        super(type, packageName, annotations);
        this.name = name;
    }

    @Override
    public JavaObject getArtefact() {
        return this;
    }

    @Override
    public ProgLanguage getLanguage() {
        return ProgLanguage.JAVA;
    }
}
