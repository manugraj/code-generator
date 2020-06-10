package org.ibs.cds.gode.codegenerator.entity;

public class CodeAppUtil {
    public static String adminAppName(CodeApp app){
        return app.getName().concat("-Monitor");
    }

    public static String containerAppName(CodeApp app){
        return app.getName().concat("-container");
    }

    public static String javaPath(){
        return PathPackage.path("src","main","java");
    }

    public static String javaResourcePath(){
        return PathPackage.path("src","main","resources");
    }

    public static String graphqlPath(){
        return PathPackage.path(CodeAppUtil.javaResourcePath(), "graphql");
    }
}
