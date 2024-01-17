/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.RolDAO;
import Datos.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import Entidades.Rol;
import Entidades.Usuario;

/**
 *
 * @author Buddys
 */
public class UsuarioControl {

    private final RolDAO DATOS;
    private final UsuarioDAO DATOSUSU;
    private Usuario objeto;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    private String respuesta;

    public UsuarioControl() {
        this.DATOS = new RolDAO();
        this.DATOSUSU = new UsuarioDAO();
        this.objeto = new Usuario();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto, int totalPorPagina, int numPagina) {
        List<Usuario> lista = new ArrayList();
        lista.addAll(DATOSUSU.listar(texto, totalPorPagina, numPagina));

        String[] titulos = {"Id", "Rol ID", "Rol", "Nombre", "Apellido", "Identidad", "Contraseña", "Estado", "Usuario"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[9];

        this.registrosMostrados = 0;
        for (Usuario item : lista) {
            if (item.isEstado()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }
            registro[0] = Integer.toString(item.getId());
            registro[1] = Integer.toString(item.getRolId());
            registro[2] = item.getRolNombre();
            registro[3] = item.getNombre();
            registro[4] = item.getApellido();
            registro[5] = item.getIdentidad();
            registro[6] = item.getCantrasenia();
            registro[7] = estado;
            registro[8] = item.getUsuario();
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }
    
    public String login(String usuario, String clave){
        String respuesta = "0";
        Usuario usu = this.DATOSUSU.login(usuario, clave);
        if (usu != null) {
            if (usu.isEstado()) {
                Variables.usuarioId = usu.getId();
                Variables.rolId = usu.getRolId();
                Variables.rolNombre = usu.getRolNombre();
                Variables.Nombre = usu.getNombre();
                Variables.usuarioLogin = usu.getUsuario();
                respuesta = "1";
            } else {
                respuesta = "2";
            }
        }
        return respuesta;
    }

    public String insertar(String nombre, String apellido, String identidad, String contrasenia, int rolID, String usuario) {
        respuesta = "error";
        objeto.setNombre(nombre);
        objeto.setApellido(apellido);
        objeto.setIdentidad(identidad);
        objeto.setCantrasenia(contrasenia);
        objeto.setId(rolID);
        objeto.setUsuario(usuario);
        if (DATOSUSU.insertar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public DefaultComboBoxModel llenandoCombo() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Rol> listaRol = new ArrayList();
        listaRol = DATOS.seleccionar();

        for (Rol objetoRol : listaRol) {
            items.addElement(new Rol(objetoRol.getId(), objetoRol.getNombre()));
        }
        return items;
    }

    public String actualizar(int id, String nombre, String apellido, String identidad, String clave, int idRol, String usuario, String usuarioAnt) {
        respuesta = "error";
        objeto.setId(id);
        objeto.setNombre(nombre);
        objeto.setApellido(apellido);
        objeto.setIdentidad(identidad);
        objeto.setCantrasenia(clave);
        objeto.setRolId(idRol);
        objeto.setUsuario(usuario);
        if (DATOSUSU.actualizar(objeto)) {
            return "OK";
        }
        return respuesta;
    }

    public String desactivar(int id) {
        if (DATOSUSU.desactivar(id)) {
            return "OK";
        } else {
            return "No se puede desactivar el registro";
        }
    }

    public String activar(int id) {
        if (DATOSUSU.activar(id)) {
            return "OK";
        } else {
            return "No se puede activar el registro";
        }
    }

    public int total() {
        return DATOSUSU.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

}
