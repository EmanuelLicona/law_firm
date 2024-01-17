/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.CasoDao;
import Entidades.Abogado;
import Entidades.Caso;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alico
 */
public class CasoControl {
    
    private final CasoDao DAO;
    private Caso objeto;
    private DefaultTableModel modeloTabla;
    
    public CasoControl(){
 
        this.DAO = new CasoDao();
        this.objeto = new Caso();
    }
    
    public String insertar(int idCliente, String titulo, int idTipo, String descripcion, double honorario, int estado, 
            int idjuzgado, Date fecha, int expJuzgado,int expDPI, int expMNP){
        //System.out.println(objeto.toString());
        
        objeto.setIdCliente(idCliente);
        objeto.setNombre(titulo);
        objeto.setIdTipoCaso(idTipo);        
        objeto.setDescripcion(descripcion);
        objeto.setHonorario(honorario);
        objeto.setEstado(estado);
        objeto.setIdJuzgado(idjuzgado);
        objeto.setFechaCreacion(fecha);
        objeto.setNumExpJuzgado(expJuzgado);
        objeto.setNumExpDPI(expDPI);
        objeto.setNumExpMinPublico(expMNP);
        
        if (DAO.insertar(objeto)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }
    
    public String actualizar(int id,int idCliente, String titulo, int idTipo, String descripcion, double honorario, int estado, 
            int idjuzgado, Date fecha, int expJuzgado,int expDPI, int expMNP){
        //System.out.println(objeto.toString());
        
        objeto.setId(id);
        objeto.setIdCliente(idCliente);
        objeto.setNombre(titulo);
        objeto.setIdTipoCaso(idTipo);        
        objeto.setDescripcion(descripcion);
        objeto.setHonorario(honorario);
        objeto.setEstado(estado);
        objeto.setIdJuzgado(idjuzgado);
        objeto.setFechaCreacion(fecha);
        objeto.setNumExpJuzgado(expJuzgado);
        objeto.setNumExpDPI(expDPI);
        objeto.setNumExpMinPublico(expMNP);
        
        if (DAO.actualizar(objeto)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }
    
    public String insertarAbogados(int idCaso, int idAbogado) {
        if (DAO.insertarAbogados(idCaso, idAbogado)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }
    
     public String insertarFiscales(int idCaso, int idFiscal) {
        if (DAO.insetarFiscales(idCaso, idFiscal)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }
    
    public int listandoUltimoCaso() {

        return DAO.listandoUltimoCaso();
    }
    
    public DefaultTableModel listar() {
        List<Caso> lista = new ArrayList();

        lista.addAll(DAO.listar());

        String[] titulos = {"Id", "Titulo","Id cliente", "Descripcion", "Fecha de creacion", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[6];

        for (Caso item : lista) {

            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getIdCliente()+"";
            registro[3] = item.getDescripcion();
            registro[4] = item.getFechaCreacion()+"";
            registro[5] = item.getEstado()+"";

            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }
    
    public DefaultTableModel listar(String nombre) {
        List<Caso> lista = new ArrayList();

        lista.addAll(DAO.listar(nombre));

        String[] titulos = {"Id", "Titulo","Id cliente", "Descripcion", "Fecha de creacion", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String[] registro = new String[6];

        for (Caso item : lista) {

            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getIdCliente()+"";
            registro[3] = item.getDescripcion();
            registro[4] = item.getFechaCreacion()+"";
            registro[5] = item.getEstado()+"";

            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }
    
}
