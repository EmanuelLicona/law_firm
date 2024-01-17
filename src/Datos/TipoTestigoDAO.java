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
import Entidades.TipoTestigo;
import java.sql.Types;
import javax.swing.ImageIcon;

/**
 *
 * @author Buddys
 */
public class TipoTestigoDAO {

    private final Conexion CON;
    private boolean respuesta;
    private CallableStatement insertando;
    private ResultSet rs;

    public TipoTestigoDAO() {
        this.CON = Conexion.getInstancia();
    }
    
    public List<TipoTestigo> listar(String nombre){
        List<TipoTestigo> listar  = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_TIPOTESTIGO(?)}");
            insertando.setString(1, "%" + nombre + "%");
            rs = insertando.executeQuery();
            while (rs.next()) {
                listar.add(new TipoTestigo(rs.getInt(1),rs.getString(2),rs.getString(3)));
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
    
    public boolean insertar(TipoTestigo objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call MER_GUARDA_TIPOTESTIGO(?,?,?,?,?)}");
            insertando.registerOutParameter(1, Types.INTEGER);
            insertando.setString(2, objeto.getNombre());
            insertando.setString(3, objeto.getDescripcion());
            insertando.registerOutParameter(4, Types.INTEGER);
            insertando.registerOutParameter(5, Types.VARCHAR);
            
            insertando.execute(); 
            //JOptionPane.showMessageDialog(null, insertando.getString(5));
            respuesta = true;
           
            if (insertando.getInt(4) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(5), null, JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "" + insertando.getString(5), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
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
    
    public boolean actualizar(TipoTestigo objeto){
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call MER_GUARDA_TIPOTESTIGO(?,?,?,?,?)}");
            insertando.setInt(1, objeto.getId());
            insertando.setString(2, objeto.getNombre());
            insertando.setString(3, objeto.getDescripcion());
            insertando.registerOutParameter(4, Types.INTEGER);
            insertando.registerOutParameter(5, Types.VARCHAR);
            
            insertando.execute();
            //JOptionPane.showMessageDialog(null, insertando.getString(5));
            respuesta = true;
            
            if (insertando.getInt(4) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(5), null, JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "" + insertando.getString(5), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
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
    
    public List<TipoTestigo> seleccionar(){
        List<TipoTestigo> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call COMBOBOX_TIPO_TESTIGO}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                registros.add(new TipoTestigo(rs.getInt(1),rs.getString(2)));
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
            insertando = CON.conectar().prepareCall("{call EXISTE_TPTESTIGO(?)}");
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
