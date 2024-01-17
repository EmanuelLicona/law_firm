/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author alico
 */
public class Fiscal {
   private int idFiscal;
   private String nombre;
   private String Apellidos;
   private String identidad;
   private String telefono;
   private String correo;
   private int tipoFiscal;
   private int num_colegiado;
   private int estadoCivil;
   private int estado;

    public Fiscal(int idFiscal, String nombre, String Apellidos, String identidad, String telefono, String correo,int num_colegiado, int tipoFiscal, int estadoCivil, int estado) {
        this.idFiscal = idFiscal;
        this.nombre = nombre;
        this.Apellidos = Apellidos;
        this.identidad = identidad;
        this.telefono = telefono;
        this.correo = correo;
        this.tipoFiscal = tipoFiscal;
        this.num_colegiado = num_colegiado;
        this.estadoCivil = estadoCivil;
        this.estado = estado;
    }

    public int getTipoFiscal() {
        return tipoFiscal;
    }

    public void setTipoFiscal(int tipoFiscal) {
        this.tipoFiscal = tipoFiscal;
    }

    
    
    public Fiscal() {
    }

    public int getIdFiscal() {
        return idFiscal;
    }

    public void setIdFiscal(int idFiscal) {
        this.idFiscal = idFiscal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    
   
   
}
