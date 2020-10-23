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
import model.Comentario;
import model.Genero;
import model.Libro;
import model.Usuario;
import utils.DataConnect;

/**
 *
 * @author wilderlizama
 */
@ManagedBean
@RequestScoped
public class LibroController {

    private Libro libro;
    private String listaLibrosOrden = "DESC";

    
    public String getListaLibrosOrden() {
        return listaLibrosOrden;
    }

    public void setListaLibrosOrden(String listaLibrosOrden) {
        this.listaLibrosOrden = listaLibrosOrden;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    

    // inicializar los datos del libro si 
    // se pasa por url la variable id_libro
    @PostConstruct
    public void init() {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
            
            if (paramMap.size() > 0) {
                String pId_libro = paramMap.get("id_libro");
                if (pId_libro != null && pId_libro != "") {
                    con = DataConnect.getConnection();
                    ps = con.prepareStatement("SELECT * FROM libro WHERE id_libro = ?");
                    ps.setString(1, pId_libro);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        Libro libro = new Libro(
                            rs.getInt("id_libro"),
                            rs.getString("titulo"),
                            rs.getString("descripcion"),
                            rs.getDate("fecha_publicacion"),
                            rs.getBigDecimal("precio"),
                            rs.getString("url_portada"),
                            rs.getString("url_ubicacion")
                        );
                        setLibro(libro);
                        
                        daoListaGenerosXLibro(Integer.parseInt(pId_libro));
                        daoListaAutoresXLibro(Integer.parseInt(pId_libro));
                        daoListaComentariosXLibro(Integer.parseInt(pId_libro));
                    }
                }
                else {
                    setLibro(new Libro());
                }
            }
            else
                setLibro(new Libro());
        } catch (SQLException ex) {
            System.out.println("init error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
    }
    
    
    public List<Libro> daoListaLibros() {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Libro> lLibros = new ArrayList<>(); 
        
        try {

            con = DataConnect.getConnection();
            cs = con.prepareCall("{call spListarLibroPorOrden (?)}");
            cs.setString(1, listaLibrosOrden);

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
    
    public void daoListaGenerosXLibro(int id_libro) {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Genero> lgeneros = new ArrayList<>(); 
        
        try {

            con = DataConnect.getConnection();
            cs = con.prepareCall("{call spListarGeneroPorLibro (?)}");
            cs.setInt(1, id_libro);

            cs.execute();
            
            ResultSet rs = cs.getResultSet();

            while(rs.next()) {
                lgeneros.add(new Genero(
                    rs.getInt("id_genero"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                ));
            }
            libro.setGeneroList(lgeneros);
        } catch (SQLException ex) {
            System.out.println("daoListaGenerosXLibro error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
    }
    
    
    public void daoListaAutoresXLibro(int id_libro) {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Autor> lautores = new ArrayList<>(); 
        
        try {

            con = DataConnect.getConnection();
            cs = con.prepareCall("{call spListarAutorPorLibro (?)}");
            cs.setInt(1, id_libro);

            cs.execute();
            
            ResultSet rs = cs.getResultSet();

            while(rs.next()) {
                lautores.add(new Autor(
                    rs.getInt("id_autor"),
                    rs.getString("nombres"),
                    rs.getString("alias")
                ));
            }
            libro.setAutorList(lautores);
        } catch (SQLException ex) {
            System.out.println("daoListaAutoresXLibro error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
    }
    
    public void daoListaComentariosXLibro(int id_libro) {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Comentario> lcomentario = new ArrayList<>(); 
        
        try {

            con = DataConnect.getConnection();
            cs = con.prepareCall("{call spListarComentarioPorLibro (?)}");
            cs.setInt(1, id_libro);

            cs.execute();
            
            ResultSet rs = cs.getResultSet();

            while(rs.next()) {
                Comentario comentario = new Comentario(
                    rs.getInt("id_comentario"),
                    rs.getString("comentario"),
                    rs.getDate("fecha"),
                    rs.getBigDecimal("puntuacion")
                );
                comentario.setIdUsuario(new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("nickname"),
                    null
                ));
                
                lcomentario.add(comentario);
            }
            libro.setComentarioList(lcomentario);
        } catch (SQLException ex) {
            System.out.println("daoListaComentariosXLibro error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
    }
    
}
