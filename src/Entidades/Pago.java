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
public class Pago {
    private int idPago;
    private String comentario;
    private double monto;
    private Date fecha;
    private int idTipoPago;
    private String nombreTipoPago;
    private int IdCaso;
    private int IdConsulta;
    private int IdUsuario;

    public Pago(int idPago, String comentario, double monto, Date fecha, int idTipoPago, int IdCaso, int IdConsulta, int IdUsuario) {
        this.idPago = idPago;
        this.comentario = comentario;
        this.monto = monto;
        this.fecha = fecha;
        this.idTipoPago = idTipoPago;
        this.IdCaso = IdCaso;
        this.IdConsulta = IdConsulta;
        this.IdUsuario = IdUsuario;
    }
    
    public Pago(int idPago, String nombreTipoPago, double monto, Date fecha, String comentario, int idTipoPago) {
        this.idPago = idPago;
        this.nombreTipoPago = nombreTipoPago;
        this.monto = monto;
        this.fecha = fecha;
        this.comentario = comentario;
        this.idTipoPago = idTipoPago;
    }

    public int getIdCaso() {
        return IdCaso;
    }

    public void setIdCaso(int IdCaso) {
        this.IdCaso = IdCaso;
    }

    public int getIdConsulta() {
        return IdConsulta;
    }

    public void setIdConsulta(int IdConsulta) {
        this.IdConsulta = IdConsulta;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public Pago() {
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
}
