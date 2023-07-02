package com.caserisimo.dao;

import com.caserisimo.modelo.Camarero;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class CamareroDao extends Dao<Camarero> {
    private static final String NOMBRE_TABLA = "camareros";

    public CamareroDao() {
        super(NOMBRE_TABLA);
    }

    @Override
    public void createTable() {
        Connection conexion = Session.getInstance().getConnection();
        try {
            Statement sentencia = conexion.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + NOMBRE_TABLA + " (" +
                    "id_camarero INTEGER PRIMARY KEY AUTOINCREMENT," +
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
    public ArrayList<Camarero> getAll() {
        ArrayList<Camarero> camareros = new ArrayList<>();
        Connection conexion = Session.getInstance().getConnection();
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultSet = sentencia.executeQuery("SELECT * FROM " + NOMBRE_TABLA);
            while (resultSet.next()) {
                int id = resultSet.getInt("id_camarero");
                String nombre = resultSet.getString("nombre");
                Camarero camarero = new Camarero(id, nombre);
                camareros.add(camarero);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
        return camareros;
    }

    @Override
    public Camarero findById(int id) {
        Camarero camarero = null;
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM " + NOMBRE_TABLA + " WHERE id_camarero = ?");
            sentencia.setInt(1, id);
            ResultSet resultSet = sentencia.executeQuery();
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                camarero = new Camarero(id, nombre);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
        return camarero;
    }

    @Override
    public void create(Camarero object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO " + NOMBRE_TABLA + " (nombre) VALUES (?)");
            sentencia.setString(1, object.getNombre().toLowerCase());
            sentencia.executeUpdate();
            JOptionPane.showMessageDialog(null, "El camarero se ha creado con éxito");
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
    }

    @Override
    public void update(Camarero object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE " + NOMBRE_TABLA + " SET nombre=? WHERE id_camarero=?");
            sentencia.setString(1, object.getNombre().toLowerCase());
            sentencia.setInt(2, object.getIdCamarero());
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea modificar el nombre del camarero?");
            if (respuesta == JOptionPane.YES_OPTION) {
                sentencia.executeUpdate();
                JOptionPane.showMessageDialog(null, "El nombre del camarero ha sido modificado correctamente");
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
    public void delete(Camarero object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM " + NOMBRE_TABLA + " WHERE id_camarero=?");
            sentencia.setInt(1, object.getIdCamarero());
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el camarero?");
            if (respuesta == JOptionPane.YES_OPTION) {
                sentencia.executeUpdate();
                JOptionPane.showMessageDialog(null, "El camarero ha sido eliminado correctamente");
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
