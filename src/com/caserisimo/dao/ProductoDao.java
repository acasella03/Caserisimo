package com.caserisimo.dao;

import com.caserisimo.modelo.Producto;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ProductoDao extends Dao<Producto> {
    private static final String NOMBRE_TABLA = "productos";

    public ProductoDao() {
        super(NOMBRE_TABLA);
    }

    @Override
    public void createTable() {
        Connection conexion = Session.getInstance().getConnection();
        try {
            Statement sentencia = conexion.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + NOMBRE_TABLA + " (" +
                    "id_producto INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT," +
                    "precio FLOAT," +
                    "id_categoria INTEGER," +
                    "FOREIGN KEY (id_categoria) REFERENCES categorias (id_categoria)" +
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
    public ArrayList<Producto> getAll() {
        ArrayList<Producto> productos = new ArrayList<>();
        Connection conexion = Session.getInstance().getConnection();
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultSet = sentencia.executeQuery("SELECT * FROM " + NOMBRE_TABLA);
            while (resultSet.next()) {
                int id = resultSet.getInt("id_producto");
                String nombre = resultSet.getString("nombre");
                float precio = resultSet.getFloat("precio");
                int id_categoria = resultSet.getInt("id_categoria");
                Producto producto = new Producto(id, nombre, precio, id_categoria);
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
        return productos;
    }

    @Override
    public Producto findById(int id) {
        Producto producto = null;
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM " + NOMBRE_TABLA + " WHERE id_producto = ?");
            sentencia.setInt(1, id);
            ResultSet resultSet = sentencia.executeQuery();
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                float precio = resultSet.getFloat("precio");
                int id_categoria = resultSet.getInt("id_categoria");
                producto = new Producto(id, nombre, precio, id_categoria);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
        return producto;
    }

    @Override
    public void create(Producto object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO " + NOMBRE_TABLA + " (nombre, precio, id_categoria) VALUES (?, ?, ?)");
            sentencia.setString(1, object.getNombre().toLowerCase());
            sentencia.setFloat(2, object.getPrecio());
            sentencia.setInt(3, object.getIdCategoria());
            sentencia.executeUpdate();
            JOptionPane.showMessageDialog(null, "El producto se ha creado con éxito");
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
    }

    @Override
    public void update(Producto object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE " + NOMBRE_TABLA + " SET nombre = ?, precio = ?, id_categoria = ? WHERE id_producto = ?");
            sentencia.setString(1, object.getNombre().toLowerCase());
            sentencia.setFloat(2, object.getPrecio());
            sentencia.setInt(3, object.getIdCategoria());
            sentencia.setInt(4, object.getIdProducto());
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea modificar los datos del producto?");
            if (respuesta == JOptionPane.YES_OPTION) {
                sentencia.executeUpdate();
                JOptionPane.showMessageDialog(null, "Los datos del producto han sido modificados correctamente");
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
    public void delete(Producto object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM " + NOMBRE_TABLA + " WHERE id_producto = ?");
            sentencia.setInt(1, object.getIdProducto());
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el producto?");
            if (respuesta == JOptionPane.YES_OPTION) {
                sentencia.executeUpdate();
                JOptionPane.showMessageDialog(null, "El producto ha sido eliminado correctamente");
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
