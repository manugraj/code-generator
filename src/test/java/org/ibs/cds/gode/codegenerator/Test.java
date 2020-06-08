package org.ibs.cds.gode.codegenerator;

import org.ibs.cds.gode.codegenerator.bind.ArtifactPackaging;
import org.ibs.cds.gode.codegenerator.entity.AppCodeGenerator;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.codegenerator.spec.Level;
import org.ibs.cds.gode.codegenerator.spec.ProgLanguage;
import org.ibs.cds.gode.entity.type.*;

import java.util.Set;

public class Test {

    public static void main(String[] args) {

        EntityField field = new EntityField();
        field.setName("name");
        field.setType(FieldType.TEXT);

        EntityField idField = new EntityField();
        idField.setName("eid");
        idField.setType(FieldType.TEXT);

        EntityState state = new EntityState();
        state.setOpsLevel(new OperationLevel(Level.HIGH, Level.MEDIUM, Level.LOW, false, false));

        EntitySpec entitySpec = new EntitySpec();
        entitySpec.setName("Entity1");
        entitySpec.setDescription("Entity 1 descr");
        entitySpec.setVersion(1L);
        entitySpec.setFields(Set.of(field));
        entitySpec.setIdField(idField);
        entitySpec.setState(state);

        App app = new App();
        app.setName("App1");
        app.setDescription("App1 description");
        app.setVersion(1L);
        app.setEntities(Set.of(entitySpec));

        BuildModel model = new BuildModel();
        model.setProgLanguage(ProgLanguage.JAVA);
        model.setArtifactPackaging(ArtifactPackaging.MAVEN);
        model.setApp(app);

        AppCodeGenerator appCodeGenerator = new AppCodeGenerator( app, model );
        appCodeGenerator.generate();
    }
}
