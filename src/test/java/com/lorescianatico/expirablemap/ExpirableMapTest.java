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
        Map<String, String> map = new ExpirableMap<>();
        assertEquals(0, map.keySet().size());
        map.put("A", "a");
        map.put("B", "b");
        map.put("C", "c");
        assertEquals(3, map.entrySet().size());
    }

    @Test
    void getOrDefault() {
        Map<String, String> map = new ExpirableMap<>();
        map.put("A", "a");
        assertEquals("a", map.getOrDefault("A", "b"));
        assertEquals("b", map.getOrDefault("B", "b"));
    }

    @Test
    void forEach() {
        Map<String, String> map = new ExpirableMap<>();
        map.put("A", "a");
        map.forEach((k,v) -> {assertNotNull(k); assertNotNull(v);});
    }

    @Test
    void replaceAll() {
        Map<String, String> map = new ExpirableMap<>();
        map.put("A", "a");
        map.replaceAll((k, v) -> v+k);
        assertEquals("aA", map.get("A"));
    }

    @Test
    void putIfAbsent() {
        Map<String, String> map = new ExpirableMap<>();
        map.put("A", "a");
        assertEquals("a", map.putIfAbsent("A", "b"));
        assertNull(map.putIfAbsent("B", "b"));
        assertEquals("b", map.get("B"));
    }

    @Test
    void remove1() {
        Map<String, String> map = new ExpirableMap<>();
        map.put("A", "a");
        assertFalse(map.remove("B", "b"));
        assertFalse(map.remove("A", "b"));
        assertTrue(map.remove("A", "a"));
    }

    @Test
    void replace() {
        Map<String, String> map = new ExpirableMap<>();
        map.put("A", "a");
        assertFalse(map.replace("B", "b", "b"));
        assertFalse(map.replace("A", "b", "a"));
        assertTrue(map.replace("A", "a", "b"));
    }

    @Test
    void replace1() {
        Map<String, String> map = new ExpirableMap<>();
        map.put("A", "a");
        assertNull(map.replace("B", "b"));
        assertEquals("a",map.replace("A", "b"));
        assertEquals("b",map.get("A"));
    }

    @Test
    void computeIfAbsent() {
        Map<String, String> map = new ExpirableMap<>();
        map.put("A", "a");
        assertEquals("b", map.computeIfAbsent("B", String::toLowerCase));
    }

    @Test
    void computeIfPresent() {
        Map<String, String> map = new ExpirableMap<>();
        map.put("A", "a");
        assertEquals("Aa",map.computeIfPresent("A", (k, v)->k+v));
    }

    @Test
    void compute() {
        Map<String, String> map = new ExpirableMap<>();
        map.put("A", "a");
        assertEquals("Aa",map.compute("A", (k, v)->k+v));
        assertEquals("b",map.compute("B", (k, v)->k.toLowerCase()));
    }

    @Test
    void merge() {
        Map<String, String> map = new ExpirableMap<>();
        map.put("A", "a");
        assertEquals("ab",map.merge("A", "b", (v1, v2)->v1+v2));
        assertEquals("b",map.merge("B", "b", (v1, v2)->v1.toLowerCase()));
    }
}