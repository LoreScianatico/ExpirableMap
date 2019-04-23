package com.lorescianatico.expirablemap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ExpirableMap<K,V> implements Map<K,V> {

    protected static final int DEFAULT_TIMEOUT = 10_000; //ten seconds

    protected Map<K, V> internalMap;

    private long timeout;

    public ExpirableMap() {
        this(DEFAULT_TIMEOUT);
    }

    public ExpirableMap(long timeout) {
        this.timeout = timeout;
        this.internalMap = new WeakHashMap<>();
    }

    @Override
    public int size() {
        return internalMap.size();
    }

    @Override
    public boolean isEmpty() {
        return this.internalMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.internalMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.internalMap.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return this.internalMap.get(key);
    }

    @Override
    public V put(K key, V value) {
        return this.internalMap.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return this.internalMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        this.internalMap.putAll(m);
    }

    @Override
    public void clear() {
        this.internalMap.clear();
    }

    @Override
    public Set<K> keySet() {
        return this.internalMap.keySet();
    }

    @Override
    public Collection<V> values() {
        return this.internalMap.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.internalMap.entrySet();
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return this.internalMap.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        this.internalMap.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        this.internalMap.replaceAll(function);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return this.internalMap.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return this.internalMap.remove(key,value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return this.internalMap.replace(key, oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
        return this.internalMap.replace(key, value);
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return this.internalMap.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return this.internalMap.computeIfPresent(key, remappingFunction);
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return this.internalMap.compute(key, remappingFunction);
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return this.internalMap.merge(key, value, remappingFunction);
    }
}
