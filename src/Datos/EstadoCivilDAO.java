/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import database.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Entidades.EstadoCivil;

/**
 *
 * @author Buddys
 */
public class EstadoCivilDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;

    public EstadoCivilDAO() {
        this.CON = Conexion.getInstancia();
    }
    
    public List<EstadoCivil> listar(String nombre){
        List<EstadoCivil> listar  = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_ESTADOCIVIL(?)}");
            insertando.setString(1, "%" + nombre + "%");
            rs = insertando.executeQuery();
            while (rs.next()) {
                listar.add(new EstadoCivil(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
            insertando.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        return listar;
    }
    
    public List<EstadoCivil> seleccionar(){
        List<EstadoCivil> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call COMBOBOX_ESTADOCIVIL}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                registros.add(new EstadoCivil(rs.getInt(1),rs.getString(2)));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            insertando =null;
            rs = null;
            CON.cerrarConexion();
        }
        return registros;
    }
    
    public List<EstadoCivil> listarGeneral(){
        List<EstadoCivil> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_ESTADOS_CIVILES}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new EstadoCivil(rs.getInt(1), rs.getString(2),rs.getString(3)));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        
    
        return lista;
    }


}
