package com.lorescianatico.expirablemap;

import lombok.ToString;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@ToString(callSuper = true)
public final class ExpirableMap<K,V> extends AbstractExpirableMap<K, V> implements Map<K,V> {

    public ExpirableMap() {
        this(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    public ExpirableMap(long timeout, TimeUnit timeUnit) {
        super(timeout, timeUnit);
    }

    public ExpirableMap(int initialCapacity, float loadFactor, long timeout, TimeUnit timeUnit) {
        super(initialCapacity, loadFactor, timeout, timeUnit);
    }

    public ExpirableMap(int initialCapacity, long timeout, TimeUnit timeUnit) {
        super(initialCapacity, timeout, timeUnit);
    }

    public ExpirableMap(Map<? extends K, ? extends V> m, long timeout, TimeUnit timeUnit) {
        super(m, timeout, timeUnit);
    }
}
