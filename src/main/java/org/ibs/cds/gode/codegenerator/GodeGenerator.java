package org.ibs.cds.gode.codegenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"org.ibs.cds.gode", "org.ibs.cds.gode.codegenerator", "org.ibs.cds.gode.entity", "org.ibs.cds.gode.entity.store"})
public class GodeGenerator {
    public static void main(String[] args) {
        SpringApplication.run(GodeGenerator.class, args);
    }
}
