package com.aiden.ad.index;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-15 22:07
 **/
public interface IndexAware<K,V> {

    V get(K key);

    void add(K key, V value);

    void update(K key, V value);

    void  delete(K key, V value);
}
