/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author IVAN
 */
public class Produccion {
    String fecha;
    int cantidad;
    Componente componente;
    Responsable responsable;
    private int id;

    public Produccion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
      
    public Produccion(String fecha, int cantidad, Componente componente, Responsable responsable) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.componente = componente;
        this.responsable = responsable;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "Produccion{" + "fecha=" + fecha + ", cantidad=" + cantidad + ", componente=" + componente + ", responsable=" + responsable + '}';
    }
    
    
}
