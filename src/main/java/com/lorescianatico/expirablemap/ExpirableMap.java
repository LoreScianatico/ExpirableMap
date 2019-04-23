package com.lorescianatico.expirablemap;

import java.util.Map;


public final class ExpirableMap<K,V> extends AbstractExpirableMap<K, V> implements Map<K,V> {

    public ExpirableMap() {
        this(DEFAULT_TIMEOUT);
    }

    public ExpirableMap(long timeout) {
        super(timeout);
    }

}
