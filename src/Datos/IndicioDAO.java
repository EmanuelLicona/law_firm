/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import database.Conexion;
import Entidades.Caso;
import Entidades.Indicio;
import Entidades.TipoIndicio;
import java.sql.CallableStatement;
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
public class IndicioDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public IndicioDAO() {
        this.CON = Conexion.getInstancia();
    }

    public List<Indicio> listar(String texto) {
        List<Indicio> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_INDICIO(?)}");
            insertando.setString(1, "%" + texto + "%");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Indicio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getInt(7), rs.getInt(8)));
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
    
     public List<Indicio> listarIdCaso(int id) {
        List<Indicio> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_INDICIO_CASO(?)}");
            insertando.setInt(1, id);
            
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Indicio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getInt(7), rs.getInt(8)));
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

    public boolean insertar(Indicio objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call MER_GUARDAINDICIO(?,?,?,?,?,?,?,?)}");
            insertando.registerOutParameter(1, Types.INTEGER);
            insertando.setString(2, objeto.getComentario());
            insertando.setString(3, objeto.getUrlIndicio());
            insertando.setInt(4, objeto.getIdTipoindicio());
            insertando.setDate(5, objeto.getFecha());
            insertando.setInt(6, objeto.getIdCaso());
            insertando.registerOutParameter(7, Types.INTEGER);
            insertando.registerOutParameter(8, Types.VARCHAR);

            insertando.execute();
            //JOptionPane.showMessageDialog(null, insertando.getString(8));
            respuesta = true;

            if (insertando.getInt(7) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(8), null, JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "" + insertando.getString(8), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
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

    public List<Caso> comboboxCaso() {
        List<Caso> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call COMBOBOX_CASO_EVIDENCIA}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                registros.add(new Caso(rs.getInt(1), rs.getString(2)));
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
        return registros;
    }

    public List<TipoIndicio> comboboxTipoIndicio() {
        List<TipoIndicio> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call COMBOBOX_INDICIO}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                registros.add(new TipoIndicio(rs.getInt(1), rs.getString(2)));
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
        return registros;
    }

    public boolean actualizar(Indicio objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call MER_GUARDAINDICIO(?,?,?,?,?,?,?,?)}");
            insertando.setInt(1, objeto.getIdIndicio());
            insertando.setString(2, objeto.getComentario());
            insertando.setString(3, objeto.getUrlIndicio());
            insertando.setInt(4, objeto.getIdTipoindicio());
            insertando.setDate(5, objeto.getFecha());
            insertando.setInt(6, objeto.getIdCaso());;
            insertando.registerOutParameter(7, Types.INTEGER);
            insertando.registerOutParameter(8, Types.VARCHAR);

            insertando.execute();
           // JOptionPane.showMessageDialog(null, insertando.getString(8));
            respuesta = true;

            if (insertando.getInt(7) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(8), null, JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "" + insertando.getString(8), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
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

    public List<Caso> buscarCaso(int id) {
        List<Caso> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call BUSCAR_CASO(?)}");
            insertando.setInt(1, id);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Caso(rs.getInt(1),rs.getString(2)));
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

}
