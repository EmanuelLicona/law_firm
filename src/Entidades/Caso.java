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
 * @author Buddys
 */
public class Caso {
    private int id;
    private int idCliente;
    private String nombre;
    private int idTipoCaso;
    private String descripcion;
    private double honorario;
    private int estado;
    private int idJuzgado;
    private Date fechaCreacion;
    private int NumExpJuzgado;
    private int NumExpDPI;
    private int NumExpMinPublico;

    
    
    public Caso(int id, int idCliente, String nombre, int idTipoCaso, String descripcion, double honorario, int estado, int idJuzgado, Date fechaCreacion, int NumExpJuzgado, int NumExpDPI, int NumExpMinPublico) {
        this.id = id;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.idTipoCaso = idTipoCaso;
        this.descripcion = descripcion;
        this.honorario = honorario;
        this.estado = estado;
        this.idJuzgado = idJuzgado;
        this.fechaCreacion = fechaCreacion;
        this.NumExpJuzgado = NumExpJuzgado;
        this.NumExpDPI = NumExpDPI;
        this.NumExpMinPublico = NumExpMinPublico;
    }
    
    
    
    
    
    
    //CONSTRUCOR PARA LLENAR COMBOBOX
    public Caso(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdTipoCaso() {
        return idTipoCaso;
    }

    public void setIdTipoCaso(int idTipoCaso) {
        this.idTipoCaso = idTipoCaso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getHonorario() {
        return honorario;
    }

    public void setHonorario(double honorario) {
        this.honorario = honorario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdJuzgado() {
        return idJuzgado;
    }

    public void setIdJuzgado(int idJuzgado) {
        this.idJuzgado = idJuzgado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getNumExpJuzgado() {
        return NumExpJuzgado;
    }

    public void setNumExpJuzgado(int NumExpJuzgado) {
        this.NumExpJuzgado = NumExpJuzgado;
    }

    public int getNumExpDPI() {
        return NumExpDPI;
    }

    public void setNumExpDPI(int NumExpDPI) {
        this.NumExpDPI = NumExpDPI;
    }

    public int getNumExpMinPublico() {
        return NumExpMinPublico;
    }

    public void setNumExpMinPublico(int NumExpMinPublico) {
        this.NumExpMinPublico = NumExpMinPublico;
    }
    
    

    public Caso() {
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

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.nombre);
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
        final Caso other = (Caso) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
   
}
