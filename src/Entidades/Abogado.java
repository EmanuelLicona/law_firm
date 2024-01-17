/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.sql.Date;



/**
 *
 * @author alico
 */
public class Abogado {
    
   private int idAbogado;
   private String nombre;
   private String pellidos;
   private String num_identidad;
   private String num_telefono;
   private String correo;
   private String domicilio;
   private Date fechaNacimiento;
   private int num_colegiado;
   private int estadoCivil;
   private int estado;

    public Abogado(int idAbogado, String nombre, String pellidos, String num_identidad, String num_telefono, String correo, String domicilio, Date fechaNacimiento, int num_colegiado, int estadoCivil, int estado) {
        this.idAbogado = idAbogado;
        this.nombre = nombre;
        this.pellidos = pellidos;
        this.num_identidad = num_identidad;
        this.num_telefono = num_telefono;
        this.correo = correo;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
        this.num_colegiado = num_colegiado;
        this.estadoCivil = estadoCivil;
        this.estado = estado;
    }

    public Abogado(int idAbogado, String nombre) {
        this.idAbogado = idAbogado;
        this.nombre = nombre;
    }
    
    
    public Abogado(){
    }

    public int getIdAbogado() {
        return idAbogado;
    }

    public void setIdAbogado(int idAbogado) {
        this.idAbogado = idAbogado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPellidos() {
        return pellidos;
    }

    public void setPellidos(String pellidos) {
        this.pellidos = pellidos;
    }

    public String getNum_identidad() {
        return num_identidad;
    }

    public void setNum_identidad(String num_identidad) {
        this.num_identidad = num_identidad;
    }

    public String getNum_telefono() {
        return num_telefono;
    }

    public void setNum_telefono(String num_telefono) {
        this.num_telefono = num_telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getNum_colegiado() {
        return num_colegiado;
    }

    public void setNum_colegiado(int num_colegiado) {
        this.num_colegiado = num_colegiado;
    }

    public int getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(int estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Abogado{" + "idAbogado=" + idAbogado + ", nombre=" + nombre + ", pellidos=" + pellidos + ", num_identidad=" + num_identidad + ", num_telefono=" + num_telefono + ", correo=" + correo + ", domicilio=" + domicilio + ", fechaNacimiento=" + fechaNacimiento + ", num_colegiado=" + num_colegiado + ", estadoCivil=" + estadoCivil + ", estado=" + estado + '}';
    }
    
   
   
    
}
