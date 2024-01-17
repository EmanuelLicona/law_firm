
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.IndicioDAO;
import Entidades.Caso;
import Entidades.Indicio;
import Entidades.TipoIndicio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;

/**
 *
 * @author Buddys
 */
public class IndicioControl {

    private final IndicioDAO DATOS;
    private Indicio objeto;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    private String respuesta;

    public IndicioControl() {
        this.DATOS = new IndicioDAO();
        this.objeto = new Indicio();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Indicio> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));

        String[] titulos = {"Id", "Titulo Caso", "Tipo Indicio", "Comentario", "Archivo", "Fecha", "Id Tipo Indicio", "Id Caso"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[8];

        this.registrosMostrados = 0;
        for (Indicio item : lista) {
            registro[0] = Integer.toString(item.getIdIndicio());
            registro[1] = item.getNombreCaso();
            registro[2] = item.getNombreTipoindicio();
            registro[3] = item.getComentario();
            registro[4] = item.getUrlIndicio();
            registro[5] = item.getFecha().toString();
            registro[6] = Integer.toString(item.getIdTipoindicio());
            registro[7] = Integer.toString(item.getIdCaso());
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }
    
     public DefaultTableModel listar(int texto) {
        List<Indicio> lista = new ArrayList();
        lista.addAll(DATOS.listarIdCaso(texto));

        String[] titulos = {"Id", "Tipo Evidencia", "Comentario", "Archivo", "Fecha"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[5];

        this.registrosMostrados = 0;
        for (Indicio item : lista) {
            registro[0] = Integer.toString(item.getIdIndicio());
            registro[1] = item.getNombreTipoindicio();
            registro[2] = item.getComentario();
            registro[3] = item.getUrlIndicio();
            registro[4] = item.getFecha().toString();

            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    public String insertar(String comentario, String urlIndicio, int idTipoIndicio, Date fecha, int idCaso) {
        respuesta = "Error";
        objeto.setComentario(comentario);
        objeto.setUrlIndicio(urlIndicio);
        objeto.setIdTipoindicio(idTipoIndicio);
        objeto.setFecha(fecha);
        objeto.setIdCaso(idCaso);
        if (DATOS.insertar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public DefaultComboBoxModel llenandoComboCaso() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Caso> listaCaso = new ArrayList();
        listaCaso = DATOS.comboboxCaso();

        for (Caso objetoCaso : listaCaso) {
            items.addElement(new Caso(objetoCaso.getId(), objetoCaso.getNombre()));
        }
        return items;
    }

    public DefaultComboBoxModel llenandoComboTipoIndicio() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<TipoIndicio> listaTipoEvidencia = new ArrayList();
        listaTipoEvidencia = DATOS.comboboxTipoIndicio();

        for (TipoIndicio objetoTipoIndicio : listaTipoEvidencia) {
            items.addElement(new TipoIndicio(objetoTipoIndicio.getId(), objetoTipoIndicio.getNombre()));
        }
        return items;
    }

    public String actualizar(int idIndicio, String comentario, String urlArchivo, int tipoIndicio, Date fecha, int idCaso) {
        respuesta = "Error";
        objeto.setIdIndicio(idIndicio);
        objeto.setComentario(comentario);
        objeto.setUrlIndicio(urlArchivo);
        objeto.setIdTipoindicio(tipoIndicio);
        objeto.setFecha(fecha);
        objeto.setIdCaso(idCaso);
        if (DATOS.actualizar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

}
