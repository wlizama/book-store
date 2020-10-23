/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Tipolista;

/**
 *
 * @author wilderlizama
 */
@ManagedBean
@SessionScoped
public class CantLibrosTipoLista {

    private int cant_libros;
    private Tipolista tipo_lista;

    public CantLibrosTipoLista(int cant_libros, Tipolista tipo_lista) {
        this.cant_libros = cant_libros;
        this.tipo_lista = tipo_lista;
    }

    public int getCant_libros() {
        return cant_libros;
    }

    public void setCant_libros(int cant_libros) {
        this.cant_libros = cant_libros;
    }

    public Tipolista getTipo_lista() {
        return tipo_lista;
    }

    public void setTipo_lista(Tipolista tipo_lista) {
        this.tipo_lista = tipo_lista;
    }
    
}
