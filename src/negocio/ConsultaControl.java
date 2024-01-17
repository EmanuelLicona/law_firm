/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;


import Datos.ConsultasDAO;
import Datos.TipoConsultaDAO;
import Entidades.Abogado;
import Entidades.Cliente;
import Entidades.Consultas;
import Entidades.TipoConsulta;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Buddys
 */
public class ConsultaControl {

    private final TipoConsultaDAO DATOSCONSULTA;
    private final ConsultasDAO DATOS;
    private Consultas objeto;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public ConsultaControl() {
        this.DATOSCONSULTA = new TipoConsultaDAO();
        this.DATOS = new ConsultasDAO();
        this.objeto = new Consultas();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Consultas> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));

        String[] titulos = {"IdConsulta", "IdCliente", "IdAbogado", "Fecha", "Tipo Consulta","Descripcion"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[6];

        this.registrosMostrados = 0;
        for (Consultas item : lista) {
            registro[0] = item.getIdConsulta()+"";
            registro[1] = item.getIdCliente()+"";
            registro[2] = item.getIdAbogado()+"";
            
            registro[3] = item.getfecha()+"";
            registro[4] = Integer.toString(item.getTipoConsulta());
            registro[5] = item.getDescripcion();
            
            
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }
    
    public DefaultTableModel listar1() {
        List<Consultas> lista = new ArrayList();
        lista.addAll(DATOS.listar1());

        String[] titulos = {"IdConsulta", "IdCliente", "IdAbogado", "Fecha", "Tipo Consulta","Descripcion"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[6];

        this.registrosMostrados = 0;
        for (Consultas item : lista) {
            registro[0] = item.getIdConsulta()+"";
            registro[1] = item.getIdCliente()+"";
            registro[2] = item.getIdAbogado()+"";
            
            registro[3] = item.getfecha()+"";
            registro[4] = item.getTipoConsulta()+"";
            registro[5] = item.getDescripcion();
            
            
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    public String insertar(int idCliente, int idAbogado,Date fecha, int tipoConsulta,String descripcion) {
        objeto.setIdCliente(idCliente);
        objeto.setIdAbogado(idAbogado);
        objeto.setFecha(fecha);
        objeto.setTipoConsulta(tipoConsulta);
        objeto.setDescripcion(descripcion);
        if (DATOS.insertar(objeto)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public DefaultComboBoxModel llenandoCombo() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<TipoConsulta> listaConsulta = new ArrayList();
        listaConsulta = DATOSCONSULTA.seleccionar();

        for (TipoConsulta objeto : listaConsulta) {
            items.addElement(new TipoConsulta(objeto.getId(), objeto.getNombre()));
        }
        return items;
    }

    public String actualizar(int idConsulta,int idCliente, int idAbogado,Date fecha, int tipoConsulta,String descripcion) {
        objeto.setIdConsulta(idConsulta);
        objeto.setIdCliente(idCliente);
        objeto.setIdAbogado(idAbogado);
        objeto.setFecha(fecha);
        objeto.setTipoConsulta(tipoConsulta);
        objeto.setDescripcion(descripcion);
        if (DATOS.Actualizar(objeto)) {
            return "OK";
        } else {
            return "Error al actualizar";
        }
    }
    
    public String buscar(int id) {
        String nombre = "";
        List<Cliente> lista = new ArrayList();
        lista.addAll(DATOS.BuscarCliente(id));

        for (Cliente item : lista) {
            nombre = item.getNombre();
            
        }
        return nombre;
    }
    
    public String buscarAbogado(int id) {
        String nombre = "";
        List<Abogado> lista = new ArrayList();
        lista.addAll(DATOS.BuscarAbogado(id));

        for (Abogado item : lista) {
            nombre = item.getNombre();
            
        }
        return nombre;
    }


    public int totalMostrados() {
        return this.registrosMostrados;
    }
    
    public Consultas listandoUnRegistro(int id){
        return DATOS.listandoUnRegistro(id);
    }

}
