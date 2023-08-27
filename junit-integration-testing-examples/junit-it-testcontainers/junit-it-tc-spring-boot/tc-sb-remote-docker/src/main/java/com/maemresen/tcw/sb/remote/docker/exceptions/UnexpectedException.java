package com.maemresen.tcw.sb.remote.docker.exceptions;

import com.maemresen.tcw.sb.remote.docker.utils.constants.ExceptionType;

public class UnexpectedException extends ServiceException {
    public UnexpectedException(Throwable throwable) {
        super(throwable, ExceptionType.UNEXPECTED);
    }
}

