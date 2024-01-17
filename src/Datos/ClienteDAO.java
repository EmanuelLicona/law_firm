/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Entidades.Cliente;
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
 * @author ANGEL-GARCIA
 */
public class ClienteDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public ClienteDAO() {
        this.CON = Conexion.getInstancia();
    }

    public boolean insertar(Cliente objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call INSERTAR_CLIENTE(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            insertando.setString(1, objeto.getNombre());
            insertando.setString(2, objeto.getApellidos());
            insertando.setString(3, objeto.getNum_identidad());
            insertando.setString(4, objeto.getNum_telefono());
            insertando.setString(5, objeto.getCorreo());
            insertando.setString(6, objeto.getDomicilio());
            insertando.setDate(7, objeto.getFechaNacimiento());
            insertando.setInt(8, objeto.getEstadoCivil());
            insertando.setInt(9, objeto.getOcupacion());
            insertando.setInt(10, objeto.getEstado());
            insertando.registerOutParameter(11, Types.INTEGER);
            insertando.registerOutParameter(12, Types.INTEGER);
            insertando.registerOutParameter(13, Types.VARCHAR);
            insertando.execute();
            //if (insertando.executeUpdate() > 0) {

            //}
            // JOptionPane.showMessageDialog(null, "" + insertando.getString(13));
            respuesta = true;

            if (insertando.getInt(12) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(13), null, JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "" + insertando.getString(13), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
            }
            insertando.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "DAO");
        } finally {
            insertando = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }

    public List<Cliente> listarNombre(String nombre) {
        List<Cliente> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_REGISTROS_CLIENTES_NOMBRE(?)}");
            insertando.setString(1, "%" + nombre + "%");

            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getDate(8), rs.getInt(9), rs.getInt(10), rs.getInt(11)));
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

    public boolean editar(Cliente objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call ACTUALIZAR_CLIENTE(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            insertando.setInt(1, objeto.getIdCliente());
            insertando.setString(2, objeto.getNombre());
            insertando.setString(3, objeto.getApellidos());
            insertando.setString(4, objeto.getNum_identidad());
            insertando.setString(5, objeto.getNum_telefono());
            insertando.setString(6, objeto.getCorreo());
            insertando.setString(7, objeto.getDomicilio());
            insertando.setDate(8, objeto.getFechaNacimiento());
            insertando.setInt(9, objeto.getEstadoCivil());
            insertando.setInt(10, objeto.getOcupacion());
            insertando.setInt(11, objeto.getEstado());
            insertando.registerOutParameter(12, Types.INTEGER);
            insertando.registerOutParameter(13, Types.VARCHAR);

            insertando.execute();
            //JOptionPane.showMessageDialog(null, "" + insertando.getString(13));
            respuesta = true;

            if (insertando.getInt(12) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(13), null, JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "" + insertando.getString(13), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
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

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_REGISTROS_CLIENTES}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getDate(8), rs.getInt(9), rs.getInt(10), rs.getInt(11)));
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

    public Cliente listandoUnRegistro(int id) {
        List<Cliente> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_UN_REGISTROS_CLIENTES(?)}");
            insertando.setInt(1, id);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getDate(8), rs.getInt(9), rs.getInt(10), rs.getInt(11)));
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

        Cliente aux = new Cliente();
        for (Cliente a : lista) {
            return a;
        }
        return aux;
    }

    public List<Cliente> listarBuscar(String texto) {
        List<Cliente> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_REGISTROS_CLIENTES}");
            insertando.setString(1, "%" + texto + "%");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getDate(8), rs.getInt(9), rs.getInt(10), rs.getInt(11)));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
        } finally {

            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        return lista;
    }

    public boolean existe(int id) {

        Cliente aux = listandoUnRegistro(id);

        if (aux != null) {
            return true;
        }

        return false;
    }
}
