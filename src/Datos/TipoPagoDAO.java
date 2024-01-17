/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import database.Conexion;
import Entidades.TipoPago;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Buddys
 */
public class TipoPagoDAO {

    private final Conexion CON;
    private boolean respuesta;
    private CallableStatement insertando;
    private ResultSet rs;

    public TipoPagoDAO() {
        this.CON = Conexion.getInstancia();
    }
    
    public List<TipoPago> listar(){
        List<TipoPago> listar  = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call LISTAR_TIPO_PAGO}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                listar.add(new TipoPago(rs.getInt(1),rs.getString(2),rs.getString(3)));
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
    
    public List<TipoPago> seleccionar(){
        List<TipoPago> registros = new ArrayList();
        try {
            insertando = CON.conectar().prepareCall("{call COMBOBOX_TP_PAGO}");
            rs = insertando.executeQuery();
            while (rs.next()) {
                registros.add(new TipoPago(rs.getInt(1),rs.getString(2)));
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
    


}
