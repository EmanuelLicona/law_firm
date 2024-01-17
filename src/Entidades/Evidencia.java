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
public class Evidencia {
    private int idEvidencia;
    private String nombreCaso;
    private String nombreTipoEvidencia;
    private String comentario;
    private String urlEvidencia;
    private Date fecha;
    private int idTipoEvidencia;
    private int IdCaso;

    public Evidencia() {
    }

    public Evidencia(int idEvidencia, String nombreCaso, String nombreTipoEvidencia, String comentario, String urlEvidencia, Date fecha, int idTipoEvidencia, int IdCaso) {
        this.idEvidencia = idEvidencia;
        this.nombreCaso = nombreCaso;
        this.nombreTipoEvidencia = nombreTipoEvidencia;
        this.comentario = comentario;
        this.urlEvidencia = urlEvidencia;
        this.fecha = fecha;
        this.idTipoEvidencia = idTipoEvidencia;
        this.IdCaso = IdCaso;
    }

    public int getIdEvidencia() {
        return idEvidencia;
    }

    public void setIdEvidencia(int idEvidencia) {
        this.idEvidencia = idEvidencia;
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso) {
        this.nombreCaso = nombreCaso;
    }

    public String getNombreTipoEvidencia() {
        return nombreTipoEvidencia;
    }

    public void setNombreTipoEvidencia(String nombreTipoEvidencia) {
        this.nombreTipoEvidencia = nombreTipoEvidencia;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getUrlEvidencia() {
        return urlEvidencia;
    }

    public void setUrlEvidencia(String urlEvidencia) {
        this.urlEvidencia = urlEvidencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdTipoEvidencia() {
        return idTipoEvidencia;
    }

    public void setIdTipoEvidencia(int idTipoEvidencia) {
        this.idTipoEvidencia = idTipoEvidencia;
    }

    public int getIdCaso() {
        return IdCaso;
    }

    public void setIdCaso(int IdCaso) {
        this.IdCaso = IdCaso;
    }
 
}
