package com.lorescianatico.expirablemap;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ConcurrentExpirableMapTest {

    @Test
    void testConstructor(){
        assertNotNull(new ConcurrentExpirableMap<String, Object>(1000, TimeUnit.MILLISECONDS));
        assertNotNull(new ConcurrentExpirableMap<String, Object>());
    }

    @Test
    void toStringTest(){
        Map<String, String> map = new ConcurrentExpirableMap<>();
        map.put("A", "a");
        map.put("B", "b");
        assertNotNull(map.toString());
    }
}