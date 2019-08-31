/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author IVAN
 */
public class ProduccionOperario {
    private int codigoProduccion;
    private String nombreResponsable;
    private String fecha;
    private String descComponente;
    private int cantidadProduccion;
    private int codigoResponsable;
    private int codigoComponente;

    public ProduccionOperario() {
    }

    public int getCodigoProduccion() {
        return codigoProduccion;
    }

    public void setCodigoProduccion(int codigoProduccion) {
        this.codigoProduccion = codigoProduccion;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescComponente() {
        return descComponente;
    }

    public void setDescComponente(String descComponente) {
        this.descComponente = descComponente;
    }

    public int getCantidadProduccion() {
        return cantidadProduccion;
    }

    public void setCantidadProduccion(int cantidadProduccion) {
        this.cantidadProduccion = cantidadProduccion;
    }

    public int getCodigoResponsable() {
        return codigoResponsable;
    }

    public void setCodigoResponsable(int codigoResponsable) {
        this.codigoResponsable = codigoResponsable;
    }

    public int getCodigoComponente() {
        return codigoComponente;
    }

    public void setCodigoComponente(int codigoComponente) {
        this.codigoComponente = codigoComponente;
    }

    @Override
    public String toString() {
        return "Producci\u00f3nPorOperario{" + "codigoProduccion=" + codigoProduccion + ", nombreResponsable=" + nombreResponsable + ", fecha=" + fecha + ", descComponente=" + descComponente + ", cantidadProduccion=" + cantidadProduccion + ", codigoResponsable=" + codigoResponsable + ", codigoComponente=" + codigoComponente + '}';
    }
    
    
}
