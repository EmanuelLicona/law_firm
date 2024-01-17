/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.EstadoCivilDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Entidades.EstadoCivil;

/**
 *
 * @author Buddys
 */
public class EstadoCivilControl {

    private final EstadoCivilDAO DATOS;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public EstadoCivilControl() {
        this.DATOS = new EstadoCivilDAO();
        this.registrosMostrados = 0;
    }


    public DefaultTableModel listar(String texto) {
        List<EstadoCivil> listar = new ArrayList();
        listar.addAll(DATOS.listar(texto));
        String titulos[] = {"Id", "Nombre", "Descripcion"};
        this.modeloTabla = new DefaultTableModel(null, titulos);
        String registro[] = new String[3];

        this.registrosMostrados = 0;
        for (EstadoCivil item : listar) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getDescripcion();
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }
    
    public String[] listarNombreEstadosCiviles(){
        
        List<EstadoCivil> lista = new ArrayList();
        
        lista.addAll(DATOS.listarGeneral());
       
        
        String arr[] = new String[0];
        
        for(EstadoCivil item:lista){
            
            arr = redefinirString(arr, item.getNombre());
        }
        
        return arr;
    }
    
    public String[] redefinirString(String arr[], String dato){
        
        String nuevo[] = new String[arr.length+1];
        
        int i = 0;
        for(String s : arr){
            nuevo[i] = s;
            i++;
        }
        
        nuevo[nuevo.length-1] = dato;
        
        return nuevo;
    }
    
    public int totalMostrados(){
        return this.registrosMostrados;
    }

}
