/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author ANGEL-GARCIA
 */
public class Cliente {
    
   private int idCliente;
   private String nombre;
   private String apellidos;
   private String num_identidad;
   private String num_telefono;
   private String correo;
   private String domicilio;
   private Date fecha_Nacimiento;
   private int estadoCivil;
   private int ocupacion;
   private int estado;

    public Cliente(int idCliente, String nombre, String apellidos, String num_identidad, String num_telefono, 
            String correo, String domicilio, Date fecha_Nacimiento, int estadoCivil, int ocupacion,int estado) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.num_identidad = num_identidad;
        this.num_telefono = num_telefono;
        this.correo = correo;
        this.domicilio = domicilio;
        this.fecha_Nacimiento = fecha_Nacimiento;
        this.estadoCivil = estadoCivil;
        this.ocupacion = ocupacion;
        this.estado = estado;
    }

   public Cliente(){
   
   }

    public Cliente(int idCliente, String nombre) {
        this.idCliente = idCliente;
        this.nombre = nombre;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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
        return fecha_Nacimiento;
    }

    public void setFechaNacimiento(Date fecha_Nacimiento) {
        this.fecha_Nacimiento = fecha_Nacimiento;
    }

    public int getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(int estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    
    public int getOcupacion(){
        return ocupacion;
    }
    
    public void setOcupacion(int ocupacion){
        this.ocupacion = ocupacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", apellidos=" + apellidos 
                + ", num_identidad=" + num_identidad + ", num_telefono=" + num_telefono + ", correo=" + correo 
                + ", domicilio=" + domicilio + ", fechaNacimiento=" + fecha_Nacimiento + ", estadoCivil=" + estadoCivil + ", "
                + ", ocupacion="+ ocupacion +"estado=" + estado + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.idCliente;
        hash = 11 * hash + Objects.hashCode(this.nombre);
        hash = 11 * hash + Objects.hashCode(this.apellidos);
        hash = 11 * hash + Objects.hashCode(this.num_identidad);
        hash = 11 * hash + Objects.hashCode(this.num_telefono);
        hash = 11 * hash + Objects.hashCode(this.correo);
        hash = 11 * hash + Objects.hashCode(this.domicilio);
        hash = 11 * hash + Objects.hashCode(this.fecha_Nacimiento);
        hash = 11 * hash + this.estadoCivil;
        hash = 11 * hash + this.ocupacion;
        hash = 11 * hash + this.estado;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.idCliente != other.idCliente) {
            return false;
        }
        if (this.estadoCivil != other.estadoCivil) {
            return false;
        }
        if (this.ocupacion != other.ocupacion) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellidos, other.apellidos)) {
            return false;
        }
        if (!Objects.equals(this.num_identidad, other.num_identidad)) {
            return false;
        }
        if (!Objects.equals(this.num_telefono, other.num_telefono)) {
            return false;
        }
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        if (!Objects.equals(this.domicilio, other.domicilio)) {
            return false;
        }
        if (!Objects.equals(this.fecha_Nacimiento, other.fecha_Nacimiento)) {
            return false;
        }
        return true;
    }
    
    
    
}
