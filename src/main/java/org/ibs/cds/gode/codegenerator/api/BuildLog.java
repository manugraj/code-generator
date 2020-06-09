package org.ibs.cds.gode.codegenerator.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ibs.cds.gode.entity.generic.DataMap;
import org.ibs.cds.gode.entity.manager.BuildDataManager;
import org.ibs.cds.gode.entity.operation.Processor;
import org.ibs.cds.gode.entity.type.BuildData;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.APIArgument;
import org.ibs.cds.gode.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generator/history")
@Api(tags = {"Gode(e) build history"})
public class BuildLog {

    @Autowired
    private BuildDataManager buildDataManager;

    @GetMapping(path = "/build")
    @ApiOperation(value = "Operation to get build details")
    public Response<DataMap> findLatestBuild(@RequestParam(required = false) String appName, @RequestParam(required = false) Long appVersion, @RequestParam(required = false) Boolean latest, @ModelAttribute APIArgument argument) {
        DataMap dataMap = new DataMap();
        PageContext pageContextResolved = PageContext.fromAPI(argument);
        if (appName != null && appVersion != null) {
            if (latest != null) {
                BuildData buildData = buildDataManager.findLatestBuild(appName, appVersion);
                dataMap.put("buildData", buildData);
                return Processor.successResponse(dataMap, String.format("App name: %s | Latest: %s", appName, latest.toString()), "/build");
            }
            PagedData<BuildData> buildDataPage = buildDataManager.findBuild(appName, appVersion, pageContextResolved);
            dataMap.put("buildData", buildDataPage);
            return Processor.successResponse(dataMap, pageContextResolved.toString(), "/build");
        }
        PagedData<BuildData> buildDataPage = buildDataManager.find(pageContextResolved);
        dataMap.put("buildData", buildDataPage);
        return Processor.successResponse(dataMap, pageContextResolved.toString(), "/build");
    }
}
