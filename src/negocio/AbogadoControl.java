/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Entidades.Abogado;
import Datos.AbogadoDAO;
import javax.swing.JTable;

public class AbogadoControl {

    private final AbogadoDAO DAO;
    private Abogado objeto;
    private DefaultTableModel modeloTabla;

    public AbogadoControl() {
        this.DAO = new AbogadoDAO();
        this.objeto = new Abogado();
    }

    public String insertar(String nombre, String apellidos, String identidad, String num_telefono,
            String correo, String domicilio, Date fechaNacimiento, int num_colegiado, int estadoCivil, int estado) {

        objeto.setNombre(nombre);
        objeto.setPellidos(apellidos);
        objeto.setNum_identidad(identidad);
        objeto.setNum_telefono(num_telefono);
        objeto.setCorreo(correo);
        objeto.setDomicilio(domicilio);
        objeto.setFechaNacimiento(fechaNacimiento);
        objeto.setNum_colegiado(num_colegiado);
        objeto.setEstadoCivil(estadoCivil);
        objeto.setEstado(estado);

        //System.out.println(objeto.toString());
        if (DAO.insertar(objeto)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public String editar(int id, String nombre, String apellidos, String identidad, String num_telefono,
            String correo, String domicilio, Date fechaNacimiento, int num_colegiado, int estadoCivil, int estado) {

        objeto.setIdAbogado(id);
        objeto.setNombre(nombre);
        objeto.setPellidos(apellidos);
        objeto.setNum_identidad(identidad);
        objeto.setNum_telefono(num_telefono);
        objeto.setCorreo(correo);
        objeto.setDomicilio(domicilio);
        objeto.setFechaNacimiento(fechaNacimiento);
        objeto.setNum_colegiado(num_colegiado);
        objeto.setEstadoCivil(estadoCivil);
        objeto.setEstado(estado);

        if (DAO.editar(objeto)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public String insertarEspecialidad(int idAbogado, int idCatagoria) {
        if (DAO.insertarEspecialidad(idAbogado, idCatagoria)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public String eliminarEspecialidad(int idAbogado) {
        if (DAO.eliminarEspecialidad(idAbogado)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public int Listar_Ultimo_Abogado_Ingresado() {

        return DAO.Listar_Ultimo_Abogado_Ingresado();
    }

    public DefaultTableModel listar() {
        List<Abogado> lista = new ArrayList();

        lista.addAll(DAO.listar());

        String[] titulos = {"Id", "Nombre", "Apellidos", "Identidad", "Telefono", "Correo", "Numero/colegiado", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[8];

        for (Abogado item : lista) {

            registro[0] = Integer.toString(item.getIdAbogado());
            registro[1] = item.getNombre();
            registro[2] = item.getPellidos();
            registro[3] = item.getNum_identidad();
            registro[4] = item.getNum_telefono();
            registro[5] = item.getCorreo();
            registro[6] = item.getNum_colegiado() + "";
            registro[7] = item.getEstado() + "";

            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public DefaultTableModel listarNombre(String nombre) {
        List<Abogado> lista = new ArrayList();

        lista.addAll(DAO.listarNombre(nombre));

        String[] titulos = {"Id", "Nombre", "Apellidos", "Identidad", "Telefono", "Correo", "Numero/colegiado", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[8];

        for (Abogado item : lista) {

            registro[0] = Integer.toString(item.getIdAbogado());
            registro[1] = item.getNombre();
            registro[2] = item.getPellidos();
            registro[3] = item.getNum_identidad();
            registro[4] = item.getNum_telefono();
            registro[5] = item.getCorreo();
            registro[6] = item.getNum_colegiado() + "";
            registro[7] = item.getEstado() + "";

            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public Abogado listandoUnRegistro(int id) {
        return DAO.listandoUnRegistro(id);
    }

    public List<Integer> listandoIdEspecialidad(int idAbogado) {
        return DAO.listandoIdEspecialidad(idAbogado);
    }

    public boolean existe(int id) {

        return DAO.existe(id);
    }
    

}
