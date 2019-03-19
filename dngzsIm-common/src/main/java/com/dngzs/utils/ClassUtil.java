package com.dngzs.utils;

import org.reflections.Reflections;

import java.util.Set;

public class ClassUtil<T> {
    public Set<T> get(String packageName, Class T) {
        Reflections reflections = new Reflections(packageName);
        return reflections.getSubTypesOf(T);
    }
}
