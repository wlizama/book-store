/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author wilderlizama
 */
@Entity
@Table(name = "tipolista")
@NamedQueries({
    @NamedQuery(name = "Tipolista.findAll", query = "SELECT t FROM Tipolista t")})
public class Tipolista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipolista")
    private Integer idTipolista;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipolista")
    private List<Lista> listaList;

    public Tipolista() {
    }

    public Tipolista(Integer idTipolista) {
        this.idTipolista = idTipolista;
    }

    public Tipolista(Integer idTipolista, String nombre, String descripcion) {
        this.idTipolista = idTipolista;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdTipolista() {
        return idTipolista;
    }

    public void setIdTipolista(Integer idTipolista) {
        this.idTipolista = idTipolista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Lista> getListaList() {
        return listaList;
    }

    public void setListaList(List<Lista> listaList) {
        this.listaList = listaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipolista != null ? idTipolista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipolista)) {
            return false;
        }
        Tipolista other = (Tipolista) object;
        if ((this.idTipolista == null && other.idTipolista != null) || (this.idTipolista != null && !this.idTipolista.equals(other.idTipolista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Tipolista[ idTipolista=" + idTipolista + " ]";
    }
    
}
