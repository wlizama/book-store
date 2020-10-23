/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.CantLibrosTipoLista;
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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Libro;
import model.Tipolista;
import utils.DataConnect;
import utils.SessionManager;

/**
 *
 * @author wilderlizama
 */
@ManagedBean
@SessionScoped
public class UsuarioListasController {
    
    private int id_tipolista;
    private Tipolista tipolista;

    public int getId_tipolista() {
        return id_tipolista;
    }

    public void setId_tipolista(int id_tipolista) {
        this.id_tipolista = id_tipolista;
    }

    public Tipolista getTipolista() {
        return tipolista;
    }

    public void setTipolista(Tipolista tipolista) {
        this.tipolista = tipolista;
    }
    
    
//    @PostConstruct
//    public void init() {
//        Connection con = null;
//        PreparedStatement ps = null;
//        
//        try {
//            FacesContext context = FacesContext.getCurrentInstance();
//            Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
//            
//            if (paramMap.size() > 0) {
//                String pId_libro = paramMap.get("id_libro");
//                if (pId_libro != null && pId_libro != "") {
//                    con = DataConnect.getConnection();
//                    ps = con.prepareStatement("SELECT * FROM libro WHERE id_libro = ?");
//                    ps.setString(1, pId_libro);
//
//                    ResultSet rs = ps.executeQuery();
//
//                    if (rs.next()) {
//                        Libro libro = new Libro(
//                            rs.getInt("id_libro"),
//                            rs.getString("titulo"),
//                            rs.getString("descripcion"),
//                            rs.getDate("fecha_publicacion"),
//                            rs.getBigDecimal("precio"),
//                            rs.getString("url_portada"),
//                            rs.getString("url_ubicacion")
//                        );
//                        setLibro(libro);
//                        
//                        daoListaGenerosXLibro(Integer.parseInt(pId_libro));
//                        daoListaAutoresXLibro(Integer.parseInt(pId_libro));
//                        daoListaComentariosXLibro(Integer.parseInt(pId_libro));
//                    }
//                }
//                else {
//                    setLibro(new Libro());
//                }
//            }
//            else
//                setLibro(new Libro());
//        } catch (SQLException ex) {
//            System.out.println("init error: " + ex.getMessage());
//        }
//        finally {
//            DataConnect.close(con);
//        }
//    }
    
    public void daoMostarPorTipolista() {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
            
            if (paramMap.size() > 0) {
                String pId_TipoLista = paramMap.get("id_tipolista");
                if (pId_TipoLista != null && pId_TipoLista != "") {
                    con = DataConnect.getConnection();
                    ps = con.prepareStatement("SELECT * FROM tipolista WHERE id_tipolista = ?");
                    ps.setString(1, pId_TipoLista);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        Tipolista tl = new Tipolista(
                            rs.getInt("id_tipolista"),
                            rs.getString("nombre"),
                            rs.getString("descripcion")
                        );
                        setTipolista(tl);
                        setId_tipolista(rs.getInt("id_tipolista"));
                    }
                }
                else {
                    setTipolista(new Tipolista());
                }
            }
            else
                setTipolista(new Tipolista());
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
            cs = con.prepareCall("{call spListarLibroPorUsuarioTLista (?, ?)}");
            cs.setString(1, nickname);
            cs.setInt(2, id_tipolista);
            
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

    public List<CantLibrosTipoLista> daoListaCantLibrosPorUsuario() {
        Connection con = null;
        CallableStatement cs = null;
        
        List<CantLibrosTipoLista> lcantL = new ArrayList<>(); 
        
        try {
            
            HttpSession session = SessionManager.getSession();
            String nickname = session.getAttribute("nickname").toString();

            con = DataConnect.getConnection();
            cs = con.prepareCall("{call spListarCantLibrosListaPorUsuario (?)}");
            cs.setString(1, nickname);

            cs.execute();
            
            ResultSet rs = cs.getResultSet();

            while(rs.next()) {
                lcantL.add(new CantLibrosTipoLista(
                    rs.getInt("cant_lista"),
                    new Tipolista(
                        rs.getInt("id_tipolista"),
                        rs.getString("nombre"),
                        null
                    )
                ));
            }
        } catch (SQLException ex) {
            System.out.println("daoListaCantLibrosPorUsuario error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
        
        return lcantL;
    }
    
}
