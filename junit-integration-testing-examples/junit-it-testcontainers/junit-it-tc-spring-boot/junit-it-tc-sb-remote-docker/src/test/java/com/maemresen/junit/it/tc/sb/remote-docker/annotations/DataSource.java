package com.maemresen.tcw.sb.remote.docker.annotations;

import com.maemresen.tcw.sb.remote.docker.base.BaseAbstractDataLoader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    String value();
    Class<? extends BaseAbstractDataLoader<?, ?>> loader();
}