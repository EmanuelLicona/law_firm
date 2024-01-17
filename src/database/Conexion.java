/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Buddys
 */
public class Conexion {
    private final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String URL = "jdbc:sqlserver://localhost:1433;databaseName=AbogadosBD";
    private final String USER = "sa";
    private final String PASSWORD = "123";
    
    public Connection cadena;
    public static Conexion instancia;
    
    private Conexion() {
        this.cadena = null;
    }
    
    public Connection conectar(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.cadena = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AbogadosBD","sa","123");
            Class.forName(DRIVER);
            this.cadena = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            System.exit(0);
        }
        return this.cadena;
    }
    
    public void cerrarConexion(){
        try {
            this.cadena.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    
    public synchronized static Conexion getInstancia(){
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

//    public static Connection obtenerConexion() {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AbogadosBD","sa","123");
//            System.out.println("Conectado");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    } 
}
