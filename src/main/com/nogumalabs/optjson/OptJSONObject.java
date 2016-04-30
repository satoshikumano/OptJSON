package com.nogumalabs.optjson;

import javafx.util.Pair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

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

    public Set<Pair<String, OptJSON>> entrySet() {
        Set<Pair<String, OptJSON>> ret = new HashSet<>();
        for (Iterator i = rawValue.keys(); i.hasNext();) {
            String key = (String) i.next();
            try {
                Object obj = rawValue.get(key);
                OptJSON optJSON = OptJSON.fromRawValue(obj);
                Pair<String, OptJSON> entry = new Pair<>(key, optJSON);
                ret.add(entry);
            } catch (JSONException e) {
                // Wont' happens.
                throw new RuntimeException("Unexpected error", e);
            } catch (OptJSONException e) {
                // Wont' happens.
                throw new RuntimeException("Unexpected error", e);
            }
        }
        return ret;
    }
}
