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
public class Agenda {
    private int id;
    private String descripcion;
    private Date fecha;
    private int idCaso;

    public Agenda() {
    }

    public Agenda(int id, String descripcion, Date fecha, int idCaso) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.idCaso = idCaso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(int idCaso) {
        this.idCaso = idCaso;
    }
    
    
}
