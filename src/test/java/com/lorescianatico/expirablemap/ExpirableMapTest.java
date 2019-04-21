package com.lorescianatico.expirablemap;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

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
        Map<String, String> map = new ExpirableMap<>();
        map.put("A", "value");
        Map<String, String> map1 = new ExpirableMap<>();
        map1.put("B", "value");
        map.putAll(map1);
        assertEquals(2, map.size());
    }

    @Test
    void clear() {
        Map<String, Object> map = new ExpirableMap<>();
        map.put("A", new Object());
        map.put("B", new Object());
        map.put("C", new Object());
        assertEquals(3, map.size());
        map.clear();
        assertEquals(0, map.size());
    }

    @Test
    void keySet() {
        Map<String, Object> map = new ExpirableMap<>();
        assertEquals(0, map.keySet().size());
        map.put("A", new Object());
        map.put("B", new Object());
        map.put("C", new Object());
        Set<String> keys = map.keySet();
        assertEquals(3, keys.size());
        assertTrue(keys.contains("A"));
    }

    @Test
    void values() {
        Map<String, String> map = new ExpirableMap<>();
        assertEquals(0, map.keySet().size());
        map.put("A", "a");
        map.put("B", "b");
        map.put("C", "c");
        Collection<String> values = map.values();
        assertEquals(3, values.size());
        assertTrue(values.contains("a"));
    }

    @Test
    void entrySet() {
        fail("Not yet implemented.");
    }

    @Test
    void getOrDefault() {
        fail("Not yet implemented.");
    }

    @Test
    void forEach() {
        fail("Not yet implemented.");
    }

    @Test
    void replaceAll() {
        fail("Not yet implemented.");
    }

    @Test
    void putIfAbsent() {
        fail("Not yet implemented.");
    }

    @Test
    void remove1() {
        fail("Not yet implemented.");
    }

    @Test
    void replace() {
        fail("Not yet implemented.");
    }

    @Test
    void replace1() {
        fail("Not yet implemented.");
    }

    @Test
    void computeIfAbsent() {
        fail("Not yet implemented.");
    }

    @Test
    void computeIfPresent() {
        fail("Not yet implemented.");
    }

    @Test
    void compute() {
        fail("Not yet implemented.");
    }

    @Test
    void merge() {
        fail("Not yet implemented.");
    }
}