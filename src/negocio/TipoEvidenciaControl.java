/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.TipoEvidenciaDAO;
import Entidades.TipoEvidencia;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Buddys
 */
public class TipoEvidenciaControl {

    private final TipoEvidenciaDAO DATOS;
    private TipoEvidencia objeto;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    private String respuesta;

    public TipoEvidenciaControl() {
        this.DATOS = new TipoEvidenciaDAO();
        this.objeto = new TipoEvidencia();
        this.registrosMostrados = 0;
    }

    public String insertar(String nombre, String descricpion) {
        respuesta = "error";
        objeto.setNombre(nombre);
        objeto.setDescripcion(descricpion);
        if (DATOS.insertar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public DefaultTableModel listar(String texto) {
        List<TipoEvidencia> listar = new ArrayList();
        listar.addAll(DATOS.listar(texto));
        String titulos[] = {"Id", "Nombre", "Descripción"};
        this.modeloTabla = new DefaultTableModel(null, titulos);
        String registro[] = new String[3];

        this.registrosMostrados = 0;
        for (TipoEvidencia item : listar) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getDescripcion();
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    public String actualizar(int id, String nombre, String descripcion) {
        respuesta = "error";
        objeto.setId(id);
        objeto.setNombre(nombre);
        objeto.setDescripcion(descripcion);
        if (DATOS.actualizar(objeto)) {
            return "OK";
        } 
        return respuesta;
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

}
