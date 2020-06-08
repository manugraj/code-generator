package org.ibs.cds.gode.codegenerator.artefact;

import lombok.Data;

import java.util.List;

@Data
public class AnnotatedArtefact extends Artefact {
    private List<Artefact> annotations;

    public AnnotatedArtefact(String type, String packageName, Artefact... annotations) {
        super(type, packageName);
        this.annotations = List.of(annotations);
    }
}
