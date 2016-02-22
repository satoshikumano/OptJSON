package com.nogumalabs.optjson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Optional;

public class OptJSONObject extends OptJSON {

    private JSONObject rawValue;
    public OptJSONObject(JSONObject jsonObject) {
        this.rawValue = jsonObject;
    }

    @Override
    public Optional<OptJSONObject> ifObject() {
        return Optional.ofNullable(this);
    }

    public Optional<OptJSON> get(String key) {
        if (rawValue.has(key)) {
            try {
                Object obj = rawValue.get(key);
                return Optional.ofNullable(OptJSON.fromRawValue(obj));
            } catch (JSONException e) {
            } catch (OptJSONException e) {
            }
        }
        return Optional.empty();
    }

}
