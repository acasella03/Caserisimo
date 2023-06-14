package caserisimo.modelo;

public class Mesa {
    private int idMesa;
    private String nombre;

    public Mesa() {
    }

    public Mesa(int idMesa, String nombre) {
        this.idMesa = idMesa;
        this.nombre = nombre;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
