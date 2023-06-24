package com.caserisimo.dao;

import com.caserisimo.modelo.Camarero;

import java.lang.reflect.Field;
import java.sql.*;

public class CamareroDao extends Dao<Camarero> {
    private static final String CAMAREROS = "camareros";
    Camarero camarero = new Camarero();

    public CamareroDao() {
        super(CAMAREROS);
    }

    @Override
    public void createTable() {
        Connection conexion = Session.getInstance().getConnection();
        try{
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
            Session.getInstance().closeConnection(conexion);
        }
    }

    @Override
    protected Camarero crearEntidadDelResultSet(ResultSet resultSet) throws SQLException {
        camarero.setIdCamarero(resultSet.getInt("id_camarero"));
        camarero.setNombre(resultSet.getString("nombre"));
        return camarero;
    }

    @Override
    protected String generateFindByIdQuery() {
        return "SELECT * FROM " + CAMAREROS + " WHERE id_camarero = ?";
    }

    @Override
    protected void setPreparedStatementParameters(PreparedStatement preparedStatement, Camarero object) {
        try {
            Class<?> objectClass = object.getClass();
            Field[] fields = objectClass.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                Object value = field.get(object);
                preparedStatement.setObject(i, value);
            }
        } catch (IllegalAccessException | SQLException e) {
            System.err.println(e);
        }
    }

    @Override
    protected String generarInsertQuery(Camarero object) {
        String query = "INSERT INTO " + CAMAREROS + " (nombre) VALUES (?)";
        return query;
    }

    @Override
    protected String generarUpdateQuery(Camarero object) throws SQLException {
        String query = "UPDATE " + CAMAREROS + " SET nombre = ? WHERE id_camarero = ?";
        return query;
    }

    @Override
    protected String generarDeleteQuery() throws SQLException {
        return "DELETE FROM " + CAMAREROS + " WHERE id_camarero = ?";
    }

    @Override
    protected String generarCountQuery() throws SQLException {
        String query = "SELECT COUNT * FROM " + CAMAREROS;
        return query;
    }

}
