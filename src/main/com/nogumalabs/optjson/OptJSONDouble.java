package com.nogumalabs.optjson;

import java.util.Optional;

public class OptJSONDouble extends OptJSON {
    private double rawValue;
    public OptJSONDouble(double rawValue) {
        this.rawValue = rawValue;
    }

    @Override
    public Optional<Double> ifDouble() {
        return Optional.ofNullable(rawValue);
    }

}
