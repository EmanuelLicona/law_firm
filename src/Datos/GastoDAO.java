/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Entidades.Gasto;
import database.Conexion;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import Entidades.Caso;

/**
 *
 * @author Buddys
 */
public class GastoDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public GastoDAO() {
        this.CON = Conexion.getInstancia();
    }

    public List<Gasto> listar() {
        List<Gasto> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_GASTO()}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Gasto(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDouble(4), rs.getInt(5), rs.getString(6)));
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

    public List<Gasto> buscarPago(Date fechaInicio, Date fechaFin) {
        List<Gasto> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call BUSCAR_GASTO(?,?)}");
            insertando.setDate(1, fechaInicio);
            insertando.setDate(2, fechaFin);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Gasto(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDouble(4), rs.getInt(5), rs.getString(6)));
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

    public boolean insertar(Gasto objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call INS_GASTO(?,?,?,?,?,?,?)}");
            insertando.setString(1, objeto.getComentario());
            insertando.setDouble(2, objeto.getMonto());
            insertando.setDate(3, objeto.getFecha());
            insertando.setInt(4, objeto.getIdTipoPago());
            insertando.setInt(5, objeto.getIdCaso());
            insertando.registerOutParameter(6, Types.INTEGER);
            insertando.registerOutParameter(7, Types.VARCHAR);

            insertando.execute();
           // JOptionPane.showMessageDialog(null, insertando.getString(7), "Bufete", JOptionPane.INFORMATION_MESSAGE);
            respuesta = true;
            if (insertando.getInt(6) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(7), null, JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "" + insertando.getString(7), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
            }
            insertando.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        } finally {
            insertando = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }

    public boolean actualizar(Gasto objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call ACTUALIZAR_GASTO(?,?,?,?,?,?,?,?)}");
            insertando.setInt(1, objeto.getIdGasto());
            insertando.setString(2, objeto.getComentario());
            insertando.setDouble(3, objeto.getMonto());
            insertando.setDate(4, objeto.getFecha());
            insertando.setInt(5, objeto.getIdTipoPago());
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

    public List<Gasto> listar(int id) {
        List<Gasto> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_GASTO_CASO(?)}");
            insertando.setInt(1, id);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Gasto(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDouble(4), rs.getInt(5), rs.getString(6)));
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

    public Gasto listarUnRegistro(int id) {
        List<Gasto> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_GASTO_CASO(?)}");
            insertando.setInt(1, id);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Gasto(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDouble(4), rs.getInt(5), rs.getString(6)));
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

        for (Gasto gasto : lista) {
            return gasto;
        }
        return null;
    }
    
    public Caso listarTituloCaso(int id) {
        List<Caso> lista = new ArrayList();

        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_CASO_GASTO(?)}");
            insertando.setInt(1, id);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Caso(rs.getInt(1), rs.getString(2)));
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

        for (Caso c : lista) {
            return c;
        }
        return null;
    }

}
