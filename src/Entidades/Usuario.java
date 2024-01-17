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
public class Usuario {
    private int id;
    private int rolId;
    private String rolNombre;
    private String nombre;
    private String apellido;
    private String identidad;
    private String cantrasenia;
    private boolean estado;
    private String usuario;
    
    public Usuario() {
    }

    public Usuario(int id,String rolNombre, String nombre, String apellido, String identidad, String cantrasenia, int rolId, boolean estado, String usuario) {
        this.id = id;
        this.rolNombre = rolNombre;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identidad = identidad;
        this.cantrasenia = cantrasenia;
        this.rolId = rolId;
        this.estado = estado;
        this.usuario = usuario;
    }
    
    public Usuario(int id,String rolNombre, String nombre, String apellido, String identidad, int rolId, boolean estado, String usuario) {
        this.id = id;
        this.rolNombre = rolNombre;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identidad = identidad;
        this.rolId = rolId;
        this.estado = estado;
        this.usuario = usuario;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }

    public String getCantrasenia() {
        return cantrasenia;
    }

    public void setCantrasenia(String cantrasenia) {
        this.cantrasenia = cantrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
