package com.lorescianatico.expirablemap;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExpirableMapTest {

    @Test
    void size() {
        Map<String, Object> map = new ExpirableMap<>();
        assertEquals(0, map.size());
        map.put("A", new Object());
        assertEquals(1, map.size());
        map.put("B", new Object());
        assertEquals(2, map.size());
        map.put("C", new Object());
        assertEquals(3, map.size());
    }

    @Test
    void isEmpty() {
        Map<String, Object> map = new ExpirableMap<>();
        assertTrue(map.isEmpty());
        map.put("A", new Object());
        assertFalse(map.isEmpty());
    }

    @Test
    void containsKey() {
        Map<String, Object> map = new ExpirableMap<>();
        assertFalse(map.containsKey("A"));
        map.put("A", new Object());
        assertTrue(map.containsKey("A"));
        map.remove("A");
        assertFalse(map.containsKey("A"));
    }

    @Test
    void containsValue() {
        Map<String, String> map = new ExpirableMap<>();
        assertFalse(map.containsValue("value"));
        map.put("A", "value");
        assertTrue(map.containsValue("value"));
        map.remove("A");
        assertFalse(map.containsValue("value"));
    }

    @Test
    void get() {
        Map<String, String> map = new ExpirableMap<>();
        map.put("A", "value");
        assertEquals("value", map.get("A"));
    }

    @Test
    void put() {
        Map<String, String> map = new ExpirableMap<>();
        map.put("A", "value");
        assertEquals("value", map.get("A"));
    }

    @Test
    void remove() {
        Map<String, String> map = new ExpirableMap<>();
        map.put("A", "value");
        assertEquals("value", map.get("A"));
        map.remove("A");
        assertNull(map.get("A"));
    }

    @Test
    void putAll() {
    }

    @Test
    void clear() {
    }

    @Test
    void keySet() {
    }

    @Test
    void values() {
    }

    @Test
    void entrySet() {
    }

    @Test
    void getOrDefault() {
    }

    @Test
    void forEach() {
    }

    @Test
    void replaceAll() {
    }

    @Test
    void putIfAbsent() {
    }

    @Test
    void remove1() {
    }

    @Test
    void replace() {
    }

    @Test
    void replace1() {
    }

    @Test
    void computeIfAbsent() {
    }

    @Test
    void computeIfPresent() {
    }

    @Test
    void compute() {
    }

    @Test
    void merge() {
    }
}