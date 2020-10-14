/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Author;

/**
 *
 * @author wilderlizama
 */
@ManagedBean(name = "authorController", eager = true)
@RequestScoped
public class AuthorController implements Serializable {

    public List<Author> getAuthors() {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = getConnection();
        String stm = "Select * from authors";
        List<Author> lstAuthors = new ArrayList<Author>();
        
        try {
            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();
            
            while (rs.next()) {
                Author author = new Author();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                lstAuthors.add(author);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return  lstAuthors;
    }
    
    public Connection getConnection() {
        Connection con = null;
        
        String url = "jdbc:mysql://192.168.0.6:3306/testdb";
        final String user = "wilder";
        final String password = "123456";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    
}
