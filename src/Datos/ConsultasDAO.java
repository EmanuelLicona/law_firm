package Datos;

import Entidades.Abogado;
import Entidades.Cliente;
import Entidades.Consultas;
import database.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ConsultasDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public ConsultasDAO() {
        this.CON = Conexion.getInstancia();
    }

    public boolean insertar(Consultas objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call INSERTAR_CONSULTAS(?,?,?,?,?,?,?,?)}");

            insertando.setInt(1, objeto.getIdCliente());
            insertando.setInt(2, objeto.getIdAbogado());
            insertando.setDate(3, objeto.getfecha());
            insertando.setInt(4, objeto.getTipoConsulta());
            insertando.setString(5, objeto.getDescripcion());
            insertando.registerOutParameter(6, Types.INTEGER);
            insertando.registerOutParameter(7, Types.INTEGER);
            insertando.registerOutParameter(8, Types.VARCHAR);

            insertando.execute();
            //if (insertando.executeUpdate() > 0) {

            //}
            //JOptionPane.showMessageDialog(null,""+insertando.getString(8));
            respuesta = true;

            if (insertando.getInt(7) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(8), null, JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "" + insertando.getString(8), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
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

    public boolean Actualizar(Consultas objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call ACTUALIZAR_CONSULTAS(?,?,?,?,?,?,?,?)}");
            insertando.setInt(1, objeto.getIdConsulta());
            insertando.setInt(2, objeto.getIdCliente());
            insertando.setInt(3, objeto.getIdAbogado());
            insertando.setDate(4, objeto.getfecha());
            insertando.setInt(5, objeto.getTipoConsulta());
            insertando.setString(6, objeto.getDescripcion());
            insertando.registerOutParameter(7, Types.INTEGER);
            insertando.registerOutParameter(8, Types.VARCHAR);

            insertando.execute();
            //JOptionPane.showMessageDialog(null, "" + insertando.getString(8));
            respuesta = true;

            if (insertando.getInt(7) == 1) {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(8), null, JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "" + insertando.getString(8), "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
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

    public List<Consultas> listar(String texto) {
        List<Consultas> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_CONSULTA_NOMBRE(?)}");
            insertando.setString(1, "%" + texto + "%");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Consultas(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getInt(5),
                        rs.getString(6)));
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

    public List<Consultas> listar1() {
        List<Consultas> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_CONSULTAS}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Consultas(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getInt(5),
                        rs.getString(6)));
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

    public Consultas listandoUnRegistro(int id) {
        List<Consultas> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_UN_REGISTROS_CONSULTAS(?)}");
            insertando.setInt(1, id);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Consultas(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getInt(5),
                        rs.getString(6)));
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

        Consultas aux = new Consultas();
        for (Consultas a : lista) {
            return a;
        }
        return aux;
    }

    public List<Cliente> BuscarCliente(int id) {
        List<Cliente> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call BUSCAR_CLIENTE(?)}");
            insertando.setInt(1, id);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt(1), rs.getString(2)));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());
        } finally {
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        return lista;
    }

    public List<Abogado> BuscarAbogado(int id) {
        List<Abogado> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call BUSCAR_ABOGADO(?)}");
            insertando.setInt(1, id);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Abogado(rs.getInt(1), rs.getString(2)));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());
        } finally {
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        return lista;
    }

}
