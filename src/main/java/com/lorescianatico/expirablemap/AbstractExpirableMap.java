package com.lorescianatico.expirablemap;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

abstract class AbstractExpirableMap<K, V> {

    protected static final int DEFAULT_TIMEOUT = 10_000; //ten seconds

    protected Map<K, V> internalMap;

    protected long timeout;

    public AbstractExpirableMap(long timeout) {
        this.internalMap = new WeakHashMap<>();
        this.timeout = timeout;
    }

    public int size() {
        return internalMap.size();
    }

    public boolean isEmpty() {
        return this.internalMap.isEmpty();
    }

    public boolean containsKey(Object key) {
        return this.internalMap.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return this.internalMap.containsValue(value);
    }

    public V get(Object key) {
        return this.internalMap.get(key);
    }

    public V put(K key, V value) {
        return this.internalMap.put(key, value);
    }

    public V remove(Object key) {
        return this.internalMap.remove(key);
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        this.internalMap.putAll(m);
    }

    public void clear() {
        this.internalMap.clear();
    }

    public Set<K> keySet() {
        return this.internalMap.keySet();
    }

    public Collection<V> values() {
        return this.internalMap.values();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return this.internalMap.entrySet();
    }

    public V getOrDefault(Object key, V defaultValue) {
        return this.internalMap.getOrDefault(key, defaultValue);
    }

    public void forEach(BiConsumer<? super K, ? super V> action) {
        this.internalMap.forEach(action);
    }

    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        this.internalMap.replaceAll(function);
    }

    public V putIfAbsent(K key, V value) {
        return this.internalMap.putIfAbsent(key, value);
    }

    public boolean remove(Object key, Object value) {
        return this.internalMap.remove(key,value);
    }

    public boolean replace(K key, V oldValue, V newValue) {
        return this.internalMap.replace(key, oldValue, newValue);
    }

    public V replace(K key, V value) {
        return this.internalMap.replace(key, value);
    }

    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return this.internalMap.computeIfAbsent(key, mappingFunction);
    }

    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return this.internalMap.computeIfPresent(key, remappingFunction);
    }

    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return this.internalMap.compute(key, remappingFunction);
    }

    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return this.internalMap.merge(key, value, remappingFunction);
    }

    static class ExpirableValue<V>{

        private static final ExpirableValue EMPTY = of(null);

        private V value;

        private long timestamp;

        private ExpirableValue(V value) {
            this.value = value;
            this.timestamp = System.currentTimeMillis();
        }

        public boolean hasExpired(){
            return System.currentTimeMillis() > this.timestamp;
        }

        public Optional<V> getValueAsOptional(){
            return Optional.ofNullable(this.value);
        }

        public static <V> ExpirableValue<V> empty(){
            return (ExpirableValue<V>) EMPTY;
        }

        public static <V> ExpirableValue<V> of(V value){
            return new ExpirableValue<>(value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ExpirableValue<?> that = (ExpirableValue<?>) o;
            return Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
