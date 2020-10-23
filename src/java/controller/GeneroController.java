/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Genero;
import model.Libro;
import utils.DataConnect;

/**
 *
 * @author wilderlizama
 */
@ManagedBean
@RequestScoped
public class GeneroController {
    
    private Genero genero;

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    @PostConstruct
    public void init() {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
            
            if (paramMap.size() > 0) {
                String pId_genero = paramMap.get("id_genero");
                if (pId_genero != null && pId_genero != "") {
                    con = DataConnect.getConnection();
                    ps = con.prepareStatement("SELECT * FROM genero WHERE id_genero = ?");
                    ps.setInt(1, Integer.parseInt(pId_genero));

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        Genero g = new Genero(
                            rs.getInt("id_genero"),
                            rs.getString("nombre"),
                            rs.getString("descripcion")
                        );
                        setGenero(g);
                        
                    }
                }
                else {
                    setGenero(new Genero());
                }
            }
            else
                setGenero(new Genero());
        } catch (SQLException ex) {
            System.out.println("init error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
    }
    
    public List<Libro> daoListaLibrosXGenero() {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Libro> lLibros = new ArrayList<>(); 
        
        try {

            con = DataConnect.getConnection();
            cs = con.prepareCall("{call spListarLibrosPorGenero (?)}");
            cs.setInt(1, genero.getIdGenero());

            cs.execute();
            
            ResultSet rs = cs.getResultSet();

            while(rs.next()) {
                lLibros.add(new Libro(
                    rs.getInt("id_libro"),
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getDate("fecha_publicacion"),
                    rs.getBigDecimal("precio"),
                    rs.getString("url_portada"),
                    rs.getString("url_ubicacion")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("daoListaLibros error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
        
        return lLibros;
    }
    
    

    public List<Genero> daoListaGeneros() {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Genero> lgeneros = new ArrayList<>(); 
        
        try {

            con = DataConnect.getConnection();
            cs = con.prepareCall("{call spListarGenero}");

            cs.execute();
            
            ResultSet rs = cs.getResultSet();

            while(rs.next()) {
                lgeneros.add(new Genero(
                    rs.getInt("id_genero"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("daoListaGeneros error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
        
        return lgeneros;
    }
    
}
