
package Datos;

import Entidades.TipoConsulta;
import database.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Entidades.TipoJuzgado;


public class TipoConsultaDAO {

    private final Conexion CON;
    private boolean respuesta;
    private CallableStatement insertando;
    private ResultSet rs;

    public TipoConsultaDAO() {
        this.CON = Conexion.getInstancia();
    }
    
    public List<TipoConsulta> listar(String nombre){
        List<TipoConsulta> listar  = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_TIPO_CONSULTA(?)}");
            insertando.setString(1, "%" + nombre + "%");
            rs = insertando.executeQuery();
            while (rs.next()) {
                listar.add(new TipoConsulta(rs.getInt(1),rs.getString(2)));
            }
            insertando.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        return listar;
    }
    
    public boolean insertar(TipoConsulta objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call INSERTAR_TIPO_CONSULTA(?)}");
            insertando.setString(1, objeto.getNombre());
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
    
    public boolean actualizar(TipoConsulta objeto){
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call ACTUALIZAR_TIPO_CONSULTA(?,?)}");
            insertando.setInt(1, objeto.getId());
            insertando.setString(2, objeto.getNombre());
            if (insertando.executeUpdate() > 0) {
                respuesta = true;
            }
            insertando.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            insertando = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }
    
    public List<TipoConsulta> seleccionar(){
        List<TipoConsulta> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call COMBOBOX_TIPO_CONSULTA}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                registros.add(new TipoConsulta(rs.getInt(1),rs.getString(2)));
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            insertando =null;
            rs = null;
            CON.cerrarConexion();
        }
        return registros;
    }
    
    public boolean existe(String nombre){
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call EXISTE_TIPO_CONSULTA(?)}");
            insertando.setString(1, nombre);
            rs = insertando.executeQuery();
            while (rs.next()) {
                respuesta = true;
            }
            insertando.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            insertando = null;
            rs = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }

}
