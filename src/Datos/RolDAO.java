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
import Entidades.Rol;

/**
 *
 * @author Buddys
 */
public class RolDAO {

    private final Conexion CON;
    private boolean respuesta;
    private CallableStatement insertando;
    private ResultSet rs;

    public RolDAO() {
        this.CON = Conexion.getInstancia();
    }
    
    public List<Rol> listar(String nombre){
        List<Rol> listar  = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_ROL(?)}");
            insertando.setString(1, "%" + nombre + "%");
            rs = insertando.executeQuery();
            while (rs.next()) {
                listar.add(new Rol(rs.getInt(1),rs.getString(2),rs.getString(3)));
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
    
    public boolean insertar(Rol objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call INSERTAR_ROL(?,?)}");
            insertando.setString(1, objeto.getNombre());
            insertando.setString(2, objeto.getDescripcion());
            
            if (insertando.executeUpdate() > 0) {
                respuesta = true;
            }
            insertando.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            insertando = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }
    
    public boolean actualizar(Rol objeto){
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call ACTUALIZAR_ROL(?,?,?)}");
            insertando.setInt(1, objeto.getId());
            insertando.setString(2, objeto.getNombre());
            insertando.setString(3, objeto.getDescripcion());
            
            if (insertando.executeUpdate() > 0) {
                respuesta = true;
            }
            insertando.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            insertando = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }
    
    public List<Rol> seleccionar(){
        List<Rol> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call COMBOBOX_DE_ROLES}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                registros.add(new Rol(rs.getInt(1),rs.getString(2)));
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
    
    public boolean existe(String nombre){
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call EXISTE_ROL(?)}");
            insertando.setString(1, nombre);
            rs = insertando.executeQuery();
            while (rs.next()) {
                respuesta = true;
            }
//            rs.last();
//            if (rs.getRow() > 0) {
//                respuesta = true;
//            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }

}
