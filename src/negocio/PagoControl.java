/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.PagoDAO;
import Datos.TipoPagoDAO;
import Entidades.Consulta;
import Entidades.Pago;
import Entidades.TipoPago;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author Buddys
 */
public class PagoControl {

    private final PagoDAO DATOS;
    private final TipoPagoDAO DATOSTIPO;
    private Pago objeto;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    private String respuesta;

    public PagoControl() {
        this.DATOS = new PagoDAO();
        this.DATOSTIPO = new TipoPagoDAO();
        this.objeto = new Pago();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar() {
        List<Pago> lista = new ArrayList();
        lista.addAll(DATOS.listar());

        String[] titulos = {"Id", "Tipo Pago", "Monto", "Fecha", "Comentario", "Id Tipo Pago"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[6];

        this.registrosMostrados = 0;
        for (Pago item : lista) {
            registro[0] = Integer.toString(item.getIdPago());
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
        List<Pago> lista = new ArrayList();
        lista.addAll(DATOS.listar(id));

        String[] titulos = {"Id", "Tipo Gasto", "Monto", "Fecha", "Comentario", "Id Tipo Gasto"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[6];

        this.registrosMostrados = 0;
        for (Pago item : lista) {
            registro[0] = Integer.toString(item.getIdPago());
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
    
    public DefaultTableModel buscarPago(Date fechaInicio, Date fechaFin) {
        List<Pago> lista = new ArrayList();
        lista.addAll(DATOS.buscarPago(fechaInicio, fechaFin));

        String[] titulos = {"Id", "Tipo Pago", "Monto", "Fecha", "Comentario", "Id Tipo Pago"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[6];

        this.registrosMostrados = 0;
        for (Pago item : lista) {
            registro[0] = Integer.toString(item.getIdPago());
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
    
    public String buscarConsulta(int id) {
        String nombre = "";
        List<Consulta> lista = new ArrayList();
        lista.addAll(DATOS.BuscarConsulta(id));

        for (Consulta item : lista) {
            nombre = item.getNombre();
            break;
        }
      //  JOptionPane.showMessageDialog(null, nombre);
        return nombre;
    }

    public String insertar(String comentario, double monto, Date fecha, int idTipoPago, int idCaso, int idConsulta) {
        respuesta = "Error";
        objeto.setComentario(comentario);
        objeto.setMonto(monto);
        objeto.setFecha(fecha);
        objeto.setIdTipoPago(idTipoPago);
        objeto.setIdCaso(idCaso);
        objeto.setIdConsulta(idConsulta);
        //objeto.setIdUsuario(Variables.usuarioId);
        objeto.setIdUsuario(2);
        if (DATOS.insertar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public DefaultComboBoxModel llenandoCombo() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<TipoPago> listaCaso = new ArrayList();
        listaCaso = DATOSTIPO.seleccionar();

        for (TipoPago objetoCaso : listaCaso) {
            items.addElement(new TipoPago(objetoCaso.getId(), objetoCaso.getNombre()));
        }
        return items;
    }

    public String actualizar(int idPago, String comentario, double monto, Date fecha, int idTipoPago) {
        respuesta = "Error";
        objeto.setIdPago(idPago);
        objeto.setComentario(comentario);
        objeto.setMonto(monto);
        objeto.setFecha(fecha);
        objeto.setIdTipoPago(idTipoPago);
        if (DATOS.actualizar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

}
