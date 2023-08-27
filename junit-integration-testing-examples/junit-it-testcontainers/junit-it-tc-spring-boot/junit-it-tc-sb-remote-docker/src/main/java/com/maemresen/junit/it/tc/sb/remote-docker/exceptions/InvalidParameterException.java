package com.maemresen.tcw.sb.remote.docker.exceptions;

import com.maemresen.tcw.sb.remote.docker.utils.constants.ExceptionType;

public class InvalidParameterException extends ServiceException {

    public InvalidParameterException(String message, Throwable cause) {
        super(message, cause, ExceptionType.INVALID_PARAMETER);
    }

    public InvalidParameterException(Throwable cause) {
        super(cause, ExceptionType.INVALID_PARAMETER);
    }

    public InvalidParameterException(Throwable cause, Object data) {
        super(cause, ExceptionType.INVALID_PARAMETER, data);
    }
}
