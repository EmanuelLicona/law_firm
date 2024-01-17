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
import Entidades.Juzgado;
import java.sql.Types;
import javax.swing.ImageIcon;

/**
 *
 * @author Buddys
 */
public class JuzgadoDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public JuzgadoDAO() {
        this.CON = Conexion.getInstancia();
    }

    public List<Juzgado> listar(String texto) {
        List<Juzgado> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_JUZGADO(?)}");
            insertando.setString(1, "%" + texto + "%");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Juzgado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
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

    public boolean insertar(Juzgado objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call MER_GUARDAJUZGADO(?,?,?,?,?,?,?)}");
            insertando.registerOutParameter(1, Types.INTEGER);
            insertando.setString(2, objeto.getNombre());
            insertando.setString(3, objeto.getUbicacion());
            insertando.setString(4, objeto.getTelefono());
            insertando.setInt(5, objeto.getIdTipoJuzgado());
            insertando.registerOutParameter(6, Types.INTEGER);
            insertando.registerOutParameter(7, Types.VARCHAR);
            insertando.execute();
           // JOptionPane.showMessageDialog(null, insertando.getString(7));
            respuesta = true;

            if (insertando.getInt(6) == 1) {
                JOptionPane.showMessageDialog(null, "" + insertando.getString(7), null, JOptionPane.ERROR_MESSAGE);
                respuesta = false;
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

    public boolean actualizar(Juzgado objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call MER_GUARDAJUZGADO(?,?,?,?,?,?,?)}");
            insertando.setInt(1, objeto.getIdJuzgado());
            insertando.setString(2, objeto.getNombre());
            insertando.setString(3, objeto.getUbicacion());
            insertando.setString(4, objeto.getTelefono());
            insertando.setInt(5, objeto.getIdTipoJuzgado());
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
    
    public Juzgado listandoUnRegistro(int id) {
        List<Juzgado> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_UN_REGISTRO_JUZGADO(?)}");
            insertando.setInt(1, id);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Juzgado(rs.getInt(1),"", rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
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

        Juzgado aux = new Juzgado();
        for (Juzgado a : lista) {
            return a;
        }
        return null;//cambie
    }
    
    public boolean existe(int id){
        
        Juzgado aux = listandoUnRegistro(id);
        
        if(aux!=null)
            return true;
            
        return false;
    }
    

}
