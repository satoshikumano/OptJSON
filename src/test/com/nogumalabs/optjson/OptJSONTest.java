package com.nogumalabs.optjson;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OptJSONTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void usageTest() throws Exception {
        String jsonStr = "{\"key1\" : \"str1\"}";

        OptJSON json = OptJSON.parse(jsonStr);
        json.ifObject().ifPresent((optJSONObject) -> {
            optJSONObject.get("key1").ifPresent((optJSON) -> {
                optJSON.ifString().ifPresent((s -> assertEquals("str1", s)));
            });
        });
    }

    @Test
    public void objectTest() throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("numberA", 1);
        obj.put("numberB", 2);
        obj.put("strA", "1");
        OptJSON optJSON = OptJSONObject.parse(obj.toString());
        optJSON.ifObject().ifPresent((consumer) -> {
            consumer.ifObject().ifPresent((jobj) -> {
                long result = jobj.entrySet().stream().filter((predicate) -> {
                    return predicate.getKey().startsWith("number");
                }).reduce(0L,
                        (total, ent) -> total + ent.getValue().ifLong().get().longValue(),
                        (t1, t2) ->t1+t2);
                assertEquals(3, result);
            });
        });
    }
    @Test
    public void arrayTest1() throws Exception {
        String jsonStr = "[1,2,3,4,5]";

        OptJSON json = OptJSON.parse(jsonStr);
        assertEquals(5, json.ifArray().get().toArrayList().size());
        long result = json.ifArray().get().toArrayList().stream().reduce(
                0L,
                (total, elm) -> total + elm.ifLong().get().longValue(),
                (total1, total2) -> total1 + total2);
        assertEquals(15L, result);
    }

    @Test
    public void arrayTest2() throws Exception {
        String jsonStr = "[1,2,3,4,\"5\"]";

        OptJSON json = OptJSON.parse(jsonStr);
        assertEquals(5, json.ifArray().get().toArrayList().size());
        long result = json.ifArray().get().toArrayList().stream().reduce(
                0L,
                (total, elm) -> {
                    return  elm.ifLong().map(aLong -> aLong + total).orElse(total);
                },
                (total1, total2) -> total1 + total2);
        assertEquals(10L, result);
    }
}