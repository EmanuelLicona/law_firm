/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Entidades.TipoGasto;
import database.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.Types;
import javax.swing.ImageIcon;

/**
 *
 * @author Buddys
 */
public class TipoGastoDAO {

    private final Conexion CON;
    private boolean respuesta;
    private CallableStatement insertando;
    private ResultSet rs;

    public TipoGastoDAO() {
        this.CON = Conexion.getInstancia();
    }
    
    public List<TipoGasto> listar(String nombre){
        List<TipoGasto> listar  = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_TIPO_GASTO(?)}");
            insertando.setString(1, "%" + nombre + "%");
            rs = insertando.executeQuery();
            while (rs.next()) {
                listar.add(new TipoGasto(rs.getInt(1),rs.getString(2),rs.getString(3)));
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
    
    public boolean insertar(TipoGasto objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call MER_GUARDA_TIPOGASTO(?,?,?,?,?)}");
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
    
    public boolean actualizar(TipoGasto objeto){
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call MER_GUARDA_TIPOGASTO(?,?,?,?,?)}");
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
    
    public List<TipoGasto> seleccionar(){
        List<TipoGasto> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call COMBOBOX_TIPO_GASTO}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                registros.add(new TipoGasto(rs.getInt(1),rs.getString(2)));
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

}
