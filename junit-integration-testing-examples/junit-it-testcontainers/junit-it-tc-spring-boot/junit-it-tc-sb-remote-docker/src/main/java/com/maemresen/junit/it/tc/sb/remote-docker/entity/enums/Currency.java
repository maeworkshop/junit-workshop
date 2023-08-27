package com.maemresen.tcw.sb.remote.docker.entity.enums;

public enum Currency {
    TRY, USD, EUR;

    public static Currency getDefaultValue() {
        return EUR;
    }
}
