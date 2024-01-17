/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.AgendaDAO;
import Entidades.Agenda;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alico
 */
public class AgendaControl {
    
    private final AgendaDAO DAO;
    private Agenda objeto;
    private DefaultTableModel modeloTabla;
    
    
    public AgendaControl() {
        this.DAO = new AgendaDAO();
        this.objeto = new Agenda();
    }
    
    public String insertar(int idCaso,String descripcion, Date fecha) {
        
        objeto.setIdCaso(idCaso);
        objeto.setDescripcion(descripcion);
        objeto.setFecha(fecha);
       

        //System.out.println(objeto.toString());
        if (DAO.insertar(objeto)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }
    
     public String actualizar(int id,String descripcion, Date fecha) {
        
        objeto.setId(id);
        objeto.setDescripcion(descripcion);
        objeto.setFecha(fecha);
       

        //System.out.println(objeto.toString());
        if (DAO.actualizar(objeto)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }
    
    public DefaultTableModel listar(int id) {
        List<Agenda> lista = new ArrayList();

        lista.addAll(DAO.listar(id));

        String[] titulos = {"ID", "Fecha", "Descripcion"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[3];

        for (Agenda item : lista) {

            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getFecha()+"";
            registro[2] = item.getDescripcion();

            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }
    
     public Agenda listarUnRegistro(int id){
         return DAO.listarUnRegistro(id);
     }
}
