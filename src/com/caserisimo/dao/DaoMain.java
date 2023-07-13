package com.caserisimo.dao;

import com.caserisimo.modelo.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DaoMain {
    public static void main(String[] args) {
        //CamareroDao camareroDao = new CamareroDao();
        //Camarero camarero1 = new Camarero();
        //camarero.createTable();
        //camarero1.setNombre("Pepe");
        //camareroDao.create(camarero1);
        //System.out.println(camareroDao.findById(4).getNombre());
        //Camarero camareroEncontrado=camareroDao.findById(4);
        //System.out.println(camareroEncontrado.getNombre());
        //camareroEncontrado.setNombre("Francisco");
        //camareroDao.update(camareroEncontrado);
        //System.out.println(camareroEncontrado.getNombre());
        //camareroDao.delete(camareroEncontrado);
        //System.out.println(camareroDao.count());

        //CategoriaDao categoriaDao=new CategoriaDao();
        //Categoria categoria=new Categoria();
        //categoriaDao.createTable();
        //categoria.setNombre("Bedida alcoholica");
        //categoriaDao.create(categoria);
        //System.out.println(categoriaDao.findById(4).getNombre());
        //Categoria categoriaEncontrada=categoriaDao.findById(4);
        //System.out.println(categoriaEncontrada.getNombre());
        //categoriaEncontrada.setNombre("bebida alcohol.");
        //categoriaDao.update(categoriaEncontrada);
        //System.out.println(categoriaEncontrada.getNombre());
        //categoriaDao.delete(categoriaEncontrada);
        //System.out.println(categoriaDao.count());

        //MesaDao mesaDao=new MesaDao();
        //Mesa mesa=new Mesa();
        //mesaDao.createTable();
        //mesa.setNombre("4");
        //mesaDao.create(mesa);
        //System.out.println(mesaDao.findById(4).getNombre());
        //Mesa mesaEncontrada=mesaDao.findById(4);
        //System.out.println(mesaEncontrada.getNombre());
        //mesaEncontrada.setNombre("5");
        //mesaDao.update(mesaEncontrada);
        //System.out.println(mesaEncontrada.getNombre());
        //mesaDao.delete(mesaEncontrada);
        //System.out.println(mesaDao.count());

        //ProductoDao productoDao=new ProductoDao();
        //Producto producto=new Producto();
        //productoDao.createTable();
        //producto.setNombre("Dorada Frita con Patatas Fritas");
        //producto.setPrecio(15.8f);
        //producto.setIdCategoria(2);
        //productoDao.create(producto);
        //System.out.println(productoDao.findById(4).getNombre());
        //Producto productoEncontrado=productoDao.findById(5);
        //System.out.println(productoEncontrado.getNombre());
        //productoEncontrado.setNombre("dorada y patatas");
        //productoEncontrado.setPrecio(15.00f);
        //productoDao.update(productoEncontrado);
        //System.out.println(productoEncontrado.getNombre());
        //System.out.println(productoEncontrado.getPrecio());
        //System.out.println(productoEncontrado.getIdCategoria());
        //productoDao.delete(productoEncontrado);
        //System.out.println(productoDao.count());

        ServicioDao servicioDao=new ServicioDao();
        Servicio servicio=new Servicio();
        //servicioDao.createTable();
        /*
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date fechaInicio = null;
        Date fechaFin = null;
        try {
            fechaInicio = formatter.parse("13-07-2023 19:00");
            fechaFin = formatter.parse("13-07-2023 21:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        servicio.setInicio(String.valueOf(fechaInicio));
        servicio.setFin(String.valueOf(fechaFin));
        servicio.setIdCamarero(1);
        servicio.setIdMesa(1);
        servicioDao.create(servicio);
        */
        /*
        System.out.println(servicioDao.findById(4).getInicio());
        System.out.println(servicioDao.findById(4).getFin());
        System.out.println(servicioDao.findById(4).getIdCamarero());
        System.out.println(servicioDao.findById(4).getIdMesa());
         */
        //Servicio servicioEncontrado=servicioDao.findById(4);
        /*
        System.out.println(servicioEncontrado.getInicio());
        System.out.println(servicioEncontrado.getFin());
        System.out.println(servicioEncontrado.getIdCamarero());
        System.out.println(servicioEncontrado.getIdMesa());
        servicioEncontrado.setInicio("13-07-2023 13:00");
        servicioEncontrado.setFin("13-07-2023 15:00");
        servicioEncontrado.setIdCamarero(3);
        servicioEncontrado.setIdMesa(3);
        servicioDao.update(servicioEncontrado);
        System.out.println(servicioEncontrado.getInicio());
        System.out.println(servicioEncontrado.getFin());
        System.out.println(servicioEncontrado.getIdCamarero());
        System.out.println(servicioEncontrado.getIdMesa());
         */
        //servicioDao.delete(servicioEncontrado);
        //System.out.println(servicioDao.count());

    }
}
