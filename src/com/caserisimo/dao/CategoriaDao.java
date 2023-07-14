package com.caserisimo.dao;

import com.caserisimo.modelo.Categoria;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class CategoriaDao extends Dao<Categoria> {
    private static final String NOMBRE_TABLA = "categorias";

    public CategoriaDao() {
        super(NOMBRE_TABLA);
    }

    @Override
    public void createTable() {
        Connection conexion = Session.getInstance().getConnection();
        try {
            Statement sentencia = conexion.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + NOMBRE_TABLA + " (" +
                    "id_categoria INTEGER PRIMARY KEY AUTOINCREMENT," +
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
    public ArrayList<Categoria> getAll() {
        ArrayList<Categoria> categorias = new ArrayList<>();
        Connection conexion = Session.getInstance().getConnection();
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultSet = sentencia.executeQuery("SELECT * FROM " + NOMBRE_TABLA);
            while (resultSet.next()) {
                int id = resultSet.getInt("id_categoria");
                String nombre = resultSet.getString("nombre");
                Categoria categoria = new Categoria(id, nombre);
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
        return categorias;
    }

    @Override
    public Categoria findById(int id) {
        Categoria categoria = null;
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM " + NOMBRE_TABLA + " WHERE id_categoria = ?");
            sentencia.setInt(1, id);
            ResultSet resultSet = sentencia.executeQuery();
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                categoria = new Categoria(id, nombre);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
        return categoria;
    }

    @Override
    public void create(Categoria object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO " + NOMBRE_TABLA + " (nombre) VALUES (?)");
            sentencia.setString(1, object.getNombre().toLowerCase());
            sentencia.executeUpdate();
            JOptionPane.showMessageDialog(null, "La categoría se ha creado con éxito");
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
    }

    @Override
    public void update(Categoria object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE " + NOMBRE_TABLA + " SET nombre = ? WHERE id_categoria = ?");
            sentencia.setString(1, object.getNombre().toLowerCase());
            sentencia.setInt(2, object.getIdCategoria());
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea modificar el nombre de la categoría?");
            if (respuesta == JOptionPane.YES_OPTION) {
                sentencia.executeUpdate();
                JOptionPane.showMessageDialog(null, "El nombre de la categoría ha sido modificado correctamente");
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
    public void delete(Categoria object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM " + NOMBRE_TABLA + " WHERE id_categoria = ?");
            sentencia.setInt(1, object.getIdCategoria());
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar la categoría?");
            if (respuesta == JOptionPane.YES_OPTION) {
                sentencia.executeUpdate();
                JOptionPane.showMessageDialog(null, "La categoría ha sido eliminada correctamente");
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
