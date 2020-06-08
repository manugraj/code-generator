package org.ibs.cds.gode.codegenerator.exception;

import org.ibs.cds.gode.exception.Error;
import org.ibs.cds.gode.exception.GodeException;

public class CodeGenerationException extends GodeException {
    public CodeGenerationException(Error error) {
        super(error);
    }

    public CodeGenerationException(Error error, String message) {
        super(error, message);
    }

    public CodeGenerationException(String message) {
        super(null, message);
    }

    public CodeGenerationException(Error error, String message, Throwable cause) {
        super(error, message, cause);
    }

    public CodeGenerationException(Error error, Throwable cause) {
        super(error, cause);
    }

    public CodeGenerationException(Error error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error, message, cause, enableSuppression, writableStackTrace);
    }
}
