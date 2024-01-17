/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Datos.FiscalDAO;
import Entidades.Fiscal;

/**
 *
 * @author alico
 */
public class FiscalControl {

    private final FiscalDAO DAO;
    private Fiscal objeto;
    private DefaultTableModel modeloTabla;

    public FiscalControl() {
        this.DAO = new FiscalDAO();
        this.objeto = new Fiscal();
    }

    public String insertar(String nombre, String apellidos, String identidad, String num_telefono,
            String correo, int num_colegiado, int tipo, int estadoCivil, int estado) {

        objeto.setNombre(nombre);
        objeto.setApellidos(apellidos);
        objeto.setIdentidad(identidad);
        objeto.setTelefono(num_telefono);
        objeto.setCorreo(correo);
        objeto.setNum_colegiado(num_colegiado);
        objeto.setTipoFiscal(tipo);
        objeto.setEstadoCivil(estadoCivil);
        objeto.setEstado(estado);

        if (DAO.insertar(objeto)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public String editar(int id, String nombre, String apellidos, String identidad, String num_telefono,
            String correo, int num_colegiado, int tipo, int estadoCivil, int estado) {

        objeto.setIdFiscal(id);
        objeto.setNombre(nombre);
        objeto.setApellidos(apellidos);
        objeto.setIdentidad(identidad);
        objeto.setTelefono(num_telefono);
        objeto.setCorreo(correo);
        objeto.setNum_colegiado(num_colegiado);
        objeto.setTipoFiscal(tipo);
        objeto.setEstadoCivil(estadoCivil);
        objeto.setEstado(estado);

        if (DAO.editar(objeto)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public int Listar_Ultimo_Fiscal_Ingresado() {

        return DAO.Listar_Ultimo_Fiscal_Ingresado();
    }

    public DefaultTableModel listar() {
        List<Fiscal> lista = new ArrayList();

        lista.addAll(DAO.listar());

        String[] titulos = {"Id", "Nombre", "Apellidos", "Identidad", "Telefono", "Correo", "Numero/colegiado", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[8];

        for (Fiscal item : lista) {

            registro[0] = Integer.toString(item.getIdFiscal());
            registro[1] = item.getNombre();
            registro[2] = item.getApellidos();
            registro[3] = item.getIdentidad();
            registro[4] = item.getTelefono();
            registro[5] = item.getCorreo();
            registro[6] = item.getNum_colegiado() + "";
            registro[7] = item.getEstado() + "";

            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public DefaultTableModel listarNombre(String nombre) {
        List<Fiscal> lista = new ArrayList();

        lista.addAll(DAO.listarNombre(nombre));

        String[] titulos = {"Id", "Nombre", "Apellidos", "Identidad", "Telefono", "Correo", "Numero/colegiado", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[8];

        for (Fiscal item : lista) {

            registro[0] = Integer.toString(item.getIdFiscal());
            registro[1] = item.getNombre();
            registro[2] = item.getApellidos();
            registro[3] = item.getIdentidad();
            registro[4] = item.getTelefono();
            registro[5] = item.getCorreo();
            registro[6] = item.getNum_colegiado() + "";
            registro[7] = item.getEstado() + "";

            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public Fiscal listandoUnRegistro(int id) {
        return DAO.listandoUnRegistro(id);
    }

    public boolean existe(int id) {
        return DAO.existe(id);
    }
}
