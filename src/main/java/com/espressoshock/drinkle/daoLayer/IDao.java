package com.espressoshock.drinkle.daoLayer;

import java.util.List;

/********* K:key, E:entity         */
public interface IDao<K, E> {

    void save(E entity);
    void update(K k, E entity);
    void remove(K k,E entity);
    E getByKey(K id);
    List<E>getAll();


}
