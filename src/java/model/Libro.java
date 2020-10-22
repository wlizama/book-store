/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author wilderlizama
 */
@Entity
@Table(name = "libro")
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l")})
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_libro")
    private Integer idLibro;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "fecha_publicacion")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "precio")
    private BigDecimal precio;
    @Basic(optional = false)
    @Lob
    @Column(name = "url_portada")
    private String urlPortada;
    @Basic(optional = false)
    @Lob
    @Column(name = "url_ubicacion")
    private String urlUbicacion;
    @JoinTable(name = "libro_genero", joinColumns = {
        @JoinColumn(name = "id_libro", referencedColumnName = "id_libro")}, inverseJoinColumns = {
        @JoinColumn(name = "id_genero", referencedColumnName = "id_genero")})
    @ManyToMany
    private List<Genero> generoList;
    @JoinTable(name = "libro_autor", joinColumns = {
        @JoinColumn(name = "id_libro", referencedColumnName = "id_libro")}, inverseJoinColumns = {
        @JoinColumn(name = "id_autor", referencedColumnName = "id_autor")})
    @ManyToMany
    private List<Autor> autorList;
    @JoinTable(name = "lista_libro", joinColumns = {
        @JoinColumn(name = "id_libro", referencedColumnName = "id_libro")}, inverseJoinColumns = {
        @JoinColumn(name = "id_lista", referencedColumnName = "id_lista")})
    @ManyToMany
    private List<Lista> listaList;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private Estado idEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLibro")
    private List<Comentario> comentarioList;

    public Libro() {
    }

    public Libro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public Libro(Integer idLibro, String titulo, String descripcion, Date fechaPublicacion, BigDecimal precio, String urlPortada, String urlUbicacion) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.precio = precio;
        this.urlPortada = urlPortada;
        this.urlUbicacion = urlUbicacion;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getUrlPortada() {
        return urlPortada;
    }

    public void setUrlPortada(String urlPortada) {
        this.urlPortada = urlPortada;
    }

    public String getUrlUbicacion() {
        return urlUbicacion;
    }

    public void setUrlUbicacion(String urlUbicacion) {
        this.urlUbicacion = urlUbicacion;
    }

    public List<Genero> getGeneroList() {
        return generoList;
    }

    public void setGeneroList(List<Genero> generoList) {
        this.generoList = generoList;
    }

    public List<Autor> getAutorList() {
        return autorList;
    }

    public void setAutorList(List<Autor> autorList) {
        this.autorList = autorList;
    }

    public List<Lista> getListaList() {
        return listaList;
    }

    public void setListaList(List<Lista> listaList) {
        this.listaList = listaList;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLibro != null ? idLibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.idLibro == null && other.idLibro != null) || (this.idLibro != null && !this.idLibro.equals(other.idLibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Libro[ idLibro=" + idLibro + " ]";
    }
    
}
