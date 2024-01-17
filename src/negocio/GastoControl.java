/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.GastoDAO;
import Datos.TipoGastoDAO;
import Entidades.Gasto;
import Entidades.Caso;
import Entidades.TipoGasto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;

/**
 *
 * @author Buddys
 */
public class GastoControl {

    private final GastoDAO DATOS;
    private final TipoGastoDAO DATOSTIPO;
    private Gasto objeto;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    private String respuesta;

    public GastoControl() {
        this.DATOS = new GastoDAO();
        this.DATOSTIPO = new TipoGastoDAO();
        this.objeto = new Gasto();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar() {
        List<Gasto> lista = new ArrayList();
        lista.addAll(DATOS.listar());

        String[] titulos = {"Id", "Tipo Gasto", "Monto", "Fecha", "Comentario", "Id Tipo Gasto"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[6];

        this.registrosMostrados = 0;
        for (Gasto item : lista) {
            registro[0] = Integer.toString(item.getIdGasto());
            registro[1] = item.getNombreTipoPago();
            registro[2] = Double.toString(item.getMonto());
            registro[3] = item.getFecha().toString();
            registro[4] = item.getComentario();
            registro[5] = Integer.toString(item.getIdTipoPago());
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    public DefaultTableModel listar(int id) {
        List<Gasto> lista = new ArrayList();
        lista.addAll(DATOS.listar(id));

        String[] titulos = {"Id", "Tipo Gasto", "Monto", "Fecha", "Comentario", "Id Tipo Gasto"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[6];

        this.registrosMostrados = 0;
        for (Gasto item : lista) {
            registro[0] = Integer.toString(item.getIdGasto());
            registro[1] = item.getNombreTipoPago();
            registro[2] = Double.toString(item.getMonto());
            registro[3] = item.getFecha().toString();
            registro[4] = item.getComentario();
            registro[5] = Integer.toString(item.getIdTipoPago());
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    public DefaultTableModel buscarGasto(Date fechaInicio, Date fechaFin) {
        List<Gasto> lista = new ArrayList();
        lista.addAll(DATOS.buscarPago(fechaInicio, fechaFin));

        String[] titulos = {"Id", "Tipo Pago", "Monto", "Fecha", "Comentario", "Id Tipo Pago"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[6];

        this.registrosMostrados = 0;
        for (Gasto item : lista) {
            registro[0] = Integer.toString(item.getIdGasto());
            registro[1] = item.getNombreTipoPago();
            registro[2] = Double.toString(item.getMonto());
            registro[3] = item.getFecha().toString();
            registro[4] = item.getComentario();
            registro[5] = Integer.toString(item.getIdTipoPago());
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    public String insertar(String comentario, double monto, Date fecha, int idTipoPago, int idCaso) {
        respuesta = "Error";
        objeto.setComentario(comentario);
        objeto.setMonto(monto);
        objeto.setFecha(fecha);
        objeto.setIdTipoPago(idTipoPago);
        objeto.setIdCaso(idCaso);
        if (DATOS.insertar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public DefaultComboBoxModel llenandoCombo() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<TipoGasto> listaCaso = new ArrayList();
        listaCaso = DATOSTIPO.seleccionar();

        for (TipoGasto objetoCaso : listaCaso) {
            items.addElement(new TipoGasto(objetoCaso.getId(), objetoCaso.getNombre()));
        }
        return items;
    }

    public String actualizar(int idGasto, String comentario, double monto, Date fecha, int idTipoPago, int idCaso) {
        respuesta = "Error";
        objeto.setIdGasto(idGasto);
        objeto.setComentario(comentario);
        objeto.setMonto(monto);
        objeto.setFecha(fecha);
        objeto.setIdTipoPago(idTipoPago);
        objeto.setIdCaso(idCaso);
        if (DATOS.actualizar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

    public Caso listarTituloCaso(int idGasto) {
        return DATOS.listarTituloCaso(idGasto);
    }

}
