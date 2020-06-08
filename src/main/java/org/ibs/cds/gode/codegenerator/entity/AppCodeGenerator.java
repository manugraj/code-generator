package org.ibs.cds.gode.codegenerator.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ibs.cds.gode.codegenerator.bind.ArtefactBinding;
import org.ibs.cds.gode.codegenerator.config.EngineConfiguration;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.codegenerator.velocity.VelocityGeneratorEngine;
import org.ibs.cds.gode.entity.type.App;

@Data
@RequiredArgsConstructor
@Slf4j
public class AppCodeGenerator {

    private final App rawApp;
    private final BuildModel buildModel;

    public boolean generate() {
        CodeApp app = new CodeApp(rawApp, buildModel);
        CodeAdminApp codeAdminApp = new CodeAdminApp(app, buildModel);
        EngineConfiguration configuration = new EngineConfiguration(buildModel);
        boolean entityGenerationStatus = app.getEntities().stream()
                .map(k -> new VelocityGeneratorEngine(configuration).run(k))
                .allMatch(k -> k);
        VelocityGeneratorEngine<CodeApp> codeAppVelocityGeneratorEngine = new VelocityGeneratorEngine(configuration);
        VelocityGeneratorEngine<CodeAdminApp> codeAdminAppVelocityGeneratorEngine = new VelocityGeneratorEngine(configuration);
        boolean codeAppStatus = codeAppVelocityGeneratorEngine.run(app);
        boolean codeAdminStatus = codeAdminAppVelocityGeneratorEngine.run(codeAdminApp);
        log.info("Code Generation status: Phase 1: {} | Phase 2: {} | Phase 3: {}", entityGenerationStatus, codeAppStatus, codeAdminStatus);

        boolean codeAppBuildStatus = codeAppVelocityGeneratorEngine.getBuildable().stream()
                .map(buildable -> ArtefactBinding.resolve(buildModel.getArtifactPackaging()).run(buildable))
                .allMatch(k -> k);
        boolean codeAdminBuildStatus = codeAdminAppVelocityGeneratorEngine.getBuildable().stream()
                .map(buildable -> ArtefactBinding.resolve(buildModel.getArtifactPackaging()).run(buildable))
                .allMatch(k -> k);
        log.info("Code Build status: Phase 1: {} | Phase 2: {}", codeAppBuildStatus, codeAdminBuildStatus);
        return entityGenerationStatus &&
                codeAppStatus &&
                codeAdminStatus &&
                codeAppBuildStatus &&
                codeAdminBuildStatus;
    }
}
