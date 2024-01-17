/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.EvidenciaDAO;

import Entidades.Caso;
import Entidades.Evidencia;
import Entidades.TipoEvidencia;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;

/**
 *
 * @author Buddys
 */
public class EvidenciaControl {

    private final EvidenciaDAO DATOS;
    private Evidencia objeto;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    private String respuesta;

    public EvidenciaControl() {
        this.DATOS = new EvidenciaDAO();
        this.objeto = new Evidencia();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Evidencia> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));

        String[] titulos = {"Id", "Titulo Caso", "Tipo Evidencia", "Comentario", "Archivo", "Fecha", "Id Tipo Evidencia", "Id Caso"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[8];

        this.registrosMostrados = 0;
        for (Evidencia item : lista) {
            registro[0] = Integer.toString(item.getIdEvidencia());
            registro[1] = item.getNombreCaso();
            registro[2] = item.getNombreTipoEvidencia();
            registro[3] = item.getComentario();
            registro[4] = item.getUrlEvidencia();
            registro[5] = item.getFecha().toString();
            registro[6] = Integer.toString(item.getIdTipoEvidencia());
            registro[7] = Integer.toString(item.getIdCaso());
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }
    
    public DefaultTableModel listar(int id) {
        List<Evidencia> lista = new ArrayList();
        lista.addAll(DATOS.listarIdCaso(id));

        String[] titulos = {"Id", "Tipo Evidencia", "Comentario", "Archivo", "Fecha"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[5];

        this.registrosMostrados = 0;
        for (Evidencia item : lista) {
            registro[0] = Integer.toString(item.getIdEvidencia());
            //registro[1] = item.getNombreCaso();
            registro[1] = item.getNombreTipoEvidencia();
            registro[2] = item.getComentario();
            registro[3] = item.getUrlEvidencia();
            registro[4] = item.getFecha().toString();
         
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    public String insertar(String comentario, String urlEvidencia, int idTipoEvidencia, Date fecha, int idCaso) {
        respuesta = "Error";
        objeto.setComentario(comentario);
        objeto.setUrlEvidencia(urlEvidencia);
        objeto.setIdTipoEvidencia(idTipoEvidencia);
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
    
    public String buscar(int id) {
        String nombre = "";
        List<Caso> lista = new ArrayList();
        lista.addAll(DATOS.BuscarCaso(id));

        for (Caso item : lista) {
            nombre = item.getNombre();
            
        }
        return nombre;
    }

    public DefaultComboBoxModel llenandoComboTipoEvidencia() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<TipoEvidencia> listaTipoEvidencia = new ArrayList();
        listaTipoEvidencia = DATOS.comboboxTipoEvidencia();

        for (TipoEvidencia objetoTipoEvidencia : listaTipoEvidencia) {
            items.addElement(new TipoEvidencia(objetoTipoEvidencia.getId(), objetoTipoEvidencia.getNombre()));
        }
        return items;
    }

    public String actualizar(int idEvidencia, String comentario, String urlArchivo, int tipoEvidencia, Date fecha, int idCaso) {
        String respuesta = "Error";
        objeto.setIdEvidencia(idEvidencia);
        objeto.setComentario(comentario);
        objeto.setUrlEvidencia(urlArchivo);
        objeto.setIdTipoEvidencia(tipoEvidencia);
        objeto.setFecha(fecha);
        objeto.setIdCaso(idCaso);
        if (DATOS.actualizar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public String desactivar(int id) {
        if (DATOS.desactivar(id)) {
            return "OK";
        } else {
            return "No se puede desactivar el registro";
        }
    }

    public String activar(int id) {
        if (DATOS.activar(id)) {
            return "OK";
        } else {
            return "No se puede activar el registro";
        }
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

}
