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
import model.Autor;
import model.Genero;
import model.Libro;
import utils.DataConnect;

/**
 *
 * @author wilderlizama
 */
@ManagedBean
@RequestScoped
public class AutorController {
    
    private Autor autor;

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
    
    @PostConstruct
    public void init() {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
            
            if (paramMap.size() > 0) {
                String pId_autor = paramMap.get("id_autor");
                if (pId_autor != null && pId_autor != "") {
                    con = DataConnect.getConnection();
                    ps = con.prepareStatement("SELECT * FROM autor WHERE id_autor = ?");
                    ps.setInt(1, Integer.parseInt(pId_autor));

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        Autor g = new Autor(
                            rs.getInt("id_autor"),
                            rs.getString("nombres"),
                            rs.getString("alias")
                        );
                        setAutor(g);
                        
                    }
                }
                else {
                    setAutor(new Autor());
                }
            }
            else
                setAutor(new Autor());
        } catch (SQLException ex) {
            System.out.println("init error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
    }
    
    
    
    public List<Libro> daoListaLibrosXAutor() {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Libro> lLibros = new ArrayList<>(); 
        
        try {

            con = DataConnect.getConnection();
            cs = con.prepareCall("{call spListarLibrosPorAutor (?)}");
            cs.setInt(1, autor.getIdAutor());

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
            System.out.println("daoListaLibrosXAutor error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
        
        return lLibros;
    }

    
    public List<Autor> daoListaAutores() {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Autor> lautores = new ArrayList<>(); 
        
        try {

            con = DataConnect.getConnection();
            cs = con.prepareCall("{call spListarAutor}");

            cs.execute();
            
            ResultSet rs = cs.getResultSet();

            while(rs.next()) {
                lautores.add(new Autor(
                    rs.getInt("id_autor"),
                    rs.getString("nombres"),
                    rs.getString("alias")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("daoListaAutores error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
        
        return lautores;
    }
}
