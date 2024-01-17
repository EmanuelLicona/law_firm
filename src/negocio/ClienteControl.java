/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.ClienteDAO;
import Entidades.Cliente;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANGEL-GARCIA
 */
public class ClienteControl {

    private final ClienteDAO DAO;
    private Cliente objeto;
    private DefaultTableModel modelTabla;

    public ClienteControl() {
        this.DAO = new ClienteDAO();
        this.objeto = new Cliente();
    }

    public String insertar(String nombre, String apellidos, String identidad, String num_telefono,
            String correo, String domicilio, Date fechaNacimiento, int estadoCivil, int ocupacion, int estado) {

        objeto.setNombre(nombre);
        objeto.setApellidos(apellidos);
        objeto.setNum_identidad(identidad);
        objeto.setNum_telefono(num_telefono);
        objeto.setCorreo(correo);
        objeto.setDomicilio(domicilio);
        objeto.setFechaNacimiento(fechaNacimiento);
        objeto.setEstadoCivil(estadoCivil);
        objeto.setOcupacion(ocupacion);
        objeto.setEstado(estado);

        if (DAO.insertar(objeto)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }
    
    public DefaultTableModel listarNombre(String nombre) {
        List<Cliente> lista = new ArrayList();

        lista.addAll(DAO.listarNombre(nombre));

        String[] titulos = {"Id_Cliente", "Nombre", "Apellidos", "Identidad", "Telefono", "Correo", "Domicilio", "FechaNacimiento", "Estado Civil", "Ocupacion", "Estado"};
        this.modelTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[11];

        for (Cliente item : lista) {

            registro[0] = Integer.toString(item.getIdCliente());
            registro[1] = item.getNombre();
            registro[2] = item.getApellidos();
            registro[3] = item.getNum_identidad();
            registro[4] = item.getNum_telefono();
            registro[5] = item.getCorreo();
            registro[6] = item.getDomicilio();
            registro[7] = item.getFechaNacimiento() + "";
            registro[8] = item.getEstadoCivil() + "";
            registro[9] = item.getOcupacion() + "";
            registro[10] = item.getEstado() + "";

            this.modelTabla.addRow(registro);
        }
        return this.modelTabla;
    }

    public String editar(int id, String nombre, String apellidos, String identidad, String num_telefono,
            String correo, String domicilio, Date fechaNacimiento, int estadoCivil, int ocupacion, int estado) {

        objeto.setIdCliente(id);
        objeto.setNombre(nombre);
        objeto.setApellidos(apellidos);
        objeto.setNum_identidad(identidad);
        objeto.setNum_telefono(num_telefono);
        objeto.setCorreo(correo);
        objeto.setDomicilio(domicilio);
        objeto.setFechaNacimiento(fechaNacimiento);
        objeto.setEstadoCivil(estadoCivil);
        objeto.setOcupacion(ocupacion);
        objeto.setEstado(estado);

        if (DAO.editar(objeto)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public DefaultTableModel listar() {
        List<Cliente> lista = new ArrayList();

        lista.addAll(DAO.listar());

        String[] titulos = {"Id_Cliente", "Nombre", "Apellidos", "Identidad", "Telefono", "Correo", "Domicilio", "FechaNacimiento", "Estado Civil", "Ocupacion", "Estado"};
        this.modelTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[11];

        for (Cliente item : lista) {

            registro[0] = Integer.toString(item.getIdCliente());
            registro[1] = item.getNombre();
            registro[2] = item.getApellidos();
            registro[3] = item.getNum_identidad();
            registro[4] = item.getNum_telefono();
            registro[5] = item.getCorreo();
            registro[6] = item.getDomicilio();
            registro[7] = item.getFechaNacimiento() + "";
            registro[8] = item.getEstadoCivil() + "";
            registro[9] = item.getOcupacion() + "";
            registro[10] = item.getEstado() + "";

            this.modelTabla.addRow(registro);
        }
        return this.modelTabla;
    }

    public Cliente listandoUnRegistro(int id) {
        return DAO.listandoUnRegistro(id);
    }

    public boolean existe(int id){
        return DAO.existe(id);
    }
}
