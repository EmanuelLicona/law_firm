/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Entidades.CategoriaAbogado;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import database.Conexion;
import Entidades.TipoFiscal;
import java.sql.Types;
import javax.swing.ImageIcon;

/**
 *
 * @author alico
 */
public class TipoFiscalDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;

    public TipoFiscalDAO() {
        this.CON = Conexion.getInstancia();
    }

    public List<TipoFiscal> listar() {
        List<TipoFiscal> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_TIPO_FISCAL}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new TipoFiscal(rs.getInt(1), rs.getString(2), rs.getString(3)));
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

    public TipoFiscal listarUnRegistro(int id) {
        List<TipoFiscal> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_UN_REGISTRO_TIPO_FISCAL(?)}");
            insertando.setInt(1, id);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new TipoFiscal(rs.getInt(1), rs.getString(2), rs.getString(3)));
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

        for (TipoFiscal c : lista) {
            return c;
        }

        return null;
    }

    public boolean insertar(TipoFiscal objeto) {
        boolean respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call INSERTAR_TIPO_FISCAL(?,?,?,?,?)}");
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

    public boolean actualizar(TipoFiscal objeto) {
        boolean respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call ACTUALIZAR_TIPO_FISCAL(?,?,?,?,?)}");
            insertando.setInt(1, objeto.getId());
            insertando.setString(2, objeto.getNombre());
            insertando.setString(3, objeto.getDescripcion());

            insertando.registerOutParameter(4, Types.INTEGER);
            insertando.registerOutParameter(5, Types.VARCHAR);

            insertando.execute();

            JOptionPane.showMessageDialog(null, "" + insertando.getString(5));

            respuesta = true;

            if (insertando.getInt(4) == 1) {
                respuesta = false;
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

    public List<CategoriaAbogado> listarNombre(String nombre) {
        List<CategoriaAbogado> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_REGISTRO_TIPO_FISCAL_NOMBRE(?)}");
            insertando.setString(1, "%" + nombre + "%");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new CategoriaAbogado(rs.getInt(1), rs.getString(2), rs.getString(3)));

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
