package com.nogumalabs.optjson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
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

    public ArrayList<OptJSON> toArrayList() {
        ArrayList<OptJSON> l = new ArrayList<OptJSON>();
        for (int i=0; i<rawValue.length(); i++) {
            try {
                l.add(OptJSON.fromRawValue(rawValue.get(i)));
            } catch (OptJSONException e) {
            } catch (JSONException e) {
            }
        }
        return l;
    }

}
