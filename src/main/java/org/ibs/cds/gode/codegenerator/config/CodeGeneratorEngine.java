package org.ibs.cds.gode.codegenerator.config;

import org.ibs.cds.gode.entity.type.Specification;
import org.ibs.cds.gode.status.BinaryStatus;

import java.io.File;
import java.util.StringJoiner;

public interface CodeGeneratorEngine<T extends Specification & CodeGenerationComponent> {

    EngineConfiguration getConfiguration();

    boolean addToContext(String name, Object input);

    BinaryStatus run(T components);

    String getRepo();

    default String getProcessPath(T component) {
        File processPath = getConfiguration().getProcessPath();
        StringJoiner path = new StringJoiner(File.separator);
        if (processPath.exists()) {
            processPath.mkdirs();
        }
        return path.add(processPath.getAbsolutePath())
                .add(getRepo())
                .add(component.getComponentName().toString().toLowerCase())
                .add(component.getName().toLowerCase().concat(".yml"))
                .toString();
    }

}
