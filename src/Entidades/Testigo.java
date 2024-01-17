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
public class Testigo {
    private int id;
    private String nombre;
    private String apellido;
    private String identidad;
    private String telefono;
    private String EstadoCivilNombre;
    private String tituloCaso;
    private int idCaso;
    private String OcupacionNombre;
    private String TPTestigoNombre;
    private String correo;
    private String Domicilio;
    private Date fechaNacimiento;
    private int idEstadoCivil;    
    private int idOcupacion;    
    private int idTipoTestigo;    
    private boolean estado;    
     private int estadoint;   

    public Testigo(int id, String nombre, String apellido, String identidad, String telefono, String EstadoCivilNombre, int idCaso, String tituloCaso, String OcupacionNombre, String TPTestigoNombre, String correo, String Domicilio, Date fechaNacimiento, int idEstadoCivil, int idOcupacion, int idTipoTestigo, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identidad = identidad;
        this.telefono = telefono;
        this.EstadoCivilNombre = EstadoCivilNombre;
        this.tituloCaso = tituloCaso;
        this.idCaso = idCaso;
        this.OcupacionNombre = OcupacionNombre;
        this.TPTestigoNombre = TPTestigoNombre;
        this.correo = correo;
        this.Domicilio = Domicilio;
        this.fechaNacimiento = fechaNacimiento;
        this.idEstadoCivil = idEstadoCivil;
        this.idOcupacion = idOcupacion;
        this.idTipoTestigo = idTipoTestigo;
        this.estado = estado;
    }

    public Testigo(int id, String nombre, String apellido, String identidad, String telefono, String correo, String Domicilio, Date fechaNacimiento, int idEstadoCivil, int idOcupacion, String idTipoTestigo, int estado, int idCaso) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identidad = identidad;
        this.telefono = telefono;
        this.correo = correo;
        this.Domicilio = Domicilio;
        this.fechaNacimiento = fechaNacimiento;
        this.idEstadoCivil = idEstadoCivil;
        this.idOcupacion = idOcupacion;
        this.TPTestigoNombre = idTipoTestigo;
        this.estadoint = estado;
        this.idCaso = idCaso;
    }

    public Testigo() {
    }

    public int getEstadoint() {
        return estadoint;
    }

    public void setEstadoint(int estadoint) {
        this.estadoint = estadoint;
    }

    
    public int getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(int idCaso) {
        this.idCaso = idCaso;
    }

    public String getTituloCaso() {
        return tituloCaso;
    }

    public void setTituloCaso(String tituloCaso) {
        this.tituloCaso = tituloCaso;
    }
    


    public String getEstadoCivilNombre() {
        return EstadoCivilNombre;
    }

    public void setEstadoCivilNombre(String EstadoCivilNombre) {
        this.EstadoCivilNombre = EstadoCivilNombre;
    }

    public String getOcupacionNombre() {
        return OcupacionNombre;
    }

    public void setOcupacionNombre(String OcupacionNombre) {
        this.OcupacionNombre = OcupacionNombre;
    }

    public String getTPTestigoNombre() {
        return TPTestigoNombre;
    }

    public void setTPTestigoNombre(String TPTestigoNombre) {
        this.TPTestigoNombre = TPTestigoNombre;
    }
    
    
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String Domicilio) {
        this.Domicilio = Domicilio;
    }

    public int getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(int idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public int getIdOcupacion() {
        return idOcupacion;
    }

    public void setIdOcupacion(int idOcupacion) {
        this.idOcupacion = idOcupacion;
    }

    public int getIdTipoTestigo() {
        return idTipoTestigo;
    }

    public void setIdTipoTestigo(int idTipoTestigo) {
        this.idTipoTestigo = idTipoTestigo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.nombre);
        hash = 83 * hash + Objects.hashCode(this.apellido);
        hash = 83 * hash + Objects.hashCode(this.identidad);
        hash = 83 * hash + Objects.hashCode(this.telefono);
        hash = 83 * hash + Objects.hashCode(this.EstadoCivilNombre);
        hash = 83 * hash + Objects.hashCode(this.tituloCaso);
        hash = 83 * hash + this.idCaso;
        hash = 83 * hash + Objects.hashCode(this.OcupacionNombre);
        hash = 83 * hash + Objects.hashCode(this.TPTestigoNombre);
        hash = 83 * hash + Objects.hashCode(this.correo);
        hash = 83 * hash + Objects.hashCode(this.Domicilio);
        hash = 83 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 83 * hash + this.idEstadoCivil;
        hash = 83 * hash + this.idOcupacion;
        hash = 83 * hash + this.idTipoTestigo;
        hash = 83 * hash + (this.estado ? 1 : 0);
        hash = 83 * hash + this.estadoint;
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
        final Testigo other = (Testigo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idCaso != other.idCaso) {
            return false;
        }
        if (this.idEstadoCivil != other.idEstadoCivil) {
            return false;
        }
        if (this.idOcupacion != other.idOcupacion) {
            return false;
        }
        if (this.idTipoTestigo != other.idTipoTestigo) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (this.estadoint != other.estadoint) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        if (!Objects.equals(this.identidad, other.identidad)) {
            return false;
        }
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        if (!Objects.equals(this.EstadoCivilNombre, other.EstadoCivilNombre)) {
            return false;
        }
        if (!Objects.equals(this.tituloCaso, other.tituloCaso)) {
            return false;
        }
        if (!Objects.equals(this.OcupacionNombre, other.OcupacionNombre)) {
            return false;
        }
        if (!Objects.equals(this.TPTestigoNombre, other.TPTestigoNombre)) {
            return false;
        }
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        if (!Objects.equals(this.Domicilio, other.Domicilio)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        return true;
    }
    
    
 
}
