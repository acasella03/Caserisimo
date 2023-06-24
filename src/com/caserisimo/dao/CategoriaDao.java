package com.caserisimo.dao;

import com.caserisimo.modelo.Categoria;

import java.sql.*;

public class CategoriaDao extends Dao<Categoria> {
    private static final String CATEGORIAS = "categorias";
    Categoria categoria = new Categoria();

    protected CategoriaDao() {
        super(CATEGORIAS);
    }

    @Override
    protected Categoria crearEntidadDelResultSet(ResultSet resultSet) throws SQLException {
        categoria.setIdCategoria(resultSet.getInt("id_categoria"));
        categoria.setNombre(resultSet.getString("nombre"));
        return categoria;
    }

    @Override
    protected void setPreparedStatementParameters(PreparedStatement preparedStatement, Categoria object) {
        try {
            preparedStatement.setInt(1, findById(categoria.getIdCategoria()).getIdCategoria());
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @Override
    protected String generarInsertQuery(Categoria object) throws SQLException {
        String query = "INSERT INTO " + CATEGORIAS + " nombre VALUES ?";
        return query;
    }

    @Override
    protected String generarUpdateQuery(Categoria object) throws SQLException {
        String query = "UPDATE " + CATEGORIAS + " SET nombre = ? WHERE id = ?";
        return query;
    }

    @Override
    protected String generarDeleteQuery(Categoria object) throws SQLException {
        String query = "DELETE FROM " + CATEGORIAS + " WHERE id = ?";
        return query;
    }

    @Override
    protected String generarCountQuery() throws SQLException {
        String query = "SELECT COUNT * FROM " + CATEGORIAS;
        return query;
    }

    @Override
    public void createTable() {
        try {
            Connection conexion = Session.getInstance().getConnection();
            Statement sentencia = conexion.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + CATEGORIAS + " (" +
                    "id_categoria INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT" +
                    ")";
            sentencia.executeUpdate(query);
            System.out.println("Tabla " + CATEGORIAS + " creada exitosamente.");
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection();
        }
    }
}
