package org.ibs.cds.gode.codegenerator.model.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class SystemRunner {

    public static String run(String... commands) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(commands);
        Process p = builder
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start();
        p.waitFor();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        return r.lines().collect(Collectors.joining());
    }
}
