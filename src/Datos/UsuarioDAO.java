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
import Entidades.Usuario;
import java.sql.Types;
import javax.swing.ImageIcon;

/**
 *
 * @author Buddys
 */
public class UsuarioDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public UsuarioDAO() {
        this.CON = Conexion.getInstancia();
    }

    public List<Usuario> listar(String texto, int totalPorPagina, int numPagina) {
        List<Usuario> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_USUARIOS2(?,?,?)}");
            insertando.setString(1, "%" + texto + "%");
            insertando.setInt(2, (numPagina - 1) * totalPorPagina);
            insertando.setInt(3, totalPorPagina);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getBoolean(8), rs.getString(9)));
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
    
    public Usuario login(String usuario, String clave){
        Usuario usu = null;
        try {
            insertando = CON.conectar().prepareCall("{call SP_LOGIN(?,?)}");
            insertando.setString(1, usuario);
            insertando.setString(2, clave);
            rs = insertando.executeQuery();
            while (rs.next()) {
                usu = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getBoolean(7), rs.getString(8));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        return usu;
    }

    public boolean insertar(Usuario objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call MER_GUARDAUSUARIO(?,?,?,?,?,?,?,?,?,?)}");
            insertando.registerOutParameter(1, Types.INTEGER);
            insertando.setString(2, objeto.getNombre());
            insertando.setString(3, objeto.getApellido());
            insertando.setString(4, objeto.getIdentidad());
            insertando.setString(5, objeto.getCantrasenia());
            insertando.setInt(6, objeto.getId());
            insertando.setInt(7, 1);
            insertando.setString(8, objeto.getUsuario());

            insertando.registerOutParameter(9, Types.INTEGER);
            insertando.registerOutParameter(10, Types.VARCHAR);

            insertando.execute();
            //JOptionPane.showMessageDialog(null, insertando.getString(10),"Bufete",JOptionPane.INFORMATION_MESSAGE);
            respuesta = true;

            if (insertando.getInt(9) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(10), null, JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "" + insertando.getString(10), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
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

    public boolean actualizar(Usuario objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call MER_GUARDAUSUARIO(?,?,?,?,?,?,?,?,?,?)}");
            insertando.setInt(1, objeto.getId());
            insertando.setString(2, objeto.getNombre());
            insertando.setString(3, objeto.getApellido());
            insertando.setString(4, objeto.getIdentidad());
            insertando.setString(5, objeto.getCantrasenia());
            insertando.setInt(6, objeto.getRolId());
            insertando.setInt(7, 1);
            insertando.setString(8, objeto.getUsuario());
            insertando.registerOutParameter(9, Types.INTEGER);
            insertando.registerOutParameter(10, Types.VARCHAR);

            insertando.execute();
            //JOptionPane.showMessageDialog(null, insertando.getString(10));
            respuesta = true;

            if (insertando.getInt(9) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(10), null, JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "" + insertando.getString(10), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
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

    public int total() {
        int totalRegistros = 0;
        try {
            insertando = CON.conectar().prepareCall("{call CONTADOR()}");
            rs = insertando.executeQuery();
            totalRegistros = rs.getRow();
//            while (rs.next()) {
//                
//            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
        } finally {
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        return totalRegistros;
    }
}
