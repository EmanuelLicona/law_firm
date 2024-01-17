/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Entidades.Consulta;
import database.Conexion;
import Entidades.Pago;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Buddys
 */
public class PagoDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public PagoDAO() {
        this.CON = Conexion.getInstancia();
    }

    public List<Pago> listar() {
        List<Pago> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_PAGO()}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Pago(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDate(4), rs.getString(5),rs.getInt(6)));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        return lista;
    }
    
    public List<Pago> listar(int id) {
        List<Pago> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_PAGO_CASO(?)}");
            insertando.setInt(1, id);
            rs = insertando.executeQuery();
            while (rs.next()) {
                 lista.add(new Pago(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDate(4), rs.getString(5),rs.getInt(6)));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        return lista;
    }
    
    public List<Pago> buscarPago(Date fechaInicio, Date fechaFin) {
        List<Pago> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call BUSCAR_PAGO(?,?)}");
            insertando.setDate(1, fechaInicio);
            insertando.setDate(2, fechaFin);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Pago(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDate(4), rs.getString(5),rs.getInt(6)));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),null,JOptionPane.ERROR_MESSAGE);
        } finally {
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        return lista;
    }

    public List<Consulta> BuscarConsulta(int id){
         List<Consulta> lista = new ArrayList();
         try {
             insertando = CON.conectar().prepareCall("{call BUSCAR_CONSULTA2(?)}");
             insertando.setInt(1, id);
             rs = insertando.executeQuery();
             while (rs.next()) {
                lista.add(new Consulta(rs.getInt(1),rs.getString(2))); 
                 //JOptionPane.showMessageDialog(null, rs.getString(2));
             } insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
         
         
        return lista;
    }
    
    public boolean insertar(Pago objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call INS_PAGO(?,?,?,?,?,?,?,?,?)}");
//            insertando.registerOutParameter(1, Types.INTEGER);
            insertando.setString(1, objeto.getComentario());            
            insertando.setDouble(2, objeto.getMonto());
            insertando.setDate(3, objeto.getFecha());
            insertando.setInt(4, objeto.getIdTipoPago());                       
            insertando.setInt(5, objeto.getIdCaso());                       
            insertando.setInt(6, objeto.getIdConsulta());                       
            insertando.setInt(7, objeto.getIdUsuario());                       
            insertando.registerOutParameter(8, Types.INTEGER);           
            insertando.registerOutParameter(9, Types.VARCHAR);
            
            insertando.execute();            
            //JOptionPane.showMessageDialog(null, insertando.getString(9),"Bufete",JOptionPane.INFORMATION_MESSAGE);
            respuesta = true;  
            
            if (insertando.getInt(8) == 1) {
                JOptionPane.showMessageDialog(null, "" + insertando.getString(9), null, JOptionPane.ERROR_MESSAGE);
                respuesta = false;           
            }else{
                JOptionPane.showMessageDialog(null, "" + insertando.getString(9), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
            }
            insertando.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: "+e.getMessage());
        } finally {
            insertando = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }

    public boolean actualizar(Pago objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call MER_GUARDAPAGO(?,?,?,?,?,?,?)}");
            insertando.setInt(1, objeto.getIdPago());
            insertando.setString(2, objeto.getComentario());
            insertando.setDouble(3, objeto.getMonto());
            insertando.setDate(4, objeto.getFecha());
            insertando.setInt(5, objeto.getIdTipoPago());
            insertando.registerOutParameter(6, Types.INTEGER);
            insertando.registerOutParameter(7, Types.VARCHAR);
            insertando.execute(); 
            //JOptionPane.showMessageDialog(null, insertando.getString(7));
            respuesta = true;
           
            if (insertando.getInt(6) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(7), null, JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "" + insertando.getString(7), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
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

    public boolean activar(int id) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call ESTADO_USUARIO(?,1)}");
            insertando.setInt(1, id);
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

    public boolean desactivar(int id) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call ESTADO_USUARIO(?,0)}");
            insertando.setInt(1, id);
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
    
    public Pago listarUnRegistro(int id) {
        List<Pago> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_PAGO_CASO(?)}");
            insertando.setInt(1, id);
            rs = insertando.executeQuery();
            while (rs.next()) {
               lista.add(new Pago(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDate(4), rs.getString(5),rs.getInt(6)));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        
          for (Pago gasto : lista) {
              return gasto;
          }
        return null;
    }

}
