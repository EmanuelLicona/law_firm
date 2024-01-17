/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import database.Conexion;
import Entidades.Caso;
import Entidades.Evidencia;
import Entidades.TipoEvidencia;
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
public class EvidenciaDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public EvidenciaDAO() {
        this.CON = Conexion.getInstancia();
    }

    public List<Evidencia> listar(String texto) {
        List<Evidencia> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_EVIDENCIA(?)}");
            insertando.setString(1, "%" + texto + "%");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Evidencia(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getInt(7), rs.getInt(8)));
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
    
    public List<Evidencia> listarIdCaso(int id) {
        List<Evidencia> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_EVIDENCIA_CASO(?)}");
            insertando.setInt(1, id);
            
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Evidencia(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getInt(7), rs.getInt(8)));
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

    public boolean insertar(Evidencia objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call MER_GUARDAEVIDENCIA(?,?,?,?,?,?,?,?)}");
            insertando.registerOutParameter(1, Types.INTEGER);
            insertando.setString(2, objeto.getComentario());
            insertando.setString(3, objeto.getUrlEvidencia());
            insertando.setInt(4, objeto.getIdTipoEvidencia());
            insertando.setDate(5, objeto.getFecha());
            insertando.setInt(6, objeto.getIdCaso());
            insertando.registerOutParameter(7, Types.INTEGER);
            insertando.registerOutParameter(8, Types.VARCHAR);

            insertando.execute();
//            JOptionPane.showMessageDialog(null, insertando.getString(8));
            respuesta = true;

            if (insertando.getInt(7) == 1) {
                respuesta = false;
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

    public List<Caso> BuscarCaso(int id) {
        List<Caso> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call BUSCAR_CASO(?)}");
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
        return lista;
    }

    public List<TipoEvidencia> comboboxTipoEvidencia() {
        List<TipoEvidencia> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call COMBOBOX_TIPO_EVIDENCIA}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                registros.add(new TipoEvidencia(rs.getInt(1), rs.getString(2)));
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

    public boolean actualizar(Evidencia objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call MER_GUARDAEVIDENCIA(?,?,?,?,?,?,?,?)}");
            insertando.setInt(1, objeto.getIdEvidencia());
            insertando.setString(2, objeto.getComentario());
            insertando.setString(3, objeto.getUrlEvidencia());
            insertando.setInt(4, objeto.getIdTipoEvidencia());
            insertando.setDate(5, objeto.getFecha());
            insertando.setInt(6, objeto.getIdCaso());;
            insertando.registerOutParameter(7, Types.INTEGER);
            insertando.registerOutParameter(8, Types.VARCHAR);

            insertando.execute();
//            JOptionPane.showMessageDialog(null, insertando.getString(8));
            respuesta = true;

            if (insertando.getInt(7) == 1) {
                respuesta = false;
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

}
