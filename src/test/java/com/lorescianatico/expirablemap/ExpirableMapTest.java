package com.lorescianatico.expirablemap;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
        assertEquals("value", map.put("A", "new value"));
        assertEquals("new value", map.get("A"));
    }

    @Test
    void remove() {
        Map<String, String> map = new ExpirableMap<>();
        map.put("A", "value");
        assertEquals("value", map.get("A"));
        map.remove("A");
        assertNull(map.get("A"));
        assertNull(map.remove("A"));
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
        map.put("D", null);
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

    @Test
    void testRemovalOfExpiredElements() throws Exception{
        Map<String, String> map = new ExpirableMap<>(3000, TimeUnit.MILLISECONDS); //three seconds
        map.put("A", "a");
        Thread.sleep(1000); //one second
        map.put("B", "b");
        assertEquals(2, map.size());
        Thread.sleep(2500); //2.5 seconds
        assertTrue(map.size()<=2);
        Thread.sleep(3000); //three seconds
        assertTrue(map.size()<=1);
        Thread.sleep(2000);
        assertFalse(map.containsKey("A"));
        assertFalse(map.containsKey("B"));
        assertTrue(map.isEmpty());
    }

    @Test
    void toStringTest(){
        Map<String, String> map = new ExpirableMap<>(); //two seconds
        map.put("A", "a");
        map.put("B", "b");
        assertNotNull(map.toString());
    }

    @Test
    void testConstructors(){
        assertNotNull(new ExpirableMap<String, String>(20, 2, TimeUnit.SECONDS));
        assertNotNull(new ExpirableMap<String, String>(20, 0.80F, 2, TimeUnit.SECONDS));
        Map<String, String> map = new HashMap<>();
        map.put("A", "a");
        assertNotNull(new ExpirableMap<String, String>(map, 2, TimeUnit.SECONDS));
    }
}