package org.ibs.cds.gode.codegenerator.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ibs.cds.gode.codegenerator.bind.ArtefactBinding;
import org.ibs.cds.gode.codegenerator.config.EngineConfiguration;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.codegenerator.model.deploy.DeploymentRequirement;
import org.ibs.cds.gode.codegenerator.velocity.VelocityGeneratorEngine;
import org.ibs.cds.gode.entity.store.StoreType;
import org.ibs.cds.gode.entity.type.App;
import org.ibs.cds.gode.status.BinaryStatus;

import java.util.stream.Stream;

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
        VelocityGeneratorEngine codeEntityVelocityGeneratorEngine = new VelocityGeneratorEngine(configuration);
        VelocityGeneratorEngine<CodeApp> codeAppVelocityGeneratorEngine = new VelocityGeneratorEngine(configuration);
        VelocityGeneratorEngine<CodeAdminApp> codeAdminAppVelocityGeneratorEngine = new VelocityGeneratorEngine(configuration);

        Stream.of(PathPackage.values()).forEach(pathPackage -> {
            codeEntityVelocityGeneratorEngine.addToContext(pathPackage.name(), pathPackage);
            codeAppVelocityGeneratorEngine.addToContext(pathPackage.name(), pathPackage);
            codeAdminAppVelocityGeneratorEngine.addToContext(pathPackage.name(), pathPackage);
        });

        codeEntityVelocityGeneratorEngine.addToContext("PathPackage", PathPackage.class);
        codeAppVelocityGeneratorEngine.addToContext("PathPackage", PathPackage.class);
        codeAdminAppVelocityGeneratorEngine.addToContext("PathPackage", PathPackage.class);

        codeEntityVelocityGeneratorEngine.addToContext("app", app);

        codeAppVelocityGeneratorEngine.addToContext("StoreType", StoreType.class);
        codeAppVelocityGeneratorEngine.addToContext("DeploymentRequirement", DeploymentRequirement.class);
        //Generation
        BinaryStatus entityGenerationStatus = BinaryStatus.valueOf(app.getEntities().stream()
                .map(k -> codeEntityVelocityGeneratorEngine.run(k))
                .allMatch(k -> k == BinaryStatus.SUCCESS));

        BinaryStatus codeAppStatus = codeAppVelocityGeneratorEngine.run(app);

        BinaryStatus codeAdminStatus = codeAdminAppVelocityGeneratorEngine.run(codeAdminApp);

        log.info("Code Generation status: Phase 1: {} | Phase 2: {} | Phase 3: {}", entityGenerationStatus, codeAppStatus, codeAdminStatus);

        BinaryStatus codeAppBuildStatus = BinaryStatus.valueOf(codeAppVelocityGeneratorEngine.getBuildable().stream()
                .map(buildable -> ArtefactBinding.resolve(buildModel.getArtifactPackaging()).run(buildable))
                .allMatch(k -> k));
        BinaryStatus codeAdminBuildStatus =  BinaryStatus.valueOf(codeAdminAppVelocityGeneratorEngine.getBuildable().stream()
                .map(buildable -> ArtefactBinding.resolve(buildModel.getArtifactPackaging()).run(buildable))
                .allMatch(k -> k));
        log.info("Code Build status: Phase 1: {} | Phase 2: {}", codeAppBuildStatus, codeAdminBuildStatus);
        return entityGenerationStatus.isSuccess() &&
                codeAppStatus.isSuccess() &&
                codeAdminStatus.isSuccess() &&
                codeAppBuildStatus.isSuccess() &&
                codeAdminBuildStatus.isSuccess();
    }
}
