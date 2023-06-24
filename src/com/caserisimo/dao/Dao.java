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
        Connection conexion = Session.getInstance().getConnection();
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultSet = sentencia.executeQuery("SELECT * FROM " + nombreTabla);
            while (resultSet.next()) {
                E entidad = crearEntidadDelResultSet(resultSet);
                lista.add(entidad);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
        return lista;
    }

    protected abstract E crearEntidadDelResultSet(ResultSet resultSet) throws SQLException;

    @Override
    public E findById(int id) {
        E entidad = null;
        Connection conexion = Session.getInstance().getConnection();
        try {
            String query = generateFindByIdQuery();
            PreparedStatement buscarEntidad = getPreparedStatementParameters(query, id);
            ResultSet resultSet = buscarEntidad.executeQuery();
            if (resultSet.next()) {
                entidad = crearEntidadDelResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
        return entidad;
    }

    private PreparedStatement getPreparedStatementParameters(String query, Object... params) throws SQLException {
        Connection conexion = Session.getInstance().getConnection();
        PreparedStatement preparedStatement = conexion.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement;
    }

    protected abstract String generateFindByIdQuery();

    @Override
    public void create(E object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            String query = generarInsertQuery(object);
            PreparedStatement crearEntidad = conexion.prepareStatement(query);
            setPreparedStatementParameters(crearEntidad, object);
            crearEntidad.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
    }

    protected abstract void setPreparedStatementParameters(PreparedStatement preparedStatement, E object);

    protected abstract String generarInsertQuery(E object);

    @Override
    public void update(E object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            String query = generarUpdateQuery(object);
            PreparedStatement modificarEntidad = conexion.prepareStatement(query);
            setPreparedStatementParameters(modificarEntidad,object);
            modificarEntidad.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }

    }

    protected abstract String generarUpdateQuery(E object) throws SQLException;

    @Override
    public void delete(E object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            String query = generarDeleteQuery();
            PreparedStatement eliminarEntidad = conexion.prepareStatement(query);
            setPreparedStatementParameters(eliminarEntidad,object);
            eliminarEntidad.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
    }

    protected abstract String generarDeleteQuery() throws SQLException;

    @Override
    public int count() {
        int count = 0;
        Connection conexion = Session.getInstance().getConnection();
        try {
            String query = generarCountQuery();
            PreparedStatement contarEntidad = conexion.prepareStatement(query);
            ResultSet resultSet = contarEntidad.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
        return count;
    }

    protected abstract String generarCountQuery() throws SQLException;
}
