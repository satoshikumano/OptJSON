package com.nogumalabs.optjson;

import java.util.Optional;

public class OptJSONBoolean extends OptJSON {
    boolean rawValue;
    public OptJSONBoolean(boolean rawValue) {
        this.rawValue = rawValue;
    }

    @Override
    public Optional<Boolean> ifBoolean() {
        return Optional.ofNullable(rawValue);
    }

}
