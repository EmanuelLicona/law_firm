/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import database.Conexion;
import Entidades.Fiscal;
import java.sql.Types;
import javax.swing.ImageIcon;

/**
 *
 * @author alico
 */
public class FiscalDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public FiscalDAO() {
        this.CON = Conexion.getInstancia();
    }

    public boolean insertar(Fiscal objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call INSERTAR_FISCAL(?,?,?,?,?,?,?,?,?,?,?,?)}");
            insertando.setString(1, objeto.getNombre());
            insertando.setString(2, objeto.getApellidos());
            insertando.setString(3, objeto.getIdentidad());
            insertando.setString(4, objeto.getTelefono());
            insertando.setString(5, objeto.getCorreo());
            insertando.setInt(6, objeto.getNum_colegiado());
            insertando.setInt(7, objeto.getTipoFiscal());
            insertando.setInt(8, objeto.getEstadoCivil());
            insertando.setInt(9, objeto.getEstado());

            insertando.registerOutParameter(10, Types.INTEGER);
            insertando.registerOutParameter(11, Types.INTEGER);
            insertando.registerOutParameter(12, Types.VARCHAR);

            insertando.execute();

            //JOptionPane.showMessageDialog(null, "" + insertando.getString(12));

            respuesta = true;

            if (insertando.getInt(11) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(12), null, JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "" + insertando.getString(12), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
            }

            insertando.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            insertando = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }

    public boolean editar(Fiscal objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call EDITAR_FISCAL(?,?,?,?,?,?,?,?,?,?,?,?)}");
            insertando.setInt(1, objeto.getIdFiscal());
            insertando.setString(2, objeto.getNombre());
            insertando.setString(3, objeto.getApellidos());
            insertando.setString(4, objeto.getIdentidad());
            insertando.setString(5, objeto.getTelefono());
            insertando.setString(6, objeto.getCorreo());
            insertando.setInt(7, objeto.getNum_colegiado());
            insertando.setInt(8, objeto.getTipoFiscal());
            insertando.setInt(9, objeto.getEstadoCivil());
            insertando.setInt(10, objeto.getEstado());

            insertando.registerOutParameter(11, Types.INTEGER);
            insertando.registerOutParameter(12, Types.VARCHAR);

            insertando.execute();

           // JOptionPane.showMessageDialog(null, "" + insertando.getString(12));

            respuesta = true;

            if (insertando.getInt(11) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(12), null, JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "" + insertando.getString(12), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
            }

            insertando.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            insertando = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }

    public List<Fiscal> listar() {
        List<Fiscal> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_REGISTROS_FISCAL}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Fiscal(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7),
                        rs.getInt(8), rs.getInt(9), rs.getInt(10)));
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

    public List<Fiscal> listarNombre(String nombre) {
        List<Fiscal> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_REGISTROS_FISCAL_NOMBRE(?)}");
            insertando.setString(1, "%" + nombre + "%");
            rs = insertando.executeQuery();
            while (rs.next()) {

                lista.add(new Fiscal(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7),
                        rs.getInt(8), rs.getInt(9), rs.getInt(10)));

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

    public Fiscal listandoUnRegistro(int id) {
        List<Fiscal> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_UN_REGISTRO_FISCAL(?)}");
            insertando.setInt(1, id);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Fiscal(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7),
                        rs.getInt(8), rs.getInt(9), rs.getInt(10)));
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

        Fiscal aux = new Fiscal();
        for (Fiscal a : lista) {
            return a;
        }
        return null;
    }

    public int Listar_Ultimo_Fiscal_Ingresado() {
        int num = 0;
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_ULTIMO_FISCAL}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                num = rs.getInt(1);
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

        return num;
    }

    public boolean existe(int id) {

        Fiscal aux = listandoUnRegistro(id);

        if (aux != null) {
            return true;
        }

        return false;
    }
}
