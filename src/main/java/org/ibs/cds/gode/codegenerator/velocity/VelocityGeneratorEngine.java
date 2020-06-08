package org.ibs.cds.gode.codegenerator.velocity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.ibs.cds.gode.codegenerator.config.*;
import org.ibs.cds.gode.codegenerator.exception.CodeGenerationFailure;
import org.ibs.cds.gode.entity.type.Specification;
import org.ibs.cds.gode.codegenerator.spec.YamlReadWriteUtil;
import org.ibs.cds.gode.system.GodeConstant;
import org.ibs.cds.gode.util.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.*;
import java.util.List;

@Data
@Slf4j
@RequiredArgsConstructor
public class VelocityGeneratorEngine<T extends Specification & CodeGenerationComponent> implements CodeGeneratorEngine<T> {

    private final EngineConfiguration engineConfiguration;
    private final VelocityContext context;

    public VelocityGeneratorEngine(EngineConfiguration engineConfiguration) {
        this.engineConfiguration = engineConfiguration;
        this.context = new VelocityContext();
        context.put("StringUtils", StringUtils.class);
        context.put("GodeConstant", GodeConstant.class);
    }

    @Override
    public EngineConfiguration getConfiguration() {
        return this.engineConfiguration;
    }

    @Override
    public boolean addToContext(String name, Object input) {
        return context.put(name, input) != null;
    }

    private GenerateComponentConfiguration process(T component) throws IOException {
        log.info("Processing Component:{} version:{} Type: {}", component.getName(), component.getVersion(), component.getComponentName());
        ComponentConfiguration componentConfiguration = this.engineConfiguration.getComponentConfiguration().get(component);
        Reader template = new BufferedReader(new InputStreamReader(new FileInputStream(componentConfiguration.getVmLocation())));
        File parentFile = this.engineConfiguration.getProcessPath().getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        String processPath = getProcessPath(component);
        build(template, processPath);
        log.info("Processed Component:{} version:{} Type: {}", component.getName(), component.getVersion(), component.getComponentName());
        return YamlReadWriteUtil.readFile(new File(processPath), GenerateComponentConfiguration.class);
    }

    private void build(Reader template, String processPath) throws IOException {
        Writer writer = new FileWriter(processPath);
        Velocity.evaluate(this.context, writer, "Generation", template);
        writer.flush();
        writer.close();
    }


    private boolean generate(T component, GenerateComponentConfiguration generateComponent) throws IOException {
        log.info("Generating Component:{} version:{} Type: {}", component.getName(), component.getVersion(), component.getComponentName());
        List<GenerateComponent> configurations = generateComponent.getConfiguration();
        for (GenerateComponent configuration: configurations) {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource resource = resolver.getResource(this.engineConfiguration.getBuildConfiguration().getComponentTemplatePath().concat(File.separator).concat(configuration.getTemplate()));
            Reader templateReader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            build(templateReader, configuration.getPath().concat(File.separator).concat(configuration.getName()));
            log.info("Generated  Element:{}", configuration.getName());
        }
        log.info("Generated Component:{} version:{} Type: {}", component.getName(), component.getVersion(), component.getComponentName());
        return true;
    }

    @Override
    public boolean run(T component) {
        try {
            return this.generate(component, process(component));
        } catch (IOException e) {
            throw CodeGenerationFailure.SYSTEM_ERROR.provide(e);
        }
    }
}
