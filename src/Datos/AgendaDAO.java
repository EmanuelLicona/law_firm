/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Entidades.Agenda;
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
 * @author alico
 */
public class AgendaDAO {

    private final Conexion CON;
    private CallableStatement insertando;
    private ResultSet rs;
    private boolean respuesta;

    public AgendaDAO() {
        this.CON = Conexion.getInstancia();
    }

    public boolean insertar(Agenda objeto) {

        respuesta = false;

        try {
            insertando = CON.conectar().prepareCall("{call INSERTAR_AGENDA(?,?,?,?,?,?)}");

            insertando.setInt(1, objeto.getIdCaso());
            insertando.setString(2, objeto.getDescripcion());
            insertando.setDate(3, objeto.getFecha());

            insertando.registerOutParameter(4, Types.INTEGER);
            insertando.registerOutParameter(5, Types.INTEGER);
            insertando.registerOutParameter(6, Types.VARCHAR);

            insertando.execute();

            //JOptionPane.showMessageDialog(null, "" + insertando.getString(6));

            respuesta = true;

            if (insertando.getInt(5) == 1) {
                JOptionPane.showMessageDialog(null, "" + insertando.getString(6),null,JOptionPane.ERROR_MESSAGE);
                respuesta = false;
            }else{
                JOptionPane.showMessageDialog(null,"Agregado","", JOptionPane.PLAIN_MESSAGE,new ImageIcon("src\\imagenes\\confirmar.png"));
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "" + e.getMessage());

        } finally {
            insertando = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }
    
    public boolean actualizar(Agenda objeto) {

        respuesta = false;

        try {
            insertando = CON.conectar().prepareCall("{call ACTUALIZAR_AGENDA(?,?,?,?,?)}");

            insertando.setInt(1, objeto.getId());
            insertando.setString(2, objeto.getDescripcion());
            insertando.setDate(3, objeto.getFecha());

            insertando.registerOutParameter(4, Types.INTEGER);
            insertando.registerOutParameter(5, Types.VARCHAR);

            insertando.execute();

            //JOptionPane.showMessageDialog(null, "" + insertando.getString(5));

            respuesta = true;

            if (insertando.getInt(4) == 1) {
                respuesta = false;
                 JOptionPane.showMessageDialog(null, "" + insertando.getString(5),null,JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null,""+insertando.getString(5),"", JOptionPane.PLAIN_MESSAGE,new ImageIcon("src\\imagenes\\confirmar.png"));
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "" + e.getMessage());

        } finally {
            insertando = null;
            CON.cerrarConexion();
        }
        return respuesta;
    }

    public List<Agenda> listar(int id) {
        List<Agenda> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_AGENDA_CASO(?)}");
            insertando.setInt(1, id);

            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Agenda(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getInt(4)));
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

    public Agenda listarUnRegistro(int id) {
        List<Agenda> lista = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_UN_AGENDA_CASO(?)}");
            insertando.setInt(1, id);

            rs = insertando.executeQuery();
            while (rs.next()) {
                lista.add(new Agenda(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getInt(4)));
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
        
        for(Agenda item : lista){
            return item;
        }
        return null;
    }

}
