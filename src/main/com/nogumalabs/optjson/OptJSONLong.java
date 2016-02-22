package com.nogumalabs.optjson;

import java.util.Optional;

public class OptJSONLong extends OptJSON {
    public long rawValue;
    public OptJSONLong(long rawValue) {
        this.rawValue = rawValue;
    }

    @Override
    public Optional<Long> ifLong() {
        return Optional.ofNullable(rawValue);
    }

}
