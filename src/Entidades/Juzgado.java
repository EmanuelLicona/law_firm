/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Buddys
 */
public class Juzgado {
    private int idJuzgado;
    private String nombreIdtipoJuzgado;
    private String nombre;
    private String ubicacion;
    private String telefono;
    private int idTipoJuzgado;

    public Juzgado(int idJuzgado, String nombreIdtipoJuzgado, String nombre, String ubicacion, String telefono, int idTipoJuzgado) {
        this.idJuzgado = idJuzgado;
        this.nombreIdtipoJuzgado = nombreIdtipoJuzgado;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.idTipoJuzgado = idTipoJuzgado;
    }

    public Juzgado() {
    }

    public int getIdJuzgado() {
        return idJuzgado;
    }

    public void setIdJuzgado(int idJuzgado) {
        this.idJuzgado = idJuzgado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreIdtipoJuzgado() {
        return nombreIdtipoJuzgado;
    }

    public void setNombreIdtipoJuzgado(String nombreIdtipoJuzgado) {
        this.nombreIdtipoJuzgado = nombreIdtipoJuzgado;
    }

    public int getIdTipoJuzgado() {
        return idTipoJuzgado;
    }

    public void setIdTipoJuzgado(int idTipoJuzgado) {
        this.idTipoJuzgado = idTipoJuzgado;
    }
}
