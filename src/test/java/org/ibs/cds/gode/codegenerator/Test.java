package org.ibs.cds.gode.codegenerator;

import org.ibs.cds.gode.codegenerator.bind.ArtifactPackaging;
import org.ibs.cds.gode.codegenerator.entity.AppCodeGenerator;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.codegenerator.spec.Level;
import org.ibs.cds.gode.codegenerator.spec.ProgLanguage;
import org.ibs.cds.gode.codegenerator.spec.StoreName;
import org.ibs.cds.gode.entity.type.*;

import java.util.List;
import java.util.Set;

public class Test {

    public static void main(String[] args) {

        EntityField field = new EntityField();
        field.setName("name");
        field.setType(FieldType.TEXT);

        IdField idField = new IdField();
        idField.setName("eid");
        idField.setType(FieldType.TEXT);

        EntityState state = new EntityState();
        state.setOpsLevel(new OperationLevel(Level.HIGH, Level.MEDIUM, Level.LOW, false, false));
        EntityStateStore test = new EntityStateStore();
        test.setStoreName(StoreName.MYSQL);
        state.setEntityStateStore(test);

        EntityState state2 = new EntityState();
        state2.setOpsLevel(new OperationLevel(Level.HIGH, Level.MEDIUM, Level.LOW, false, false));
        EntityStateStore test2 = new EntityStateStore();
        test2.setStoreName(StoreName.MONGODB);
        state2.setEntityStateStore(test2);

        EntitySpec entitySpec = new EntitySpec();
        entitySpec.setName("Entity1");
        entitySpec.setDescription("Entity 1 descr");
        entitySpec.setVersion(2L);
        entitySpec.setFields(Set.of(field));
        entitySpec.setIdField(idField);
        entitySpec.setState(state);

        EntitySpec entitySpec2 = new EntitySpec();
        entitySpec2.setName("Entity2");
        entitySpec2.setDescription("Entity 2 descr");
        entitySpec2.setVersion(3L);
        entitySpec2.setFields(Set.of(field));
        entitySpec2.setIdField(idField);
        entitySpec2.setState(state2);

        AppFunction function = new AppFunction();
        function.setMethodName("method1");
        function.setInput(List.of(entitySpec));
        function.setOutput(List.of(entitySpec));

        App app = new App();
        app.setName("App1");
        app.setDescription("App1 description");
        app.setVersion(2L);
        app.setEntities(List.of(entitySpec,entitySpec2));
        app.setFunctions(List.of(function));

        BuildModel model = new BuildModel();
        model.setProgLanguage(ProgLanguage.JAVA);
        model.setArtifactPackaging(ArtifactPackaging.MAVEN);
        model.setApp(app);

        AppCodeGenerator appCodeGenerator = new AppCodeGenerator( app, model );
        appCodeGenerator.generate();
    }
}
