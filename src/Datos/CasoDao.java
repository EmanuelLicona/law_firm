/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import database.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import Entidades.Caso;
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
public class CasoDao {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public CasoDao() {
        this.CON = Conexion.getInstancia();
    }

    public boolean insertar(Caso objeto) {

        respuesta = false;

        try {
            insertando = CON.conectar().prepareCall("{call INSERTAR_CASO(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            insertando.setInt(1, objeto.getIdCliente());
            insertando.setString(2, objeto.getNombre());
            insertando.setInt(3, objeto.getIdTipoCaso());
            insertando.setString(4, objeto.getDescripcion());
            insertando.setDouble(5, objeto.getHonorario());
            insertando.setInt(6, objeto.getEstado());
            insertando.setInt(7, objeto.getIdJuzgado());
            insertando.setDate(8, objeto.getFechaCreacion());
            insertando.setInt(9, objeto.getNumExpJuzgado());
            insertando.setInt(10, objeto.getNumExpDPI());
            insertando.setInt(11, objeto.getNumExpMinPublico());

            insertando.registerOutParameter(12, Types.INTEGER);
            insertando.registerOutParameter(13, Types.INTEGER);
            insertando.registerOutParameter(14, Types.VARCHAR);

            insertando.execute();

            // JOptionPane.showMessageDialog(null, "aaa" + insertando.getString(14));
            respuesta = true;

            if (insertando.getInt(12) == 1) {
                JOptionPane.showMessageDialog(null, "" + insertando.getString(14), null, JOptionPane.ERROR_MESSAGE);
                respuesta = false;
            } else {
                JOptionPane.showMessageDialog(null, "Registrado Correctamente", "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "ddd" + e.getMessage());

        } finally {
            insertando = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }

    public boolean actualizar(Caso objeto) {

        respuesta = false;

        try {
            insertando = CON.conectar().prepareCall("{call ACTUALIZAR_CASO(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            insertando.setInt(1, objeto.getId());
            insertando.setInt(2, objeto.getIdCliente());
            insertando.setString(3, objeto.getNombre());
            insertando.setInt(4, objeto.getIdTipoCaso());
            insertando.setString(5, objeto.getDescripcion());
            insertando.setDouble(6, objeto.getHonorario());
            insertando.setInt(7, objeto.getEstado());
            insertando.setInt(8, objeto.getIdJuzgado());
            insertando.setDate(9, objeto.getFechaCreacion());
            insertando.setInt(10, objeto.getNumExpJuzgado());
            insertando.setInt(11, objeto.getNumExpDPI());
            insertando.setInt(12, objeto.getNumExpMinPublico());

            insertando.registerOutParameter(13, Types.INTEGER);
            insertando.registerOutParameter(14, Types.VARCHAR);

            insertando.execute();

            //JOptionPane.showMessageDialog(null, "" + insertando.getString(14));
            respuesta = true;

            if (insertando.getInt(13) == 1) {
                JOptionPane.showMessageDialog(null, "" + insertando.getString(14), null, JOptionPane.ERROR_MESSAGE);
                respuesta = false;
            } else {
                JOptionPane.showMessageDialog(null, "Registrado Correctamente", "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src\\imagenes\\confirmar.png"));
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "ddd" + e.getMessage());

        } finally {
            insertando = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }

    public boolean insetarAbogado(int idCaso, int idAbogado) {

        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call INS_CASO_ABOGADO(?,?)}");
            insertando.setInt(1, idCaso);
            insertando.setInt(2, idAbogado);

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

    public boolean insetarFiscales(int idCaso, int idFiscal) {

        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call INS_CASO_FISCAL(?,?)}");
            insertando.setInt(1, idCaso);
            insertando.setInt(2, idFiscal);

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

    public int Listar_Ultimo_Caso() {
        int num = 0;
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_ULTIMO_CASO}");
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

    public List<Caso> listar(String nombre) {

        List<Caso> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_CASOS(?)}");
            insertando.setString(1, "%" + nombre + "%");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Caso(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getDouble(6),
                        rs.getInt(7), rs.getInt(8), rs.getDate(9), rs.getInt(10),
                        rs.getInt(11), rs.getInt(12)));
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

    public List<Caso> listar() {
        List<Caso> lista = new ArrayList();

        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_TODOS_CASOS}");

            rs = insertando.executeQuery();

            while (rs.next()) {

                lista.add(new Caso(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getDouble(6),
                        rs.getInt(7), rs.getInt(8), rs.getDate(9), rs.getInt(10),
                        rs.getInt(11), rs.getInt(12)));
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

    public Caso listandoUnRegistro(int id) {
        List<Caso> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_UN_REGISTRO_CASO(?)}");
            insertando.setInt(1, id);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Caso(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getDouble(6),
                        rs.getInt(7), rs.getInt(8), rs.getDate(9), rs.getInt(10),
                        rs.getInt(11), rs.getInt(12)));
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

        Caso aux = new Caso();
        for (Caso a : lista) {
            return a;
        }
        return aux;
    }

    public boolean insertarAbogados(int idCaso, int idAbogado) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call INS_CASO_ABOGADO(?,?)}");
            insertando.setInt(1, idCaso);
            insertando.setInt(2, idAbogado);

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

    public int listandoUltimoCaso() {
        int num = 0;
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_ULTIMO_CASO}");
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

    public void eliminadoAbogados(int id) {
        try {
            insertando = CON.conectar().prepareCall("{call ELIMINAR_CASO_ABOGADO_POR_CASO(?)}");
            insertando.setInt(1, id);
            insertando.execute();

            insertando.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {

            insertando = null;
            CON.cerrarConexion();
        }
    }

    public void eliminadoFiscales(int id) {
        try {
            insertando = CON.conectar().prepareCall("{call ELIMINAR_CASO_FISCAL_POR_CASO(?)}");
            insertando.setInt(1, id);
            insertando.execute();

            insertando.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {

            insertando = null;
            CON.cerrarConexion();
        }
    }

    public List<Integer> listandoIdAbogados(int idCaso) {
        List<Integer> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_CASO_ABOGADO_POR_CASO(?)}");
            insertando.setInt(1, idCaso);
            rs = insertando.executeQuery();

            while (rs.next()) {
                lista.add(rs.getInt(2));
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

    public List<Integer> listandoIdFiscales(int idCaso) {
        List<Integer> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_CASO_FISCAL_POR_CASO(?)}");
            insertando.setInt(1, idCaso);
            rs = insertando.executeQuery();

            while (rs.next()) {
                lista.add(rs.getInt(2));
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

    public List<Integer> listandoIdTestigos(int idCaso) {
        List<Integer> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_CASO_TESTIGO_POR_CASO(?)}");
            insertando.setInt(1, idCaso);
            rs = insertando.executeQuery();

            while (rs.next()) {
                lista.add(rs.getInt(1));
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

    public List<Integer> listandoIdGastos(int idCaso) {
        List<Integer> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_IDGASTO_CASO(?)}");
            insertando.setInt(1, idCaso);
            rs = insertando.executeQuery();

            while (rs.next()) {
                lista.add(rs.getInt(2));
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

    public List<Integer> listandoIdPagos(int idCaso) {
        List<Integer> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_IDPAGO_CASO(?)}");
            insertando.setInt(1, idCaso);
            rs = insertando.executeQuery();

            while (rs.next()) {
                lista.add(rs.getInt(2));
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
