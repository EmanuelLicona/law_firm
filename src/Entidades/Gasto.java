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
public class Gasto {
    private int idGasto;
    private String comentario;
    private Date fecha;
    private double monto;
    private int idTipoPago;
    private String nombreTipoPago;
    private int IdCaso;

    public Gasto() {
    }

    public Gasto(int idGasto, String comentario, Date fecha, double monto, int idTipoPago, String nombreTipoPago) {
        this.idGasto = idGasto;
        this.comentario = comentario;
        this.fecha = fecha;
        this.monto = monto;
        this.idTipoPago = idTipoPago;
        this.nombreTipoPago = nombreTipoPago;
    }
    

    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getNombreTipoPago() {
        return nombreTipoPago;
    }

    public void setNombreTipoPago(String nombreTipoPago) {
        this.nombreTipoPago = nombreTipoPago;
    }

    public int getIdCaso() {
        return IdCaso;
    }

    public void setIdCaso(int IdCaso) {
        this.IdCaso = IdCaso;
    }
}
