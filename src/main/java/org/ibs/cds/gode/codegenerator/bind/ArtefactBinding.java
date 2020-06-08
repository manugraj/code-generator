package org.ibs.cds.gode.codegenerator.bind;


public interface ArtefactBinding {

    boolean run(String configFile, String... args);

    static ArtefactBinding resolve(ArtifactPackaging packaging){
        switch (packaging){
            case MAVEN: default: return new MavenBind();
        }
    }
}
