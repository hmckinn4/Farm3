package com.solvd.farm.DAO.interfaces;

public interface AbstractDAO<T> {

    void create(T t);

    T getById(Long id);
}