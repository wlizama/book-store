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
import javax.servlet.http.HttpSession;
import model.Autor;
import model.Genero;
import model.Libro;
import model.LibroTipoLista;
import model.Tipolista;
import utils.DataConnect;
import utils.SessionManager;

/**
 *
 * @author wilderlizama
 */
@ManagedBean
@RequestScoped
public class UsuarioLibroController {
    
    private Libro libro;
    
    private LibroTipoLista libro_tipo_lista;

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public LibroTipoLista getLibro_tipo_lista() {
        return libro_tipo_lista;
    }

    public void setLibro_tipo_lista(LibroTipoLista libro_tipo_lista) {
        this.libro_tipo_lista = libro_tipo_lista;
    }
    
    
    
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
                        daoLibroTipoLista(Integer.parseInt(pId_libro));
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
            
            HttpSession session = SessionManager.getSession();
            String nickname = session.getAttribute("nickname").toString();

            con = DataConnect.getConnection();
            cs = con.prepareCall("{call spListarLibrosPorUsuario (?)}");
            cs.setString(1, nickname);
            
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
    
    public void daoLibroTipoLista(int id_libro) {
        Connection con = null;
        CallableStatement cs = null;
        
        try {
            
            HttpSession session = SessionManager.getSession();
            String nickname = session.getAttribute("nickname").toString();

            con = DataConnect.getConnection();
            cs = con.prepareCall("{call spTipoListaLibroPorUsuario (?, ?)}");
            cs.setString(1, nickname);
            cs.setInt(2, id_libro);
            
            cs.execute();
            
            ResultSet rs = cs.getResultSet();

            while(rs.next()) {
                setLibro_tipo_lista(new LibroTipoLista(
                    id_libro,
                    new Tipolista(
                        rs.getInt("id_tipolista"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                    )
                ));
            }
        } catch (SQLException ex) {
            System.out.println("daoListaLibros error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
    }
    
    
    public List<Tipolista> daoListaTiposLista() {
        Connection con = null;
        PreparedStatement ps = null;
        
        List<Tipolista> ltlista = new ArrayList<>(); 
        
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM tipolista");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tipolista tl = new Tipolista(
                    rs.getInt("id_tipolista"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                );
                ltlista.add(tl);
            }
        } catch (SQLException ex) {
            System.out.println("init error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
        
        return ltlista;
    }
    
}
