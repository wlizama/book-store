/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Usuario;
import utils.DataConnect;
import utils.SessionManager;

/**
 *
 * @author wilderlizama
 */
@ManagedBean
@RequestScoped
public class UsuarioController {

    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @PostConstruct
    public void init() {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            
            HttpSession session = SessionManager.getSession();
            String nickname = session.getAttribute("nickname").toString();
            
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT nickname, nombres, apellidos FROM usuario WHERE nickname = ?");
            ps.setString(1, nickname);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usu = new Usuario();
                usu.setNickname(rs.getString("nickname"));
                usu.setNombres(rs.getString("nombres"));
                usu.setApellidos(rs.getString("apellidos"));
                setUsuario(usu);
            }
        } catch (SQLException ex) {
            System.out.println("daoDatosUsuario error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
    }
}
