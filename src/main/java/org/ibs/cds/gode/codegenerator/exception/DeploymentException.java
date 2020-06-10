package org.ibs.cds.gode.codegenerator.exception;

import org.ibs.cds.gode.exception.Error;
import org.ibs.cds.gode.exception.GodeException;

public class DeploymentException extends CodeGenerationException  {
    public DeploymentException(Error error) {
        super(error);
    }

    public DeploymentException(Error error, String message) {
        super(error, message);
    }

    public DeploymentException(Error error, String message, Throwable cause) {
        super(error, message, cause);
    }

    public DeploymentException(Error error, Throwable cause) {
        super(error, cause);
    }

    public DeploymentException(Error error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error, message, cause, enableSuppression, writableStackTrace);
    }
}
