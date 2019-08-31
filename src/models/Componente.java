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
public class Componente {
    private String descripcion;
    private int id;

    public Componente(String descripcion) {
        this.descripcion = descripcion;
    }

    public Componente() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%d - %s", id, descripcion);
    }
       
}
