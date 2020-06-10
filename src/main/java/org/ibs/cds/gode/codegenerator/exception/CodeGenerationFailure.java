package org.ibs.cds.gode.codegenerator.exception;

import org.ibs.cds.gode.exception.Error;
import org.ibs.cds.gode.exception.*;

import java.io.Serializable;

public enum CodeGenerationFailure {

    DATATYPE_NOT_IDENTIFIED(-1,"Failed to identify datatype"){
        private String message = getMessage();
        public CodeGenerationException provide(Throwable e, Serializable details) {
            return new CodeGenerationException(new Error(getCode(), message, details), message, e);
        }

        public CodeGenerationException provide(Serializable details) {
            return new CodeGenerationException(new Error(getCode(), message, details), message);
        }
    },
    LANGUAGE_NOT_FOUND(-2,"Failed to identify programming language"){
        private String message = getMessage();
        public CodeGenerationException provide(Throwable e, Serializable details) {
            return new CodeGenerationException(new Error(getCode(), message, details), message, e);
        }

        public CodeGenerationException provide(Serializable details) {
            return new CodeGenerationException(new Error(getCode(), message, details), message);
        }
    },
    SYSTEM_ERROR(-3,"System error"){
        private String message = getMessage();
        public CodeGenerationException provide(Throwable e, Serializable details) {
            return new CodeGenerationException(new Error(getCode(), message, details), message, e);
        }

        public CodeGenerationException provide(Serializable details) {
            return new CodeGenerationException(new Error(getCode(), message, details), message);
        }
    },
    DEPLOYMENT_FAILURE(-3,"Deployment error"){
        private String message = getMessage();
        public DeploymentException provide(Throwable e, Serializable details) {
            return new DeploymentException(new Error(getCode(), message, details), message, e);
        }

        public DeploymentException provide(Serializable details) {
            return new DeploymentException(new Error(getCode(), message, details), message);
        }
    };

    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    CodeGenerationFailure(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public <T extends CodeGenerationException>  T provide(Throwable e){
        return provide(e, null);
    }
    public <T extends CodeGenerationException>  T provide(){
        return provide((String) null);
    }
    public abstract <T extends CodeGenerationException,Details extends Serializable>  T provide(Details details);
    public abstract <T extends CodeGenerationException,Details extends Serializable>  T provide(Throwable e, Details details);

}
