package com.example.asteroid.storage;

import java.util.HashMap;
import java.util.Map;

/**
 * Game cache based on {@link HashMap} with basic operations like put and get. Keys usually stored at
 * {@link com.example.asteroid.AbstractConstant} class.
 */
public class Cache {

    private final Map<String, Object> CACHE;

    private static Cache instance;

    public Cache() {
        CACHE = new HashMap<>();
    }

    public static Cache getInstance() {
        if (instance == null) {
            instance = new Cache();
        }
        return instance;
    }

    public void setValue(String key, Object o) {
        put(key, o);
    }

    public Object getValue(String key) {
        return CACHE.get(key);
    }

    public void setLong(String key, Long o) {
        put(key, o);
    }

    public Long getLong(String key) {
        return (Long) CACHE.get(key);
    }

    private void put(String key, Object o) {
        CACHE.put(key, o);
    }

}
