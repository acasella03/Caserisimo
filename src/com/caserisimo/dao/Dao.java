package com.caserisimo.dao;

import java.sql.*;
import java.util.ArrayList;

public abstract class Dao<E> implements IDao<E> {
    private final String nombreTabla;

    protected Dao(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    @Override
    public ArrayList<E> getAll() {
        ArrayList<E> lista = new ArrayList<>();
        try {
            Connection conexion = Session.getInstance().getConnection();
            Statement sentencia = conexion.createStatement();
            ResultSet resultSet = sentencia.executeQuery("SELECT * FROM" + nombreTabla);
            while (resultSet.next()) {
                E entidad = crearEntidadDelResultSet(resultSet);
                lista.add(entidad);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection();
        }
        return lista;
    }

    protected abstract E crearEntidadDelResultSet(ResultSet resultSet) throws SQLException;

    @Override
    public E findById(int id) {
        E entidad = null;
        try {
            Connection conexion = Session.getInstance().getConnection();
            PreparedStatement buscarEntidad = conexion.prepareStatement("SELECT * FROM " + nombreTabla + " WHERE id = ?");
            buscarEntidad.setInt(1, id);
            ResultSet resultSet = buscarEntidad.executeQuery();
            if (resultSet.next()) {
                entidad = crearEntidadDelResultSet(resultSet);
                buscarYAgregar(entidad, id);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection();
        }
        return entidad;
    }

    protected abstract void buscarYAgregar(E entidad, int id) throws SQLException;

    @Override
    public void create(E object) {
        try {
            Connection conexion = Session.getInstance().getConnection();
            String query = generarInsertQuery(object);
            PreparedStatement crearEntidad = conexion.prepareStatement(query);
            setPreparedStatementParameters(crearEntidad, object);
            crearEntidad.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection();
        }
    }

    protected abstract void setPreparedStatementParameters(PreparedStatement preparedStatement, E object);

    protected abstract String generarInsertQuery(E object) throws SQLException;

    @Override
    public void update(E object) {
        try {
            Connection conexion = Session.getInstance().getConnection();
            String query = generarUpdateQuery(object);
            PreparedStatement modificarEntidad = conexion.prepareStatement(query);
            setPreparedStatementParameters(modificarEntidad, object);
            modificarEntidad.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection();
        }

    }

    protected abstract String generarUpdateQuery(E object) throws SQLException;

    @Override
    public void delete(E object) {
        try {
            Connection conexion = Session.getInstance().getConnection();
            String query = generarDeleteQuery(object);
            PreparedStatement eliminarEntidad = conexion.prepareStatement(query);
            setPreparedStatementParameters(eliminarEntidad, object);
            eliminarEntidad.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection();
        }
    }

    protected abstract String generarDeleteQuery(E object) throws SQLException;

    @Override
    public int count() {
        int count = 0;
        try {
            Connection conexion = Session.getInstance().getConnection();
            String query = generarCountQuery();
            PreparedStatement contarEntidad = conexion.prepareStatement(query);
            ResultSet resultSet = contarEntidad.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection();
        }
        return count;
    }

    protected abstract String generarCountQuery() throws SQLException;
}
