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
import Entidades.Testigo;
import java.sql.Types;

/**
 *
 * @author Buddys
 */
public class TestigoDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public TestigoDAO() {
        this.CON = Conexion.getInstancia();
    }

    public List<Testigo> listar(String texto) {
        List<Testigo> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_TESTIGO(?)}");
            insertando.setString(1, "%" + texto + "%");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Testigo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getDate(13), rs.getInt(14), rs.getInt(15), rs.getInt(16), rs.getBoolean(17)));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "AQUI ERROR" + e.getMessage());
        } finally {
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        return lista;
    }
    
    public Testigo listarUnTestigo(int texto) {
        List<Testigo> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_UN_TESTIGO(?)}");
            insertando.setInt(1, texto);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Testigo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8),rs.getInt(9),rs.getInt(10),rs.getString(11),rs.getInt(12),rs.getInt(13)));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "AQUI ERROR" + e.getMessage());
        } finally {
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        
        for (Testigo testigo : lista) {
            return testigo;
        }
        return null;
    }

    public String insertar(Testigo objeto) throws SQLException {
        try {
            
            if (objeto.getNombre() != null) {
                insertando = CON.conectar().prepareCall("{call INSERTAR_TESTIGO(0,?,?,?,?,?,?,?,?,?,?,1,?)}");
                insertando.setString(1, objeto.getNombre());
                insertando.setString(2, objeto.getApellido());
                insertando.setString(3, objeto.getIdentidad());
                insertando.setString(4, objeto.getTelefono());
                insertando.setString(5, objeto.getCorreo());
                insertando.setString(6, objeto.getDomicilio());
                insertando.setDate(7, objeto.getFechaNacimiento());
                insertando.setInt(8, objeto.getIdEstadoCivil());
                insertando.setInt(9, objeto.getIdOcupacion());
                insertando.setInt(10, objeto.getIdTipoTestigo());
                insertando.setInt(11, objeto.getIdCaso());
            }else{
                 insertando = CON.conectar().prepareCall("{call INSERTAR_TESTIGO2(?,?,?)}");
                 insertando.registerOutParameter(1, Types.INTEGER);
                insertando.setInt(2, objeto.getIdTipoTestigo());
                insertando.setInt(3, objeto.getIdCaso());
                
            }

            insertando.execute();
            insertando.close();
            CON.cerrarConexion();
        } catch (SQLException e) {
            throw new SQLException("ERROR " + e.getMessage());
        } finally {
            insertando = null;
            CON.cerrarConexion();
        }
        return null;
    }

    public String actualizar(Testigo objeto) throws SQLException {
        try {
            insertando = CON.conectar().prepareCall("{call ACTUALIZAR_TESTIGO(?,?,?,?,?,?,?,?,?,?,?,?)}");
            insertando.setInt(1, objeto.getId());
            insertando.setString(2, objeto.getNombre());
            insertando.setString(3, objeto.getApellido());
            insertando.setString(4, objeto.getIdentidad());
            insertando.setString(5, objeto.getTelefono());
            insertando.setString(6, objeto.getCorreo());
            insertando.setString(7, objeto.getDomicilio());
            insertando.setDate(8, objeto.getFechaNacimiento());
            insertando.setInt(9, objeto.getIdEstadoCivil());
            insertando.setInt(10, objeto.getIdOcupacion());
            insertando.setInt(11, objeto.getIdTipoTestigo());
            insertando.setInt(12, objeto.getIdCaso());
            insertando.execute();
            insertando.close();
        } catch (SQLException e) {
            throw new SQLException("ERROR 5757 " + e.getMessage());
        } finally {
            insertando = null;
            CON.cerrarConexion();
        }
        return null;
    }

    public boolean activar(int id) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call ESTADO_TESTIGO(?,1)}");
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
            insertando = CON.conectar().prepareCall("{call ESTADO_TESTIGO(?,0)}");
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
