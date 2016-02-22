package com.nogumalabs.optjson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Optional;
import java.util.stream.Stream;

public class OptJSONArray extends OptJSON {
    private JSONArray rawValue;
    public OptJSONArray(JSONArray jsonArray) {
        this.rawValue = jsonArray;
    }

    @Override
    public Optional<OptJSONArray> ifArray() {
        return Optional.ofNullable(this);
    }

    public Optional<OptJSON> getAt(int index) {
        try {
            Object obj = rawValue.get(index);
            return Optional.ofNullable(OptJSON.fromRawValue(obj));
        } catch (JSONException e) {
        } catch (OptJSONException e) {
        }
        return Optional.empty();
    }

    public Stream<OptJSON> stream() {
        Stream.Builder b = Stream.builder();
        for (int i=0; i<rawValue.length(); i++) {
            try {
                b.add(OptJSON.fromRawValue(rawValue.get(i)));
            } catch (OptJSONException e) {
            } catch (JSONException e) {
            }
        }
        return b.build();
    }

}
