package com.lorescianatico.expirablemap;

import lombok.ToString;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@ToString(callSuper = true)
public final class ExpirableMap<K,V> extends AbstractExpirableMap<K, V> implements Map<K,V> {

    public ExpirableMap() {
        this(DEFAULT_TIMEOUT);
    }

    public ExpirableMap(long timeout) {
        super(timeout, TimeUnit.MILLISECONDS);
    }

}
