/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.usuario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Libro;
import utils.DataConnect;

/**
 *
 * @author wilderlizama
 */
@ManagedBean
@RequestScoped
public class LibroController {

    private String listaLibrosOrden = "DESC";

    public String getListaLibrosOrden() {
        return listaLibrosOrden;
    }

    public void setListaLibrosOrden(String listaLibrosOrden) {
        this.listaLibrosOrden = listaLibrosOrden;
    }
    
    
    // validar si ya existe nickname en BD
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

            if (rs.next()) {
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
            }
        } catch (SQLException ex) {
            System.out.println("daoListaLibros error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
        
        return lLibros;
    }
    
}
