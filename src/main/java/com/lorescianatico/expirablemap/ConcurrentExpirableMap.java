package com.lorescianatico.expirablemap;

import java.util.Collections;
import java.util.Map;

public final class ConcurrentExpirableMap<K,V> extends ExpirableMap<K,V> implements Map<K,V> {

    public ConcurrentExpirableMap() {
        this(DEFAULT_TIMEOUT);
    }

    public ConcurrentExpirableMap(long timeout) {
        super(timeout);
        this.internalMap = Collections.synchronizedMap(internalMap);
    }
}
