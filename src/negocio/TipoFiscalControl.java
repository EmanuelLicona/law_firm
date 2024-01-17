/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Datos.TipoFiscalDAO;
import Entidades.CategoriaAbogado;
import Entidades.TipoFiscal;

/**
 *
 * @author alico
 */
public class TipoFiscalControl {

    private final TipoFiscalDAO DAO;
    private TipoFiscal objeto;
    private DefaultTableModel modeloTabla;

    public TipoFiscalControl() {
        this.DAO = new TipoFiscalDAO();
        this.objeto = new TipoFiscal();
    }

    public DefaultTableModel listar() {
        List<TipoFiscal> lista = new ArrayList();

        lista.addAll(DAO.listar());

        String[] titulos = {"ID", "Nombre", "Descripcion"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[3];

        for (TipoFiscal item : lista) {

            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getDescripcion();

            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public TipoFiscal listarUnRegistro(int id) {
        return DAO.listarUnRegistro(id);
    }

    public String[] listarNombreTipoFiscal() {

        List<TipoFiscal> lista = new ArrayList();

        lista.addAll(DAO.listar());

        String arr[] = new String[0];

        for (TipoFiscal item : lista) {

            arr = redefinirString(arr, item.getNombre());
        }

        return arr;
    }

    public String[] redefinirString(String arr[], String dato) {

        String nuevo[] = new String[arr.length + 1];

        int i = 0;
        for (String s : arr) {
            nuevo[i] = s;
            i++;
        }

        nuevo[nuevo.length - 1] = dato;

        return nuevo;
    }

    public String actualizar(int id, String nombre, String descripcion) {
        objeto.setId(id);
        objeto.setNombre(nombre);
        objeto.setDescripcion(descripcion);
        if (DAO.actualizar(objeto)) {
            return "OK";
        } else {
            return "Error en la actualización";
        }
    }

    public String insertar(String nombre, String Descripcion) {

        objeto.setNombre(nombre);
        objeto.setDescripcion(Descripcion);
        if (DAO.insertar(objeto)) {
            return "OK";
        } else {
            return "Error en la inserción del tipo de juzgado";
        }

    }

    public DefaultTableModel listarNombre(String nombre) {
        List<CategoriaAbogado> lista = new ArrayList();

        lista.addAll(DAO.listarNombre(nombre));

        String[] titulos = {"ID", "Nombre", "Descripcion"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[3];

        for (CategoriaAbogado item : lista) {

            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getDescripcion();

            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }
}
