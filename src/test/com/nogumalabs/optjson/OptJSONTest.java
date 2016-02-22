package com.nogumalabs.optjson;

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

}