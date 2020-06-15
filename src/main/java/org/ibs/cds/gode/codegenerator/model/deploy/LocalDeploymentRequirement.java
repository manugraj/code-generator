package org.ibs.cds.gode.codegenerator.model.deploy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.ibs.cds.gode.codegenerator.config.CodeGenerationComponent;
import org.ibs.cds.gode.codegenerator.entity.CodeApp;
import org.ibs.cds.gode.codegenerator.entity.CodeAppUtil;
import org.ibs.cds.gode.entity.store.StoreType;
import org.ibs.cds.gode.entity.type.FieldType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.ibs.cds.gode.codegenerator.model.deploy.Action.of;

public enum LocalDeploymentRequirement {
    ADMIN_PORT(c -> true, "adminPort", FieldType.NUMBER,
            of(CodeGenerationComponent.ComponentName.ADMIN, "server.port"),
            of(CodeGenerationComponent.ComponentName.APP, "spring.boot.admin.client.url", adminUrl())),

    JPA_DRIVER(requireJPA(), "jpaDriver", FieldType.TEXT,
            of(CodeGenerationComponent.ComponentName.APP, "gode.datastore.jpa.driver")),

    JPA_DIALECT(requireJPA(), "jpaDialect", FieldType.TEXT,
            of(CodeGenerationComponent.ComponentName.APP, "gode.datastore.jpa.dialect")),

    JPA_URL(requireJPA(), "jpaUrl", FieldType.TEXT,
            of(CodeGenerationComponent.ComponentName.APP, "gode.datastore.jpa.datasource.url")),

    JPA_USERNAME(requireJPA(), "jpaUsername", FieldType.TEXT,
            of(CodeGenerationComponent.ComponentName.APP, "gode.datastore.jpa.datasource.username")),

    JPA_PASSWORD(requireJPA(), "jpaPassword", FieldType.TEXT,
            of(CodeGenerationComponent.ComponentName.APP, "gode.datastore.jpa.datasource.password")),

    MONGODB_URI(requireMongoDB(), "mongoUri", FieldType.TEXT,
            of(CodeGenerationComponent.ComponentName.APP, "gode.datastore.mongodb.uri")),

    MONGODB_DATABASE(requireMongoDB(), "mongoDatabaseName", FieldType.TEXT,
            of(CodeGenerationComponent.ComponentName.APP, "gode.datastore.mongodb.database.name")),

    ACCESS_TOKEN_EXPIRY(CodeApp::isSecure, "accessTokenExpiryTime", FieldType.NUMBER,
            of(CodeGenerationComponent.ComponentName.APP, "gode.security.token.access.expiry")),

    REFRESH_TOKEN_EXPIRY(CodeApp::isSecure, "refreshTokenExpiryTime", FieldType.NUMBER,
            of(CodeGenerationComponent.ComponentName.APP, "gode.security.token.refresh.expiry")),


    APP_PORT(c -> true, "appPort", FieldType.NUMBER,
            of(CodeGenerationComponent.ComponentName.APP, "server.port")),

    MEDIA_SERVER_LOC(c -> true, "mediaServer", FieldType.TEXT,
            of(CodeGenerationComponent.ComponentName.APP, "gode.media.store.location")),

    ;

    private final Predicate<CodeApp> entryCriteria;
    private @Getter
    final String propertyName;
    private @Getter
    final FieldType type;
    private @Getter
    @Setter
    String value;
    private @Getter List<Action> actions;
    LocalDeploymentRequirement(Predicate<CodeApp> entryCriteria, String propertyName, FieldType type, Action... actions) {
        this.entryCriteria = entryCriteria;
        this.propertyName = propertyName;
        this.type = type;
        this.actions = List.of(actions);
    }

    @NotNull
    private static Function<org.ibs.cds.gode.codegenerator.model.deploy.LocalDeploymentRequirement, Function<CodeApp, String>> adminUrl() {
        return req -> codeApp -> "http://localhost:".concat(req.getValue()).concat("/").concat(CodeAppUtil.adminAppName(codeApp).toLowerCase());
    }

    @NotNull
    private static Predicate<CodeApp> requireJPA() {
        return c -> DeploymentRequirement.getStoreRequirements(c).containsKey(StoreType.JPA);
    }

    @NotNull
    private static Predicate<CodeApp> requireMongoDB() {
        return c -> DeploymentRequirement.getStoreRequirements(c).containsKey(StoreType.MONGODB);
    }

    @JsonIgnore
    public static LocalDeploymentRequirement from(String propertyName, String value, CodeApp app) {
        return Stream.of(LocalDeploymentRequirement.values())
                .filter(k -> k.propertyName.equals(propertyName))
                .findAny()
                .map(k -> {
                    k.setValue(value);
                    return k;
                })
                .orElse(null);
    }

    @JsonIgnore
    public static Map<String, FieldType> values(CodeApp app) {
        return Arrays
                .stream(LocalDeploymentRequirement.values())
                .filter(k -> k.entryCriteria.test(app))
                .collect(Collectors.toMap(s -> s.propertyName, s -> s.type));
    }

    @Override
    public String toString() {
        return this.propertyName;
    }
}
