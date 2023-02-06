package com.solvd.farm.DAO;

public interface AbstractDAO<T> {

    void create(T t);

    T getById(long id);
}