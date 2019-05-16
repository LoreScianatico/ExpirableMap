package com.lorescianatico.expirablemap;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.lorescianatico.expirablemap.AbstractExpirableMap.ExpirableValue.of;

@EqualsAndHashCode(of = {"internalMap", "timeout"})
@ToString(of = {"internalMap", "timeout"})
abstract class AbstractExpirableMap<K, V> {

    protected static final int DEFAULT_TIMEOUT = 10_000; //ten seconds

    protected Map<K, ExpirableValue<V>> internalMap;

    protected long timeout;

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    AbstractExpirableMap(long timeout, TimeUnit timeUnit) {
        this.internalMap = new WeakHashMap<>();
        this.timeout = TimeUnit.MILLISECONDS.convert(timeout, timeUnit);
        this.executorService.scheduleWithFixedDelay(this::removeExpiredElements, 0, this.timeout, TimeUnit.MILLISECONDS);
        Runtime.getRuntime().addShutdownHook(new Thread(executorService::shutdownNow));
    }

    AbstractExpirableMap(int initialCapacity, float loadFactor, long timeout, TimeUnit timeUnit) {
        this.internalMap = new WeakHashMap<>(initialCapacity, loadFactor);
        this.timeout = TimeUnit.MILLISECONDS.convert(timeout, timeUnit);
        this.executorService.scheduleWithFixedDelay(this::removeExpiredElements, 0, this.timeout, TimeUnit.MILLISECONDS);
        Runtime.getRuntime().addShutdownHook(new Thread(executorService::shutdownNow));
    }

    AbstractExpirableMap(int initialCapacity, long timeout, TimeUnit timeUnit) {
        this.internalMap = new WeakHashMap<>(initialCapacity);
        this.timeout = TimeUnit.MILLISECONDS.convert(timeout, timeUnit);
        this.executorService.scheduleWithFixedDelay(this::removeExpiredElements, 0, this.timeout, TimeUnit.MILLISECONDS);
        Runtime.getRuntime().addShutdownHook(new Thread(executorService::shutdownNow));
    }

    AbstractExpirableMap(Map<? extends K, ? extends V> m, long timeout, TimeUnit timeUnit) {
        this.timeout = TimeUnit.MILLISECONDS.convert(timeout, timeUnit);
        Map<K, ExpirableValue<V>> wrappedMap = new WeakHashMap<>();
        m.forEach((k, v) -> wrappedMap.put(k, of(v,timeout)));
        this.internalMap = new WeakHashMap<>(wrappedMap);
        this.executorService.scheduleWithFixedDelay(this::removeExpiredElements, 0, this.timeout, TimeUnit.MILLISECONDS);
        Runtime.getRuntime().addShutdownHook(new Thread(executorService::shutdownNow));
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
        return this.internalMap.containsValue(of(value, this.timeout));
    }

    public V get(Object key) {
        ExpirableValue<V> value = this.internalMap.get(key);
        if(value != null) {
            return value.getValue();
        }
        return null;
    }

    public V put(K key, V value) {
        ExpirableValue<V> previousValue = this.internalMap.put(key, of(value, this.timeout));
        if (previousValue != null){
            return previousValue.getValue();
        }
        return null;
    }

    public V remove(Object key) {
        return this.internalMap.remove(key).getValue();
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        m.forEach((k, v) -> this.internalMap.put(k, of(v, this.timeout)));
    }

    public void clear() {
        this.internalMap.clear();
    }

    public Set<K> keySet() {
        return this.internalMap.keySet();
    }

    public Collection<V> values() {
        return this.internalMap.values().stream()
                .filter(value -> value.getValue()!=null)
                .map(ExpirableValue::getValue)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return internalMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getValue())).entrySet();
    }

    public V getOrDefault(Object key, V defaultValue) {
        return this.internalMap.getOrDefault(key, of(defaultValue, this.timeout)).getValue();
    }

    public void forEach(BiConsumer<? super K, ? super V> action) {
        this.internalMap.forEach((k, v) -> action.accept(k, v.getValue()));
    }

    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        this.internalMap.replaceAll((k, v) -> of(function.apply(k, v.getValue()), this.timeout));
    }

    public V putIfAbsent(K key, V value) {
        ExpirableValue<V> previous = this.internalMap.putIfAbsent(key, of(value, this.timeout));
        if(previous != null){
            return previous.getValue();
        }
        return null;
    }

    public boolean remove(Object key, Object value) {
        return this.internalMap.remove(key, of(value, this.timeout));
    }

    public boolean replace(K key, V oldValue, V newValue) {
        return this.internalMap.replace(key, of(oldValue, this.timeout), of(newValue, this.timeout));
    }

    public V replace(K key, V value) {
        ExpirableValue<V> replaced = this.internalMap.replace(key, of(value, this.timeout));
        if(replaced != null){
            return replaced.getValue();
        }
        return null;
    }

    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return this.internalMap.computeIfAbsent(key, k -> of(mappingFunction.apply(k), this.timeout)).getValue();
    }

    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return this.internalMap.computeIfPresent(key, (k, v) -> of(remappingFunction.apply(k, v.getValue()), this.timeout)).getValue();
    }

    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return this.internalMap.compute(key, (k, v) ->
            of(remappingFunction.apply(k, v != null ? v.getValue() : null), this.timeout)
        ).getValue();
    }

    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return this.internalMap.merge(key, of(value, this.timeout),
                (vOld, vNew)-> of(remappingFunction.apply(vOld.getValue(), vNew.getValue()), this.timeout)).getValue();
    }

    private void removeExpiredElements() {
        List<K> expiredKeys = this.internalMap.entrySet().stream()
                .filter(entry -> entry.getValue().hasExpired())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        expiredKeys.forEach(internalMap::remove);
    }

    @EqualsAndHashCode(of = "value")
    @ToString
    protected static class ExpirableValue<V>{

        private V value;

        private long timestamp;

        private ExpirableValue(V value, long timeToLive) {
            this.value = value;
            this.timestamp = System.currentTimeMillis() + timeToLive;
        }

        boolean hasExpired(){
            return System.currentTimeMillis() > this.timestamp;
        }

        V getValue(){
            return this.value;
        }

        static <V> ExpirableValue<V> of(V value, long timeTolive){
            return new ExpirableValue<>(value, timeTolive);
        }
    }
}
