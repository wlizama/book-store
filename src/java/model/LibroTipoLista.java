/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author wilderlizama
 */
public class LibroTipoLista {
    
    private int id_libro;
    private Tipolista tipo_lista;

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public Tipolista getTipo_lista() {
        return tipo_lista;
    }

    public void setTipo_lista(Tipolista tipo_lista) {
        this.tipo_lista = tipo_lista;
    }

    public LibroTipoLista(int id_libro, Tipolista tipo_lista) {
        this.id_libro = id_libro;
        this.tipo_lista = tipo_lista;
    }
    
    
}
