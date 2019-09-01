/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import dto.ProduccionOperario;
import java.util.ArrayList;
import java.sql.*;
import models.*;

/**
 *
 * @author IVAN
 */
public class Gestor {
    Connection con;
    String url, user, pass;
    
    private void Conectar() {
       
        try {
            url = "jdbc:sqlserver://localhost:1433;databaseName=Produccion";
            user = "sa";
            pass = "maximo";
            
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.print("Error:" + e.getMessage());
        }
        
    }
    
    private void Desconectar() {
       try {
           if(!con.isClosed()) {
               con.close();
           }
       } 
       catch (SQLException e) {
           System.out.print("Error:" + e.getMessage());
       }
    }
    
    public ArrayList<Componente> obtenerComponentes() {
        String sql = "SELECT * FROM Componentes";      
        ArrayList<Componente> lista = new ArrayList<>();
        
        try {
            Conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                int codigo = rs.getInt("codigo");
                String descripcion = rs.getString("descripcion");
                
                Componente c = new Componente(descripcion);
                c.setId(codigo);
                
                lista.add(c);
            }
            
        } catch (SQLException e) {
            System.out.print("Error:" + e.getMessage());
        } finally {
            Desconectar();
        }
               
        return lista;
    }
    
    public ArrayList<Responsable> obtenerResponsables() {
        String sql = "SELECT * FROM Responsables";
        ArrayList<Responsable> lista = new ArrayList<>();
        
        try {
            Conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                
                Responsable r = new Responsable(nombre);
                r.setId(codigo);
                
                lista.add(r);
            }
        }
        catch (SQLException e) {
            System.out.print("Error:" + e.getMessage());
        }
        finally {
            Desconectar();
        }
        return lista;
    }
    
    public ArrayList<Produccion> obtenerProduccion() {
        String sql = "SELECT * FROM Produccion";
        ArrayList<Produccion> lista = new ArrayList<>();
        
        try {
            Conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                int codigo = rs.getInt("codigoProduccion");
                String fecha = rs.getString("fecha");
                int cantidad = rs.getInt("cantidadProduccion");
                int codigoResponsable = rs.getInt("codigoResponsable");
                int codigoComponente = rs.getInt("codigoComponente");
                
                Componente c = new Componente();
                c.setId(codigoComponente);
                
                Responsable r = new Responsable();
                r.setId(codigoResponsable);
                
                Produccion p = new Produccion(fecha, cantidad, c, r); 
                p.setId(codigo);
                
                lista.add(p);                
            }
        } catch (SQLException e) {
            System.out.print("Error:" + e.getMessage());
        }
        finally {
            Desconectar();
        }
        return lista;
    }
    
    public boolean insertarProduccion(Produccion p) {
        boolean inserto = false;
        
        try {
            Conectar();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Produccion (fecha, cantidadProduccion, codigoResponsable, codigoComponente) VALUES (?,?,?,?)");
            ps.setString(1, p.getFecha());
            ps.setInt(2, p.getCantidad());
            ps.setInt(3, p.getResponsable().getId());
            ps.setInt(4, p.getComponente().getId());
            
            ps.execute();
            inserto = true;
        }
        catch (SQLException e) {
            System.out.print("Error:" + e.getMessage());
        } finally {
            Desconectar();
        }
        
        return inserto;
    }
    
    public boolean actualizarProduccion(Produccion p) {
        boolean actualizado = false;
        
        try {
            Conectar();
            PreparedStatement ps = con.prepareStatement("UPDATE Produccion SET fecha = ?, cantidadProduccion = ?, codigoResponsable = ?, codigoComponente = ? WHERE codigoProduccion = ?");
            ps.setString(1, p.getFecha());
            ps.setInt(2, p.getCantidad());
            ps.setInt(3, p.getResponsable().getId());
            ps.setInt(4, p.getComponente().getId());
            ps.setInt(5, p.getId());
            
            ps.execute();
            actualizado = true;
        } catch (SQLException e) {
            System.out.print("Error:" + e.getMessage());
        } finally {
            Desconectar();
        }
        
        return actualizado;
    }
    
    public boolean borrarProduccion(int codigo) {
        boolean borrado = false;
        
        try {
           Conectar();
           PreparedStatement ps = con.prepareStatement("DELETE FROM Produccion WHERE codigoProduccion = ?");
           ps.setInt(1, codigo);
           ps.execute();
           borrado = true;
           
        } catch (SQLException e) {
            System.out.print("Error:" + e.getMessage());
        } finally {
            Desconectar();
        }
        
        return borrado;
    }
    
    public ArrayList<ProduccionOperario> obtenerProduccionOperario(int codigo) {
        ArrayList<ProduccionOperario> lista = new ArrayList<>();
        String sql = "SELECT p.fecha, r.codigo AS CodigoOperario, r.nombre AS Operario, p.codigoProduccion, p.cantidadProduccion AS Cantidad, c.codigo AS CodigoComponente, c.descripcion AS Componente FROM Responsables r, Produccion p, Componentes c WHERE p.codigoResponsable = r.codigo AND p.codigoComponente = c.codigo AND r.codigo = ?";
                
        try {
            Conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                int codigoProduccion = rs.getInt("codigoProduccion");
                String fecha = rs.getString("fecha");
                int codigoResponsable = rs.getInt("codigoOperario");
                String responsable = rs.getString("Operario");
                int cantidad = rs.getInt("Cantidad");
                int codigoComponente = rs.getInt("CodigoComponente");
                String componente = rs.getString("Componente");
                 /*private int codigoProduccion;
                private String nombreResponsable;
                private String fecha;
                private String descComponente;
                private int cantidadProduccion;
                private int codigoResponsable;
                private int codigoComponente;*/
                
                ProduccionOperario po = new ProduccionOperario();
                
                po.setCodigoProduccion(codigoProduccion);
                po.setCodigoComponente(codigoComponente);
                po.setCodigoResponsable(codigoResponsable);
                po.setFecha(fecha);
                po.setDescComponente(componente);
                po.setNombreResponsable(responsable);
                po.setCantidadProduccion(cantidad);
                
                lista.add(po);
                
            }
        } catch (SQLException e) {
            System.out.print("Error:" + e.getMessage());
        } finally {
            Desconectar();
        }
        
        return lista;
    }
    
    public ArrayList<ProduccionOperario> totalOperario() {
        ArrayList<ProduccionOperario> lista = new ArrayList<>();
        String sql = "SELECT r.nombre as Responsable, SUM(p.cantidadProduccion) AS 'ProduccionTotal' FROM Responsables r, Produccion p WHERE p.codigoResponsable = r.codigo GROUP BY r.nombre ORDER BY 2 DESC";
        
        try {
            Conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                String nombre = rs.getString("Responsable");
                int cantidad = rs.getInt("ProduccionTotal");
                
                ProduccionOperario po = new ProduccionOperario();
                po.setNombreResponsable(nombre);
                po.setCantidadProduccion(cantidad);
                
                lista.add(po);
            }
            
        } catch (SQLException e) {
            System.out.print("Error:" + e.getMessage());
        } finally {
            Desconectar();
        }
            return lista;
    }
    
    public ArrayList<ProduccionOperario> totalComponente() {
        ArrayList<ProduccionOperario> lista = new ArrayList<>();
        String sql = "SELECT c.descripcion as Componente, SUM(p.cantidadProduccion) AS ProduccionTotal FROM Componentes c, Produccion p WHERE p.codigoComponente = c.codigo GROUP BY c.descripcion ORDER BY 2 DESC";
        
        try {
            Conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                String descripcion = rs.getString("Componente");
                int cantidad = rs.getInt("ProduccionTotal");
                
                ProduccionOperario po = new ProduccionOperario();
                po.setDescComponente(descripcion);
                po.setCantidadProduccion(cantidad);
                
                lista.add(po);
            }
        } catch (SQLException e) {
            System.out.print("Error:" + e.getMessage());
        } finally {
            Desconectar();
        }
                
        return lista;
    }
    
    public ArrayList<ProduccionOperario> masDe100Unidades() {
        ArrayList<ProduccionOperario> lista = new ArrayList<>();
        String sql = "SELECT r.nombre, c.descripcion, p.fecha, p.cantidadProduccion FROM Responsables r, Componentes c, Produccion p WHERE p.codigoComponente = c.codigo AND p.codigoResponsable = r.codigo AND p.cantidadProduccion > 100";
        
        try {
            Conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                String nombre = rs.getString(1);
                String descripcion = rs.getString(2);
                String fecha = rs.getString(3);
                int cantidad = rs.getInt(4);
                
                ProduccionOperario po = new ProduccionOperario();
                po.setNombreResponsable(nombre);
                po.setDescComponente(descripcion);
                po.setFecha(fecha);
                po.setCantidadProduccion(cantidad);
                
                lista.add(po);
            }
            
        } catch (SQLException e) {
            System.out.print("Error:" + e.getMessage());
        } finally {
            Desconectar();
        }
        return lista;
    }
    
    public float obtenerPromedio() {
        float promedio = 0.0f;
        String sql = "SELECT AVG(cantidadProduccion) AS Promedio FROM Produccion";
        
        try {
            Conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if (rs.next()) {
                promedio = rs.getFloat("Promedio");
            }
        } catch (SQLException e) {
            System.out.print("Error:" + e.getMessage());
        } finally {
            Desconectar();
        }
        return promedio;
    }
    
    public float obtenerPorcentaje() {
        float porcentaje = 0.0f;
        String sql = "SELECT COUNT(cantidadProduccion) * 100.0 / (SELECT COUNT(*) FROM Produccion) AS Porcentaje FROM Produccion WHERE cantidadProduccion < 10";
        
        try {
            Conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                porcentaje = rs.getFloat("Porcentaje");
            }
        } catch (SQLException e) {
            System.out.print("Error:" + e.getMessage());
        } finally {
            Desconectar();
        }
        return porcentaje;
    }
    
}
