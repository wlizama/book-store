/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import utils.DataConnect;
import utils.SessionManager;

/**
 *
 * @author wilderlizama
 */
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 5094805825228386363L;
	
    private String password;
    private String msg;
    private String nickname;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    private static boolean daoValidateUsuario(String nickname, String password) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT nickname, password FROM usuario WHERE nickname = ? and password = ?");
            ps.setString(1, nickname);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
                    return true;
        } catch (SQLException ex) {
            System.out.println("Login error: " + ex.getMessage());
            return false;
        }
        finally {
            DataConnect.close(con);
        }
        return false;
    }

    //validate login
    public String validateUsernamePassword() {
        boolean valid = daoValidateUsuario(nickname, password);
        System.out.println("valid:" + valid);
        if (valid) {
            HttpSession session = SessionManager.getSession();
            session.setAttribute("nickname", nickname);
            return "home?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Usuario o contraseña incorrecto",
                    "Por favor ingresar datos correctos"));
            return "login";
        }
    }

    //logout event, invalidate session
    public String logout() {
            HttpSession session = SessionManager.getSession();
            session.invalidate();
            return "login";
    }
    
}
