package com.caserisimo.dao;

import com.caserisimo.modelo.Camarero;

import java.sql.*;

public class CamareroDao extends Dao<Camarero> {
    private static final String CAMAREROS = "camareros";
    Camarero camarero = new Camarero();

    public CamareroDao() {
        super(CAMAREROS);
    }

    @Override
    public void createTable() {
        try{
            Connection conexion = Session.getInstance().getConnection();
            Statement sentencia = conexion.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + CAMAREROS + " (" +
                    "id_camarero INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT" +
                    ")";
            sentencia.executeUpdate(query);
            System.out.println("Tabla " + CAMAREROS + " creada exitosamente.");
        } catch (SQLException e) {
            System.err.println(e);
        }finally {
            Session.getInstance().closeConnection();
        }
    }

    @Override
    protected Camarero crearEntidadDelResultSet(ResultSet resultSet) throws SQLException {
        camarero.setIdCamarero(resultSet.getInt("id_camarero"));
        camarero.setNombre(resultSet.getString("nombre"));
        return camarero;
    }

    @Override
    protected void setPreparedStatementParameters(PreparedStatement preparedStatement, Camarero object) {
        try {
            preparedStatement.setInt(1, findById(camarero.getIdCamarero()).getIdCamarero());
        } catch (SQLException e) {
            System.err.println(e);
        }

    }

    @Override
    protected String generarInsertQuery(Camarero object) throws SQLException {
        String query = "INSERT INTO " + CAMAREROS + " nombre VALUES ?";
        return query;
    }

    @Override
    protected String generarUpdateQuery(Camarero object) throws SQLException {
        String query = "UPDATE " + CAMAREROS + " SET nombre = ? WHERE id = ?";
        return query;
    }

    @Override
    protected String generarDeleteQuery(Camarero object) throws SQLException {
        String query = "DELETE FROM " + CAMAREROS + " WHERE id = ?";
        return query;
    }

    @Override
    protected String generarCountQuery() throws SQLException {
        String query = "SELECT COUNT * FROM " + CAMAREROS;
        return query;
    }

}
