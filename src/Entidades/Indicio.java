/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.sql.Date;

/**
 * 
 * @author Buddys
 */
public class Indicio {
    private int idIndicio;
    private String nombreCaso;
    private String nombreTipoindicio;
    private String comentario;
    private String urlIndicio;
    private Date fecha;
    private int idTipoindicio;
    private int IdCaso;

    public Indicio() {
    }

    public Indicio(int idIndicio, String nombreCaso, String nombreTipoindicio, String comentario, String urlIndicio, Date fecha, int idTipoindicio, int IdCaso) {
        this.idIndicio = idIndicio;
        this.nombreCaso = nombreCaso;
        this.nombreTipoindicio = nombreTipoindicio;
        this.comentario = comentario;
        this.urlIndicio = urlIndicio;
        this.fecha = fecha;
        this.idTipoindicio = idTipoindicio;
        this.IdCaso = IdCaso;
    }

    public int getIdIndicio() {
        return idIndicio;
    }

    public void setIdIndicio(int idIndicio) {
        this.idIndicio = idIndicio;
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso) {
        this.nombreCaso = nombreCaso;
    }

    public String getNombreTipoindicio() {
        return nombreTipoindicio;
    }

    public void setNombreTipoindicio(String nombreTipoindicio) {
        this.nombreTipoindicio = nombreTipoindicio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getUrlIndicio() {
        return urlIndicio;
    }

    public void setUrlIndicio(String urlIndicio) {
        this.urlIndicio = urlIndicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdTipoindicio() {
        return idTipoindicio;
    }

    public void setIdTipoindicio(int idTipoindicio) {
        this.idTipoindicio = idTipoindicio;
    }

    public int getIdCaso() {
        return IdCaso;
    }

    public void setIdCaso(int IdCaso) {
        this.IdCaso = IdCaso;
    }

}
