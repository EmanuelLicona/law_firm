/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.TipoConsultaDAO;
import Entidades.TipoConsulta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Buddys
 */
public class TipoConsultaControl {

    private final TipoConsultaDAO DATOS;
    private TipoConsulta objeto;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public TipoConsultaControl() {
        this.DATOS = new TipoConsultaDAO();
        this.objeto = new TipoConsulta();
        this.registrosMostrados = 0;
    }

    public String insertar(String nombre) {
        if (DATOS.existe(nombre)) {
            return "El tipo de juzgado que tratas de insertar ya existe";
        } else {
            objeto.setNombre(nombre);
            if (DATOS.insertar(objeto)) {
                return "OK";
            } else {
                return "Error en la inserción del tipo de juzgado";
            }
        }
    }

    public DefaultTableModel listar(String texto) {
        List<TipoConsulta> listar = new ArrayList();
        listar.addAll(DATOS.listar(texto));
        String titulos[] = {"Id", "Nombre"};
        this.modeloTabla = new DefaultTableModel(null, titulos);
        String registro[] = new String[2];

        this.registrosMostrados = 0;
        for (TipoConsulta item : listar) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    public String actualizar(int id, String nombre, String nombreAnterior) {
        if (nombre.equals(nombreAnterior)) {
            objeto.setId(id);
            objeto.setNombre(nombre);
            if (DATOS.actualizar(objeto)) {
                return "OK";
            } else {
                return "Error en la actualización";
            }
        } else {
            if (DATOS.existe(nombre)) {
                return "El registro ya existe";
            } else {
                objeto.setId(id);
                objeto.setNombre(nombre);
                if (DATOS.actualizar(objeto)) {
                    return "OK";
                } else {
                    return "ERROR en la actualización";
                }
            }
        }
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

}
