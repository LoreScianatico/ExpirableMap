package com.lorescianatico.expirablemap;

import lombok.ToString;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@ToString(callSuper = true)
public final class ConcurrentExpirableMap<K,V> extends AbstractExpirableMap<K,V> implements ConcurrentMap<K,V> {

    public ConcurrentExpirableMap() {
        this(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    public ConcurrentExpirableMap(long timeout, TimeUnit timeUnit) {
        super(timeout, timeUnit);
        this.internalMap = Collections.synchronizedMap(internalMap);
    }

    public ConcurrentExpirableMap(int initialCapacity, float loadFactor, long timeout, TimeUnit timeUnit) {
        super(initialCapacity, loadFactor, timeout, timeUnit);
        this.internalMap = Collections.synchronizedMap(internalMap);
    }

    public ConcurrentExpirableMap(int initialCapacity, long timeout, TimeUnit timeUnit) {
        super(initialCapacity, timeout, timeUnit);
        this.internalMap = Collections.synchronizedMap(internalMap);
    }

    public ConcurrentExpirableMap(Map<? extends K, ? extends V> m, long timeout, TimeUnit timeUnit) {
        super(m, timeout, timeUnit);
        this.internalMap = Collections.synchronizedMap(internalMap);
    }
}
