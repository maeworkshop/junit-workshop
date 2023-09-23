package com.maemresen.it.sb.with.database.calculator;

import org.springframework.stereotype.Service;

@Service
public class TrigonometryService {

    public double sin(double a) {
        return Math.sin(Math.toRadians(a));
    }

    public double cos(double a) {
        return Math.cos(Math.toRadians(a));
    }
}
