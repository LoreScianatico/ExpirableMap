package com.lorescianatico.expirablemap;

import java.util.Collections;
import java.util.concurrent.ConcurrentMap;

public final class ConcurrentExpirableMap<K,V> extends AbstractExpirableMap<K,V> implements ConcurrentMap<K,V> {

    public ConcurrentExpirableMap() {
        this(DEFAULT_TIMEOUT);
    }

    public ConcurrentExpirableMap(long timeout) {
        super(timeout);
        this.internalMap = Collections.synchronizedMap(internalMap);
    }
}
