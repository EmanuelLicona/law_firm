/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.TipoJuzgadoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Entidades.TipoJuzgado;

/**
 *
 * @author Buddys
 */
public class TipoJuzgadoControl {

    private final TipoJuzgadoDAO DATOS;
    private TipoJuzgado objeto;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    private String respuesta;

    public TipoJuzgadoControl() {
        this.DATOS = new TipoJuzgadoDAO();
        this.objeto = new TipoJuzgado();
        this.registrosMostrados = 0;
    }

    public String insertar(String nombre) {
        respuesta = "error";
        objeto.setNombre(nombre);
        if (DATOS.insertar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public DefaultTableModel listar(String texto) {
        List<TipoJuzgado> listar = new ArrayList();
        listar.addAll(DATOS.listar(texto));
        String titulos[] = {"Id", "Nombre"};
        this.modeloTabla = new DefaultTableModel(null, titulos);
        String registro[] = new String[2];

        this.registrosMostrados = 0;
        for (TipoJuzgado item : listar) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    public String actualizar(int id, String nombre, String nombreAnterior) {
        respuesta = "error";
        objeto.setId(id);
        objeto.setNombre(nombre);
        if (DATOS.actualizar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

}
