package org.ibs.cds.gode.entity.manager;

import org.ibs.cds.gode.entity.repo.EntitySpecRepository;
import org.ibs.cds.gode.entity.repo.RepoType;
import org.ibs.cds.gode.entity.type.EntitySpec;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EntitySpecManager extends EntityManager<EntitySpec,EntitySpec, Long> {


    public EntitySpecManager(EntitySpecRepository repo) {
        super(repo, null);
    }

    public <T> List<T> findTransform(Function<EntitySpec,T> transformer){
        EntitySpecRepository entitySpecRepository = this.repository.get();
        List<EntitySpec> all = entitySpecRepository.findAll();
        return all == null ? Collections.emptyList() : all.stream().map(transformer).collect(Collectors.toList());
    }

    @Override
    public Optional<EntitySpec> transformEntity(Optional<EntitySpec> entitySpec) {
        return entitySpec;
    }

    @Override
    public Optional<EntitySpec> transformView(Optional<EntitySpec> entity) {
        return entity;
    }
}
