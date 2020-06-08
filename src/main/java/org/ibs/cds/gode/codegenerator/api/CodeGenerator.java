package org.ibs.cds.gode.codegenerator.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ibs.cds.gode.codegenerator.entity.AppCodeGenerator;
import org.ibs.cds.gode.entity.manager.AppManager;
import org.ibs.cds.gode.entity.type.App;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.codegenerator.model.deploy.DeploymentModel;
import org.ibs.cds.gode.entity.type.Specification;
import org.ibs.cds.gode.entity.operation.Executor;
import org.ibs.cds.gode.entity.operation.Logic;
import org.ibs.cds.gode.exception.KnownException;
import org.ibs.cds.gode.web.Request;
import org.ibs.cds.gode.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generator")
@Api(tags={"Gode(e) build endpoints"})
public class CodeGenerator {

    @Autowired
    private AppManager appManager;

    @PostMapping(path="/design")
    @ApiOperation(value = "Operation to design App")
    public Response<App> design(@RequestBody Request<App> appRequest){
        return Executor.run(Logic.save(), appRequest, appManager,KnownException.SAVE_FAILED, "/design");
    }

    @PostMapping(path="/build")
    @ApiOperation(value = "Operation to design App")
    public boolean build(@RequestBody Request<BuildModel> buildModelRequest){
        Specification app = buildModelRequest.getData().getApp();
        App foundApp = appManager.find(app.getName(), app.getVersion());
        if(foundApp == null) throw KnownException.OBJECT_NOT_FOUND.provide("No app available for given name and version");
        AppCodeGenerator appCodeGenerator = new AppCodeGenerator(foundApp, buildModelRequest.getData());
        return appCodeGenerator.generate();
    }

    @PostMapping(path="/deploy")
    @ApiOperation(value = "Operation to deploy App")
    public boolean deploy(@RequestBody Request<DeploymentModel> deploymentModelRequest){
        return true;
    }
}
