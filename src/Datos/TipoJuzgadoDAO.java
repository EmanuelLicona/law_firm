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
import Entidades.TipoJuzgado;
import java.sql.Types;
import javax.swing.ImageIcon;

/**
 *
 * @author Buddys
 */
public class TipoJuzgadoDAO {

    private final Conexion CON;
    private boolean respuesta;
    private CallableStatement insertando;
    private ResultSet rs;

    public TipoJuzgadoDAO() {
        this.CON = Conexion.getInstancia();
    }
    
    public List<TipoJuzgado> listar(String nombre){
        List<TipoJuzgado> listar  = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_TIPO_JUZGADO(?)}");
            insertando.setString(1, "%" + nombre + "%");
            rs = insertando.executeQuery();
            while (rs.next()) {
                listar.add(new TipoJuzgado(rs.getInt(1),rs.getString(2)));
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
    
    public boolean insertar(TipoJuzgado objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call MER_GUARDA_TIPOJUZGADO(?,?,?,?)}");
            insertando.registerOutParameter(1, Types.INTEGER);
            insertando.setString(2, objeto.getNombre());
            insertando.registerOutParameter(3, Types.INTEGER);
            insertando.registerOutParameter(4, Types.VARCHAR);
            
            insertando.execute(); 
            //JOptionPane.showMessageDialog(null, insertando.getString(4));
            respuesta = true;
           
            if (insertando.getInt(3) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(4), null, JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "" + insertando.getString(4), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
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
    
    public boolean actualizar(TipoJuzgado objeto){
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call MER_GUARDA_TIPOJUZGADO(?,?,?,?)}");
            insertando.setInt(1, objeto.getId());
            insertando.setString(2, objeto.getNombre());
            insertando.registerOutParameter(3, Types.INTEGER);
            insertando.registerOutParameter(4, Types.VARCHAR);
            
            insertando.execute(); 
            //JOptionPane.showMessageDialog(null, insertando.getString(4));
            respuesta = true;
           
            if (insertando.getInt(3) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(4), null, JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "" + insertando.getString(4), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
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
    
    public List<TipoJuzgado> seleccionar(){
        List<TipoJuzgado> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call COMBOBOX_TIPO_JUZGADO}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                registros.add(new TipoJuzgado(rs.getInt(1),rs.getString(2)));
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
            insertando = CON.conectar().prepareCall("{call EXISTE_TIPO_JUZGADO(?)}");
            insertando.setString(1, nombre);
            rs = insertando.executeQuery();
            while (rs.next()) {
                respuesta = true;
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
        return respuesta;
    }

}
