package com.caserisimo.dao;

import com.caserisimo.modelo.Servicio;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ServicioDao extends Dao<Servicio> {
    private static final String NOMBRE_TABLA = "servicios";

    public ServicioDao() {
        super(NOMBRE_TABLA);
    }
    @Override
    public void createTable() {
        Connection conexion = Session.getInstance().getConnection();
        try {
            Statement sentencia = conexion.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + NOMBRE_TABLA + " (" +
                    "id_servicio INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "inicio TEXT," +
                    "fin TEXT," +
                    "id_camarero INTEGER," +
                    "id_mesa INTEGER," +
                    "FOREIGN KEY (id_camarero) REFERENCES camareros (id_camarero)," +
                    "FOREIGN KEY (id_mesa) REFERENCES mesas (id_mesa)" +
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
    public ArrayList<Servicio> getAll() {
        ArrayList<Servicio> servicios = new ArrayList<>();
        Connection conexion = Session.getInstance().getConnection();
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultSet = sentencia.executeQuery("SELECT * FROM " + NOMBRE_TABLA);
            while (resultSet.next()) {
                int id = resultSet.getInt("id_servicio");
                String inicio = resultSet.getString("inicio");
                String fin = resultSet.getString("fin");
                int id_camarero = resultSet.getInt("id_camarero");
                int id_mesa = resultSet.getInt("id_mesa");
                Servicio servicio = new Servicio(id, inicio, fin, id_camarero, id_mesa);
                servicios.add(servicio);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
        return servicios;
    }

    @Override
    public Servicio findById(int id) {
        Servicio servicio = null;
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM " + NOMBRE_TABLA + " WHERE id_servicio = ?");
            sentencia.setInt(1, id);
            ResultSet resultSet = sentencia.executeQuery();
            if (resultSet.next()) {
                String inicio = resultSet.getString("inicio");
                String fin = resultSet.getString("fin");
                int id_camarero = resultSet.getInt("id_camarero");
                int id_mesa = resultSet.getInt("id_mesa");
                servicio = new Servicio(id, inicio, fin, id_camarero, id_mesa);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
        return servicio;
    }

    @Override
    public void create(Servicio object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO " + NOMBRE_TABLA + " (inicio, fin, id_camarero, id_mesa) VALUES (?, ?, ?, ?)");
            sentencia.setString(1, object.getInicio());
            sentencia.setString(2, object.getFin());
            sentencia.setInt(3, object.getIdCamarero());
            sentencia.setInt(4, object.getIdMesa());
            sentencia.executeUpdate();
            JOptionPane.showMessageDialog(null, "El servicio se ha creado con éxito");
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            Session.getInstance().closeConnection(conexion);
        }
    }

    @Override
    public void update(Servicio object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE " + NOMBRE_TABLA + " SET inicio = ?, fin = ?, id_camarero = ?, id_mesa = ? WHERE id_servicio = ?");
            sentencia.setString(1, object.getInicio());
            sentencia.setString(2, object.getFin());
            sentencia.setInt(3, object.getIdCamarero());
            sentencia.setInt(4, object.getIdMesa());
            sentencia.setInt(5, object.getIdServicio());
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea modificar los datos del servicio?");
            if (respuesta == JOptionPane.YES_OPTION) {
                sentencia.executeUpdate();
                JOptionPane.showMessageDialog(null, "Los datos del servicio han sido modificados correctamente");
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
    public void delete(Servicio object) {
        Connection conexion = Session.getInstance().getConnection();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM " + NOMBRE_TABLA + " WHERE id_servicio = ?");
            sentencia.setInt(1, object.getIdServicio());
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el servicio?");
            if (respuesta == JOptionPane.YES_OPTION) {
                sentencia.executeUpdate();
                JOptionPane.showMessageDialog(null, "El servicio ha sido eliminado correctamente");
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
