package com.caserisimo.dao;

import com.caserisimo.modelo.ServicioProducto;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ServicioProductoDao extends Dao<ServicioProducto> {
    private static final String NOMBRE_TABLA = "servicios_productos";

    public ServicioProductoDao() {
        super(NOMBRE_TABLA);
    }

    @Override
    public void createTable() {
        Connection conexion = Session.getInstance().getConnection();
        try {
            Statement sentencia = conexion.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + NOMBRE_TABLA + " (" +
                    "id_servicios_productos INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id_servicio INTEGER," +
                    "id_producto INTEGER," +
                    "cantidad INTEGER," +
                    "FOREIGN KEY (id_servicio) REFERENCES camareros (id_servicio)," +
                    "FOREIGN KEY (id_producto) REFERENCES mesas (id_producto)" +
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
    public ArrayList<ServicioProducto> getAll() {
        ArrayList<ServicioProducto> servicioProductos = new ArrayList<>();
        Connection conexion = Session.getInstance().getConnection();
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultSet = sentencia.executeQuery("SELECT * FROM " + NOMBRE_TABLA);
            while (resultSet.next()) {
                int id = resultSet.getInt("id_servicios_productos");
                int id_servicio = resultSet.getInt("id_servicio");
                int id_producto = resultSet.getInt("id_producto");
                int cantidad = resultSet.getInt("cantidad");
                ServicioProducto servicioProducto = new ServicioProducto(id, id_servicio, id_producto, cantidad);
                servicioProductos.add(servicioProducto);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
        return servicioProductos;
    }

    @Override
    public ServicioProducto findById(int id) {
        ServicioProducto servicioProducto = null;
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM " + NOMBRE_TABLA + " WHERE id_servicios_productos = ?");
            sentencia.setInt(1, id);
            ResultSet resultSet = sentencia.executeQuery();
            if (resultSet.next()) {
                int id_servicio = resultSet.getInt("id_servicio");
                int id_producto = resultSet.getInt("id_producto");
                int cantidad = resultSet.getInt("cantidad");
                servicioProducto = new ServicioProducto(id, id_servicio, id_producto, cantidad);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
        return servicioProducto;
    }

    @Override
    public void create(ServicioProducto object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO " + NOMBRE_TABLA + " (id_servicio, id_producto, cantidad) VALUES (?, ?, ?)");
            sentencia.setInt(1, object.getIdServicio());
            sentencia.setInt(2, object.getIdProducto());
            sentencia.setInt(3, object.getCantidad());
            sentencia.executeUpdate();
            JOptionPane.showMessageDialog(null, "El servicio-producto se ha creado con éxito");
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
    }

    @Override
    public void update(ServicioProducto object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE " + NOMBRE_TABLA + " SET id_servicio = ?, id_producto = ?, cantidad = ? WHERE id_servicios_productos = ?");
            sentencia.setInt(1, object.getIdServicio());
            sentencia.setInt(2, object.getIdProducto());
            sentencia.setInt(3, object.getCantidad());
            sentencia.setInt(4, object.getIdServicioProducto());
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea modificar los datos del servicio-producto?");
            if (respuesta == JOptionPane.YES_OPTION) {
                sentencia.executeUpdate();
                JOptionPane.showMessageDialog(null, "Los datos del servicio-producto han sido modificados correctamente");
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
    public void delete(ServicioProducto object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM " + NOMBRE_TABLA + " WHERE id_servicios_productos = ?");
            sentencia.setInt(1, object.getIdServicioProducto());
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el servicio-producto?");
            if (respuesta == JOptionPane.YES_OPTION) {
                sentencia.executeUpdate();
                JOptionPane.showMessageDialog(null, "El servicio-producto ha sido eliminado correctamente");
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
