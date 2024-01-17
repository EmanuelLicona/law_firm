/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.TipoIndicioDAO;
import Entidades.TipoIndicio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Buddys
 */
public class TipoIndicioControl {

    private final TipoIndicioDAO DATOS;
    private TipoIndicio objeto;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    private String respuesta;

    public TipoIndicioControl() {
        this.DATOS = new TipoIndicioDAO();
        this.objeto = new TipoIndicio();
        this.registrosMostrados = 0;
    }

    public String insertar(String nombre, String descricpion) {
        respuesta = "Error";
        objeto.setNombre(nombre);
        objeto.setDescripcion(descricpion);
        if (DATOS.insertar(objeto)) {
            return "OK";
        } 
        return respuesta;
    }

    public DefaultTableModel listar(String texto) {
        List<TipoIndicio> listar = new ArrayList();
        listar.addAll(DATOS.listar(texto));
        String titulos[] = {"Id", "Nombre", "Descripción"};
        this.modeloTabla = new DefaultTableModel(null, titulos);
        String registro[] = new String[3];

        this.registrosMostrados = 0;
        for (TipoIndicio item : listar) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getDescripcion();
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    public String actualizar(int id, String nombre, String descripcion) {
        respuesta = "Error";
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
