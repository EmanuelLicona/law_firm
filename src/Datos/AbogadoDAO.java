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
import Entidades.Abogado;
import java.sql.Types;
import javax.swing.ImageIcon;

/**
 *
 * @author alico
 */
public class AbogadoDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public AbogadoDAO() {
        this.CON = Conexion.getInstancia();
    }

    public boolean insertar(Abogado objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call INSERTAR_ABOGADO(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
            insertando.setString(1, objeto.getNombre());
            insertando.setString(2, objeto.getPellidos());
            insertando.setString(3, objeto.getNum_identidad());
            insertando.setString(4, objeto.getNum_telefono());
            insertando.setString(5, objeto.getCorreo());
            insertando.setString(6, objeto.getDomicilio());
            insertando.setDate(7, objeto.getFechaNacimiento());
            insertando.setInt(8, objeto.getNum_colegiado());
            insertando.setInt(9, objeto.getEstadoCivil());
            insertando.setInt(10, objeto.getEstado());
            
            insertando.registerOutParameter(11, Types.INTEGER);
            insertando.registerOutParameter(12, Types.INTEGER);
            insertando.registerOutParameter(13, Types.VARCHAR);
            
            
             insertando.execute();  
            
                 
            //System.out.println(insertando.getInt(11)); 
            //System.out.println(insertando.getInt(12)); 
            
           // JOptionPane.showMessageDialog(null,""+insertando.getString(13));
            
            respuesta = true;
            
            if(insertando.getInt(12)==1){
                JOptionPane.showMessageDialog(null, "" + insertando.getString(13), null, JOptionPane.ERROR_MESSAGE);
                respuesta = false;
            }else{
                JOptionPane.showMessageDialog(null,""+insertando.getString(13),"", JOptionPane.PLAIN_MESSAGE,new ImageIcon("src\\imagenes\\confirmar.png"));
            }
            
            insertando.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,""+ e.getMessage());
        } finally {
            insertando = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }

    public boolean editar(Abogado objeto) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call EDITAR_ABOGADO(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            insertando.setInt(1, objeto.getIdAbogado());
            insertando.setString(2, objeto.getNombre());
            insertando.setString(3, objeto.getPellidos());
            insertando.setString(4, objeto.getNum_identidad());
            insertando.setString(5, objeto.getNum_telefono());
            insertando.setString(6, objeto.getCorreo());
            insertando.setString(7, objeto.getDomicilio());
            insertando.setDate(8, objeto.getFechaNacimiento());
            insertando.setInt(9, objeto.getNum_colegiado());
            insertando.setInt(10, objeto.getEstadoCivil());
            insertando.setInt(11, objeto.getEstado());
            
            insertando.registerOutParameter(12, Types.INTEGER);
            insertando.registerOutParameter(13, Types.VARCHAR);

            insertando.execute();  
           
            //System.out.println(insertando.getInt(12)); 
            
            //JOptionPane.showMessageDialog(null,""+insertando.getString(13));
            
            respuesta = true;
            
            if(insertando.getInt(12)==1){
                respuesta = false;
                JOptionPane.showMessageDialog(null, "" + insertando.getString(13), null, JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null,""+insertando.getString(13)+" CORRECTAMENTE","", JOptionPane.PLAIN_MESSAGE,new ImageIcon("src\\imagenes\\confirmar.png"));
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

    public boolean insertarEspecialidad(int idAbogado, int idCatagoria) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call INSERTAR_ABOGADO_ESPECIALIDAD(?,?)}");
            insertando.setInt(1, idAbogado);
            insertando.setInt(2, idCatagoria);

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

    public int Listar_Ultimo_Abogado_Ingresado() {
        int num = 0;
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_ULTIMO_ABOGADO}");
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

    public List<Abogado> listar() {
        List<Abogado> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_REGISTROS_ABOGADOS}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Abogado(rs.getInt(1), rs.getString(2), rs.getString(3),
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
    
    public List<Abogado> listarNombre(String nombre) {
        List<Abogado> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_REGISTROS_ABOGADOS_NOMBRE(?)}");
            insertando.setString(1, "%" + nombre + "%");
            
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Abogado(rs.getInt(1), rs.getString(2), rs.getString(3),
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

    public Abogado listandoUnRegistro(int id) {
        List<Abogado> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTANDO_UN_REGISTRO_ABOGADO(?)}");
            insertando.setInt(1, id);
            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Abogado(rs.getInt(1), rs.getString(2), rs.getString(3),
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

        
        for (Abogado a : lista) {
            return a;
        }
        return null;
    }

    public List<Integer> listandoIdEspecialidad(int idAbogado) {
        List<Integer> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_ESPECIALIDAD_ABOGADO(?)}");
            insertando.setInt(1, idAbogado);
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

    public boolean eliminarEspecialidad(int idAbogado) {
        respuesta = false;
        try {
            insertando = CON.conectar().prepareCall("{call ELIMINAR_ESPECIALIDAD_ABOGADO(?)}");
            insertando.setInt(1, idAbogado);

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
    
    public boolean existe(int id){
        
        Abogado aux = listandoUnRegistro(id);
        
        if(aux!=null) return true;
            
        return false;
    }
}
