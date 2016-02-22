package com.nogumalabs.optjson;

import java.util.Optional;

public class OptJSONNull extends OptJSON {

    @Override
    public Optional<OptJSONNull> ifNull() {
        return Optional.ofNullable(new OptJSONNull());
    }

}
