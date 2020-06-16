package org.ibs.cds.gode.codegenerator.velocity;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class VelocityChannel {

    private static final Map<String, Object> container;

    static{
        container = new HashMap<>();
    }

    public static void put(String key, Object object){
        container.put(key, object);
    }

    public static <T>  T get(String key){
        T t = (T) container.get(key);
        container.remove(key);
        return t;
    }
}
