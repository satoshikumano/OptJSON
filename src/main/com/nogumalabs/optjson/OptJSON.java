package com.nogumalabs.optjson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Optional;

public class OptJSON {

    private Object rawValue;

    public static OptJSON fromRawValue(Object rawValue) throws OptJSONException {
        if (rawValue instanceof JSONObject) {
            return new OptJSONObject((JSONObject)rawValue);
        } else if (rawValue instanceof JSONArray) {
            return new OptJSONArray((JSONArray)rawValue);
        } else if (rawValue instanceof Double) {
            return new OptJSONDouble((Double)rawValue);
        } else if (rawValue instanceof Long) {
            return new OptJSONLong((Long)rawValue);
        } else if (rawValue instanceof Boolean) {
            return new OptJSONBoolean((Boolean)rawValue);
        } else if (rawValue.equals(null)) {
            return new OptJSONNull();
        } else {
            throw new OptJSONException("Given Object is not acceptable type.");
        }
    }
    public static OptJSON parse(String jsonString) throws OptJSONException {
        JSONTokener t = new JSONTokener(jsonString);
        try {
            Object rawValue = t.nextValue();
            return fromRawValue(rawValue);
        } catch (JSONException e) {
            throw new OptJSONException("Given string is not a valid JSON", e);
        }
    }

    public Optional<OptJSONObject> ifObject() {
        return Optional.empty();
    }

    public Optional<OptJSONArray> ifArray() {
        return Optional.empty();
    }

    public Optional<String> ifString() {
        return Optional.empty();
    }

    public Optional<Double> ifDouble() {
        return Optional.empty();
    }

    public Optional<Long> ifLong() {
        return Optional.empty();
    }

    public Optional<Boolean> ifBoolean() {
        return Optional.empty().ofNullable(null);
    }

    public Optional<OptJSONNull> ifNull() {
        return Optional.empty().ofNullable(null);
    }
}
