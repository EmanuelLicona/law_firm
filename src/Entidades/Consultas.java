/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

   

import java.sql.Date;

public class Consultas {
   private int id_Consulta;
   private int id_Cliente;
   private int id_Abogado;
   private Date fecha;
   private int tipoConsulta;
   private String Descripcion;
   
    public Consultas(int id_Consulta,int id_Cliente,int id_Abogado,Date fecha,int tipoConsulta,String Descripcion){
        this.id_Consulta = id_Consulta;
        this.id_Cliente = id_Cliente;
        this.id_Abogado = id_Abogado;
        this.fecha = fecha;
        this.tipoConsulta = tipoConsulta;
        this.Descripcion = Descripcion;
        
    }
    
    public Consultas(){
    
    }
    
    public int getIdConsulta(){
        return id_Consulta;
    }
    public void setIdConsulta(int id_Consulta){
        this.id_Consulta = id_Consulta;
    }
    
     public int getIdCliente(){
        return id_Cliente;
    }
    public void setIdCliente(int id_Cliente){
        this.id_Cliente = id_Cliente;
    }
    
     public int getIdAbogado(){
        return id_Abogado;
    }
    public void setIdAbogado(int id_Abogado){
        this.id_Abogado = id_Abogado;
    }
    
     public Date getfecha(){
        return fecha;
    }
    public void setFecha(Date fecha){
        this.fecha = fecha;
    }
    
    public int getTipoConsulta(){
        return tipoConsulta;
    }
    public void setTipoConsulta(int tipoConsulta){
        this.tipoConsulta = tipoConsulta;
    }
    
     public String getDescripcion(){
        return Descripcion;
    }
    public void setDescripcion(String descripcion){
        this.Descripcion=descripcion;
    }
}
