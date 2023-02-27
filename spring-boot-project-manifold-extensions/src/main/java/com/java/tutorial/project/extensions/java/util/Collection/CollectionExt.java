package com.java.tutorial.project.extensions.java.util.Collection;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;

import java.util.Collection;

/**
 * Collection 的扩展方法
 */
@Extension
public final class CollectionExt {

    public static boolean isNullOrEmpty(@This Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }
}
