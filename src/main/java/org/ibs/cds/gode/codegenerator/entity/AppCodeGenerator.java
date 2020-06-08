package org.ibs.cds.gode.codegenerator.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.ibs.cds.gode.codegenerator.config.EngineConfiguration;
import org.ibs.cds.gode.entity.type.App;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.codegenerator.velocity.VelocityGeneratorEngine;

@Data
@RequiredArgsConstructor
public class AppCodeGenerator {

    private final App rawApp;
    private final BuildModel buildModel;

    public boolean generate(){
        CodeApp app = new CodeApp(rawApp, buildModel);
        EngineConfiguration configuration = new EngineConfiguration(buildModel);
        boolean entityGen = app.getEntities().stream().map(k->{
            VelocityGeneratorEngine<CodeEntity> codeEntityVelocityGeneratorEngine = new VelocityGeneratorEngine<>(configuration);
            return codeEntityVelocityGeneratorEngine.run(k);
        }).allMatch(k->k);
        VelocityGeneratorEngine<CodeApp> codeAppVelocityGeneratorEngine = new VelocityGeneratorEngine<>(configuration);
        return entityGen && codeAppVelocityGeneratorEngine.run(app);
    }
}
