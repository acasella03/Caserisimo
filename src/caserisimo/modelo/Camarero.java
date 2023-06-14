package caserisimo.modelo;

public class Camarero {
    private int idCamarero;
    private String nombre;

    public Camarero() {
    }

    public Camarero(int idCamarero, String nombre) {
        this.idCamarero = idCamarero;
        this.nombre = nombre;
    }

    public int getIdCamarero() {
        return idCamarero;
    }

    public void setIdCamarero(int idCamarero) {
        this.idCamarero = idCamarero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
