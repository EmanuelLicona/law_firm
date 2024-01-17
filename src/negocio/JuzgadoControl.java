/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.JuzgadoDAO;
import Datos.TipoJuzgadoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import Entidades.Juzgado;
import Entidades.TipoJuzgado;

/**
 *
 * @author Buddys
 */
public class JuzgadoControl {

    private final TipoJuzgadoDAO DATOSJUZGADO;
    private final JuzgadoDAO DATOS;
    private Juzgado objeto;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    private String respuesta;

    public JuzgadoControl() {
        this.DATOSJUZGADO = new TipoJuzgadoDAO();
        this.DATOS = new JuzgadoDAO();
        this.objeto = new Juzgado();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Juzgado> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));

        String[] titulos = {"Id", "Tipo Juzgado", "Nombre", "Ubicación", "teléfono", "Id Tipo Juzgado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[6];

        this.registrosMostrados = 0;
        for (Juzgado item : lista) {
            registro[0] = Integer.toString(item.getIdJuzgado());
            registro[1] = item.getNombreIdtipoJuzgado();
            registro[2] = item.getNombre();
            registro[3] = item.getUbicacion();
            registro[4] = item.getTelefono();
            registro[5] = Integer.toString(item.getIdTipoJuzgado());
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    public String insertar(String nombre, String ubicacion, String telefono, int idTipoJuzgado) {
        respuesta = "error";
        objeto.setNombre(nombre);
        objeto.setUbicacion(ubicacion);
        objeto.setTelefono(telefono);
        objeto.setIdTipoJuzgado(idTipoJuzgado);
        if (DATOS.insertar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public DefaultComboBoxModel llenandoCombo() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<TipoJuzgado> listaJuzgado = new ArrayList();
        listaJuzgado = DATOSJUZGADO.seleccionar();

        for (TipoJuzgado objeto : listaJuzgado) {
            items.addElement(new TipoJuzgado(objeto.getId(), objeto.getNombre()));
        }
        return items;
    }

    public String actualizar(int id, String nombre, String ubicacion, String telefono, int idTipoJuzgado) {
        respuesta = "error";
        objeto.setIdJuzgado(id);
        objeto.setNombre(nombre);
        objeto.setUbicacion(ubicacion);
        objeto.setTelefono(telefono);
        objeto.setIdTipoJuzgado(idTipoJuzgado);
        if (DATOS.actualizar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }
    
     public Juzgado listandoUnRegistro(int id) {
        return DATOS.listandoUnRegistro(id);
    }

    public boolean existe(int id){
        return DATOS.existe(id);
    }

}
