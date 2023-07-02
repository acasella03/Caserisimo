package com.caserisimo.dao;

import com.caserisimo.modelo.Mesa;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class MesaDao extends Dao<Mesa>{
    private static final String NOMBRE_TABLA = "mesas";

    public MesaDao() {
        super(NOMBRE_TABLA);
    }

    @Override
    public void createTable() {
        Connection conexion = Session.getInstance().getConnection();
        try {
            Statement sentencia = conexion.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + NOMBRE_TABLA + " (" +
                    "id_mesa INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT" +
                    ")";
            sentencia.executeUpdate(query);
            System.out.println("Tabla " + NOMBRE_TABLA + " creada exitosamente.");
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
    }

    @Override
    public ArrayList<Mesa> getAll() {
        ArrayList<Mesa> mesas = new ArrayList<>();
        Connection conexion = Session.getInstance().getConnection();
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultSet = sentencia.executeQuery("SELECT * FROM " + NOMBRE_TABLA);
            while (resultSet.next()) {
                int id = resultSet.getInt("id_mesa");
                String nombre = resultSet.getString("nombre");
                Mesa mesa = new Mesa(id, nombre);
                mesas.add(mesa);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
        return mesas;
    }

    @Override
    public Mesa findById(int id) {
        Mesa mesa = null;
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM " + NOMBRE_TABLA + " WHERE id_mesa = ?");
            sentencia.setInt(1, id);
            ResultSet resultSet = sentencia.executeQuery();
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                mesa = new Mesa(id, nombre);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
        return mesa;
    }

    @Override
    public void create(Mesa object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO " + NOMBRE_TABLA + " (nombre) VALUES (?)");
            sentencia.setString(1, object.getNombre().toLowerCase());
            sentencia.executeUpdate();
            JOptionPane.showMessageDialog(null, "La mesa se ha creado con éxito");
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
    }

    @Override
    public void update(Mesa object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE " + NOMBRE_TABLA + " SET nombre = ? WHERE id_mesa = ?");
            sentencia.setString(1, object.getNombre().toLowerCase());
            sentencia.setInt(2, object.getIdMesa());
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea modificar el nombre de la mesa?");
            if (respuesta == JOptionPane.YES_OPTION) {
                sentencia.executeUpdate();
                JOptionPane.showMessageDialog(null, "El nombre de la mesa ha sido modificado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "La operación ha sido cancelada");
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
    }

    @Override
    public void delete(Mesa object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM " + NOMBRE_TABLA + " WHERE id_mesa = ?");
            sentencia.setInt(1, object.getIdMesa());
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar la mesa?");
            if (respuesta == JOptionPane.YES_OPTION) {
                sentencia.executeUpdate();
                JOptionPane.showMessageDialog(null, "La mesa ha sido eliminada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "La operación ha sido cancelada");
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
    }

    @Override
    public int count() {
        int rowCount = 0;
        Connection conexion = Session.getInstance().getConnection();
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultSet = sentencia.executeQuery("SELECT COUNT(*) as count FROM " + NOMBRE_TABLA);
            if (resultSet.next()) {
                rowCount = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
        return rowCount;
    }
}
