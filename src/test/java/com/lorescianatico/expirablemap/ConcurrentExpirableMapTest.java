package com.lorescianatico.expirablemap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcurrentExpirableMapTest {

    @Test
    void testConstructor(){
        assertNotNull(new ConcurrentExpirableMap<String, Object>(1000));
        assertNotNull(new ConcurrentExpirableMap<String, Object>());
    }

}