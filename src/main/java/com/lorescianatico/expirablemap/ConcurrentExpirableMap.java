package com.lorescianatico.expirablemap;

import lombok.ToString;

import java.util.Collections;
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


}
