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
import org.ibs.cds.gode.utils.StoreUtils;

import java.util.Arrays;
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
        String repo = app.getVersion().toString();
        VelocityGeneratorEngine<CodeEntity> codeEntityVelocityGeneratorEngine = new VelocityGeneratorEngine(configuration, repo);
        VelocityGeneratorEngine<CodeAppFunctionNode> codeAppFunctionVelocityGeneratorEngine = new VelocityGeneratorEngine(configuration, repo);
        VelocityGeneratorEngine<CodeApp> codeAppVelocityGeneratorEngine = new VelocityGeneratorEngine(configuration, repo);
        VelocityGeneratorEngine<CodeAdminApp> codeAdminAppVelocityGeneratorEngine = new VelocityGeneratorEngine(configuration, repo);

        Stream.of(PathPackage.values()).forEach(pathPackage -> {
            codeEntityVelocityGeneratorEngine.addToContext(pathPackage.name(), pathPackage);
            codeAppVelocityGeneratorEngine.addToContext(pathPackage.name(), pathPackage);
            codeAdminAppVelocityGeneratorEngine.addToContext(pathPackage.name(), pathPackage);
            codeAppFunctionVelocityGeneratorEngine.addToContext(pathPackage.name(), pathPackage);
        });

        commonProperties(codeEntityVelocityGeneratorEngine,
                codeAppFunctionVelocityGeneratorEngine,
                codeAppVelocityGeneratorEngine,
                codeAdminAppVelocityGeneratorEngine);

        codeEntityVelocityGeneratorEngine.addToContext("app", app);
        codeEntityVelocityGeneratorEngine.addToContext("StoreUtils", StoreUtils.class);

        codeAppFunctionVelocityGeneratorEngine.addToContext("app", app);

        //Generation
        BinaryStatus entityGenerationStatus = BinaryStatus.valueOf(app.getEntities().stream()
                .map(k -> codeEntityVelocityGeneratorEngine.run(k))
                .allMatch(k -> k == BinaryStatus.SUCCESS));

        BinaryStatus appFunctionGenerationStatus = codeAppFunctionVelocityGeneratorEngine.run(app.getAppFunction());


        BinaryStatus codeAppStatus = codeAppVelocityGeneratorEngine.run(app);

        BinaryStatus codeAdminStatus = codeAdminAppVelocityGeneratorEngine.run(codeAdminApp);

        log.info("Code Generation status: Phase 1: {} | Phase 2: {} | Phase 3: {} | Phase 4: {}",
                entityGenerationStatus, appFunctionGenerationStatus, codeAppStatus, codeAdminStatus);

        BinaryStatus codeAppBuildStatus = BinaryStatus.valueOf(codeAppVelocityGeneratorEngine.getBuildable().stream()
                .map(buildable -> ArtefactBinding.resolve(buildModel.getArtifactPackaging()).run(buildable))
                .allMatch(k -> k));
        log.info("Code Build status: {} ", codeAppBuildStatus);
        return entityGenerationStatus.isSuccess() &&
                codeAppStatus.isSuccess() &&
                codeAdminStatus.isSuccess() &&
                codeAppBuildStatus.isSuccess();
    }

    public void commonProperties(VelocityGeneratorEngine... generatorEngines) {
        Arrays.stream(generatorEngines).forEach(generatorEngine->{
            generatorEngine.addToContext("PathPackage", PathPackage.class);
            generatorEngine.addToContext("CodeAppUtil", CodeAppUtil.class);
            generatorEngine.addToContext("StoreType", StoreType.class);
            generatorEngine.addToContext("DeploymentRequirement", DeploymentRequirement.class);
        });


    }
}
