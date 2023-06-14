package com.caserisimo.dao;

import java.util.ArrayList;

public interface IDao<E> {
    ArrayList<E> getAll();
    E findById(int id);
    void create(E object);
    void update(E object);
    void delete(E object);
    int count();
}
