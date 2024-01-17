/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.RolDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Entidades.Rol;

/**
 *
 * @author Buddys
 */
public class RolControl {

    private final RolDAO DATOS;
    private Rol objeto;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public RolControl() {
        this.DATOS = new RolDAO();
        this.objeto = new Rol();
        this.registrosMostrados = 0;
    }

    public String insertar(String nombre, String descricpion) {
        if (DATOS.existe(nombre)) {
            return "El rol que acabas de insertar ya existe";
        } else {
            objeto.setNombre(nombre);
            objeto.setDescripcion(descricpion);
            if (DATOS.insertar(objeto)) {
                return "OK";
            } else {
                return "Error en la inserción del rol";
            }
        }
    }

    public DefaultTableModel listar(String texto) {
        List<Rol> listar = new ArrayList();
        listar.addAll(DATOS.listar(texto));
        String titulos[] = {"Id", "Nombre", "Descripción"};
        this.modeloTabla = new DefaultTableModel(null, titulos);
        String registro[] = new String[3];

        this.registrosMostrados = 0;
        for (Rol item : listar) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getDescripcion();
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;//CONTADOR PAGINACION
        }
        return this.modeloTabla;
    }

    public String actualizar(int id, String nombre, String nombreAnterior, String descripcion) {
        if (nombre.equals(nombreAnterior)) {
            objeto.setId(id);
            objeto.setNombre(nombre);
            objeto.setDescripcion(descripcion);
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
                objeto.setDescripcion(descripcion);
                if (DATOS.actualizar(objeto)) {
                    return "OK";
                } else {
                    return "ERROR en la actualización";
                }
            }
        }
    }
    
    public int totalMostrados(){
        return this.registrosMostrados;
    }

}
