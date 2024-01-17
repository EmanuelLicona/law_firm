/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.EstadoCivilDAO;
import Datos.OcupacionDAO;
import Datos.TestigoDAO;
import Datos.TipoTestigoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import Entidades.EstadoCivil;
import Entidades.Ocupacion;
import Entidades.Testigo;
import Entidades.TipoTestigo;

/**
 *
 * @author Buddys
 */
public class TestigoControl {
//    private final RolDAO DATOS;

    private final TipoTestigoDAO TPDATOS;
    private final EstadoCivilDAO ECDATOS;
    private final OcupacionDAO OCUPACIONDATOS;
    private final TestigoDAO DATOS;
    private Testigo objeto;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public TestigoControl() {
        this.TPDATOS = new TipoTestigoDAO();
        this.ECDATOS = new EstadoCivilDAO();
        this.OCUPACIONDATOS = new OcupacionDAO();
        this.DATOS = new TestigoDAO();
    }

    public DefaultTableModel listar(String texto) {
        List<Testigo> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));

        String[] titulos = {"Id", "Nombre", "Apellido", "Identidad", "Teléfono", "Estado Civil", "ID Caso", "Titulo Caso", "Ocupación", "Tipo Testigo", "Correo", "Domicilio", "Fecha Nacimiento", "Id Estado Civil", "Id Ocupación", "Id Tipo Testigo", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[17];

        this.registrosMostrados = 0;
        for (Testigo item : lista) {
            if (item.isEstado()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getApellido();
            registro[3] = item.getIdentidad();
            registro[4] = item.getTelefono();
            registro[5] = item.getEstadoCivilNombre();
            registro[6] = Integer.toString(item.getIdCaso());
            registro[7] = item.getTituloCaso();
            registro[8] = item.getOcupacionNombre();
            registro[9] = item.getTPTestigoNombre();
            registro[10] = item.getCorreo();
            registro[11] = item.getDomicilio();
            registro[12] = String.valueOf(item.getFechaNacimiento());
            registro[13] = Integer.toString(item.getIdEstadoCivil());
            registro[14] = Integer.toString(item.getIdOcupacion());
            registro[15] = Integer.toString(item.getIdTipoTestigo());
            registro[16] = estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    public DefaultTableModel listarEnCaso() {

        String[] titulos = {"Id", "Nombre", "Apellido", "Identidad", "Teléfono", "Tipo Testigo"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        return this.modeloTabla;
    }

    public String Insertar(Testigo objeto) {
        String respuesta = "Error";
        try {
            objeto.getNombre();
            objeto.getApellido();
            objeto.getIdentidad();
            objeto.getTelefono();
            objeto.getCorreo();
            objeto.getDomicilio();
            objeto.getFechaNacimiento();
            objeto.getIdEstadoCivil();
            objeto.getIdOcupacion();
            objeto.getIdTipoTestigo();
            objeto.getIdCaso();
            respuesta = DATOS.insertar(objeto);
            if (respuesta == null) {
                respuesta = "OK";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public String actualizar(Testigo objeto) {
        String respuesta = "Error";
        try {
            objeto.getId();
            objeto.getNombre();
            objeto.getApellido();
            objeto.getIdentidad();
            objeto.getTelefono();
            objeto.getCorreo();
            objeto.getDomicilio();
            objeto.getFechaNacimiento();
            objeto.getIdEstadoCivil();
            objeto.getIdOcupacion();
            objeto.getIdTipoTestigo();
            objeto.getIdCaso();
            respuesta = DATOS.actualizar(objeto);
            if (respuesta == null) {
                respuesta = "OK";
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return respuesta;
    }

    public String desactivar(int id) {
        if (DATOS.desactivar(id)) {
            return "OK";
        } else {
            return "No se puede desactivar el registro";
        }
    }

    public String activar(int id) {
        if (DATOS.activar(id)) {
            return "OK";
        } else {
            return "No se puede activar el registro";
        }
    }

    public DefaultComboBoxModel llenandoComboTipoTestigo() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<TipoTestigo> lista = new ArrayList();
        lista = TPDATOS.seleccionar();

        for (TipoTestigo objeto : lista) {
            items.addElement(new TipoTestigo(objeto.getId(), objeto.getNombre()));
        }
        return items;
    }

    public DefaultComboBoxModel ComboboxEstadoCivil() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<EstadoCivil> lista = new ArrayList();
        lista = ECDATOS.seleccionar();

        for (EstadoCivil objeto : lista) {
            items.addElement(new EstadoCivil(objeto.getId(), objeto.getNombre()));
        }
        return items;
    }

    public DefaultComboBoxModel ComboboxOcupacion() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Ocupacion> lista = new ArrayList();
        lista = OCUPACIONDATOS.seleccionar();

        for (Ocupacion objeto : lista) {
            items.addElement(new Ocupacion(objeto.getId(), objeto.getNombre()));
        }
        return items;
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

}
