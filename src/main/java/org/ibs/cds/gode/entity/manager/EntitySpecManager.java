package org.ibs.cds.gode.entity.manager;

import org.ibs.cds.gode.entity.repo.EntitySpecRepository;
import org.ibs.cds.gode.entity.type.StatefulEntitySpec;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EntitySpecManager extends EntityManager<StatefulEntitySpec, StatefulEntitySpec, Long> {


    public EntitySpecManager(EntitySpecRepository repo) {
        super(repo, null);
    }

    public <T> List<T> findTransform(Function<StatefulEntitySpec,T> transformer){
        EntitySpecRepository entitySpecRepository = this.repository.get();
        List<StatefulEntitySpec> all = entitySpecRepository.findAll();
        return all == null ? Collections.emptyList() : all.stream().map(transformer).collect(Collectors.toList());
    }

    @Override
    public Optional<StatefulEntitySpec> transformEntity(Optional<StatefulEntitySpec> entitySpec) {
        return entitySpec;
    }

    @Override
    public Optional<StatefulEntitySpec> transformView(Optional<StatefulEntitySpec> entity) {
        return entity;
    }
}
