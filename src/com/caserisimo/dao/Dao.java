package com.caserisimo.dao;

import java.util.ArrayList;

public abstract class Dao<E> implements IDao<E> {
    @Override
    public ArrayList<E> getAll() {
        return null;
    }

    @Override
    public E findById(int id) {
        return null;
    }

    @Override
    public void create(E object) {

    }

    @Override
    public void update(E object) {

    }

    @Override
    public void delete(E object) {

    }

    @Override
    public int count() {
        return 0;
    }
}
