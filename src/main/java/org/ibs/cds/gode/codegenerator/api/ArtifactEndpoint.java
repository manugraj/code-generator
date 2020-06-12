package org.ibs.cds.gode.codegenerator.api;

import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ibs.cds.gode.entity.manager.AppManager;
import org.ibs.cds.gode.entity.manager.EntitySpecManager;
import org.ibs.cds.gode.entity.operation.Executor;
import org.ibs.cds.gode.entity.operation.Logic;
import org.ibs.cds.gode.entity.operation.Processor;
import org.ibs.cds.gode.entity.type.EntitySpec;
import org.ibs.cds.gode.exception.KnownException;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.APIArgument;
import org.ibs.cds.gode.web.Request;
import org.ibs.cds.gode.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artifact")
@Api(tags={"Gode(e) Artifact endpoint"})
public class ArtifactEndpoint {

    @Autowired
    private EntitySpecManager entitySpecManager;

    @Autowired
    private AppManager appManager;

    @PostMapping(path="/entity")
    @ApiOperation(value = "Operation to create Entity")
    public Response<EntitySpec> createEntity(@RequestBody Request<EntitySpec> entityRequest){
        return Executor.run(Logic.save(), entityRequest, entitySpecManager,KnownException.SAVE_FAILED, "/entity");
    }

    @GetMapping(path="/entity")
    @ApiOperation(value = "Operation to get Entity")
    public Response<EntitySpec> queryEntity(Long artifactId){
        return Executor.run(Logic.findById(), artifactId, entitySpecManager,KnownException.QUERY_FAILED, "/entity");
    }

    @GetMapping(path="/brief")
    @ApiOperation(value = "Operation to get brief package")
    public Response<List<Brief>> brief(BriefType type){
        switch (type){
            case APP: return Processor.successResponse(appManager.findTransform(BriefUtil::toBrief), type.name(), "/brief");
            case ENTITY: default: return Processor.successResponse(entitySpecManager.findTransform(BriefUtil::toBrief), type.name(), "/brief");
        }
    }

    @GetMapping(path="/entity/view")
    @ApiOperation(value = "Operation to view Entity")
    public Response<PagedData<EntitySpec>> queryEntity(@QuerydslPredicate(root = EntitySpec.class) Predicate predicate, @ModelAttribute APIArgument apiargs){
        return predicate == null ?
                Executor.run(Logic.findAll(), PageContext.fromAPI(apiargs), entitySpecManager,KnownException.QUERY_FAILED, "/entity/view")
                : Executor.run(Logic.findAllByPredicate(), PageContext.fromAPI(apiargs),predicate, entitySpecManager,KnownException.QUERY_FAILED, "/entity/view");
    }
}
