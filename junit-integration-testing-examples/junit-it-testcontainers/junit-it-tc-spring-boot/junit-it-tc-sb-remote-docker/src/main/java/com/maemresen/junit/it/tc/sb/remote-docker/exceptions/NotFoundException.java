package com.maemresen.tcw.sb.remote.docker.exceptions;

import com.maemresen.tcw.sb.remote.docker.utils.constants.ExceptionType;

public class NotFoundException extends ServiceException {
    public NotFoundException(String message, Object data) {
        super(message, data, ExceptionType.NOT_FOUND);
    }

    public NotFoundException(Throwable cause) {
        super(cause, ExceptionType.NOT_FOUND);
    }
}
