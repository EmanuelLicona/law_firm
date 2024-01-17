/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Datos.CategoriaAbogadoDAO;
import Entidades.CategoriaAbogado;

/**
 *
 * @author alico
 */
public class CategoriaAbogadoControl {
    private final CategoriaAbogadoDAO DAO;
    private CategoriaAbogado objeto;
    private DefaultTableModel modeloTabla;
    
    public CategoriaAbogadoControl() {
        this.DAO = new CategoriaAbogadoDAO();
        this.objeto = new CategoriaAbogado();
    } 
    
    
    public DefaultTableModel listar() {
        List<CategoriaAbogado> lista = new ArrayList();
        
        lista.addAll(DAO.listar());

        String[] titulos = {"ID","Nombre", "Descripcion" };
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
    
    public DefaultTableModel listarNombre(String nombre) {
        List<CategoriaAbogado> lista = new ArrayList();
        
        lista.addAll(DAO.listarNombre(nombre));

        String[] titulos = {"ID","Nombre", "Descripcion" };
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
    
    public CategoriaAbogado listarUnRegistro(int id){
        return DAO.listarUnRegistro(id);
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
}
