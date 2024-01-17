/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Entidades.TipoCaso;
import database.Conexion;
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
 * @author alico
 */
public class TipoCasoDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;

    public TipoCasoDAO() {
        this.CON = Conexion.getInstancia();
    }

    public List<TipoCaso> listar() {
        List<TipoCaso> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_TIPO_CASO}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new TipoCaso(rs.getInt(1), rs.getString(2), rs.getString(3)));
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

    public TipoCaso listarUnRegistro(int id) {
        List<TipoCaso> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_UN_REGISTRO_TIPO_CASO(?)}");
            insertando.setInt(1, id);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new TipoCaso(rs.getInt(1), rs.getString(2), rs.getString(3)));
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

        for (TipoCaso c : lista) {
            return c;
        }

        return null;
    }

    public boolean insertar(TipoCaso objeto) {
        boolean respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call INSERTAR_TIPO_CASO(?,?,?,?,?)}");
            insertando.setString(1, objeto.getNombre());
            insertando.setString(2, objeto.getDescripcion());

            insertando.registerOutParameter(3, Types.INTEGER);
            insertando.registerOutParameter(4, Types.VARCHAR);
            insertando.registerOutParameter(5, Types.INTEGER);

            insertando.execute();

            //JOptionPane.showMessageDialog(null, "" + insertando.getString(4));

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

    public boolean actualizar(TipoCaso objeto) {
        boolean respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call ACTUALIZAR_TIPO_CASO(?,?,?,?,?)}");
            insertando.setInt(1, objeto.getId());
            insertando.setString(2, objeto.getNombre());
            insertando.setString(3, objeto.getDescripcion());

            insertando.registerOutParameter(4, Types.INTEGER);
            insertando.registerOutParameter(5, Types.VARCHAR);

            insertando.execute();

            //JOptionPane.showMessageDialog(null, "" + insertando.getString(5));

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

    public List<TipoCaso> listarNombre(String nombre) {
        List<TipoCaso> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_REGISTRO_TIPO_CASO_NOMBRE(?)}");
            insertando.setString(1, "%" + nombre + "%");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new TipoCaso(rs.getInt(1), rs.getString(2), rs.getString(3)));

                lista.toString();
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
