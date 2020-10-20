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

    // inicializar los datos del usuario si 
    // existe la variable de sesión nickname
    @PostConstruct
    public void init() {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            HttpSession session = SessionManager.getSession();
            if (session.getAttribute("nickname") != null) {
                String nickname = session.getAttribute("nickname").toString();
                
                if (!nickname.equals("")) {
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
                }
            }
            else {
                setUsuario(new Usuario());
            }
        } catch (SQLException ex) {
            System.out.println("init error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
    }
    
    // validar si ya existe nickname en BD
    private boolean daoValidarExisteNickname(String nickname) {
        Connection con = null;
        PreparedStatement ps = null;
        
        boolean existNickname = false; 
        
        try {

            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT nickname FROM usuario WHERE nickname = ?");
            ps.setString(1, nickname);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) existNickname = true;
        } catch (SQLException ex) {
            System.out.println("daoValidarNickname error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
        
        return existNickname;
    }
    
    public String daoRegistrarUsuario() {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            String nickname = this.usuario.getNickname().trim();
            if (!nickname.equals("")) {
                if (!daoValidarExisteNickname(nickname)) {
                    con = DataConnect.getConnection();
                    ps = con.prepareStatement("INSERT INTO usuario VALUES(0, ?, ?, ?, ?)");
                    ps.setString(1, this.usuario.getNombres().trim());
                    ps.setString(2, this.usuario.getApellidos().trim());
                    ps.setString(3, nickname);
                    ps.setString(4, this.usuario.getPassword().trim());

                   int rows = ps.executeUpdate();
                   if (rows > 0) 
                       return "login?faces-redirect=true";
                   else {
                        FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Sucedio un eror ingresando datos",
                            "Usuario no registrado"));
                       return "registro";
                   }
                }
                else {
                    FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Ese nickname ya esta siendo usado",
                            "Digite otro nickname"));
                       return "registro";
                }
            }
            else {
                FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Debe ingresar el nickname",
                    "El valor \"nickname\" es obligatorio"));
                
                return "registro";
            }
        } catch (SQLException ex) {
            System.out.println("daoValidarNickname error: " + ex.getMessage());
        }
        finally {
            DataConnect.close(con);
        }
        
        return null;
    }
}
