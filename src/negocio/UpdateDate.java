package negocio;


import java.util.Calendar;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alico
 */
public class UpdateDate implements Runnable{
    //Creamos una clase que implementa interfaz runnable
    JLabel someLabel;
    //Debemos guardar el label que queremos modificar, o en el que va el tiempo
   public UpdateDate(JLabel dateLabel){
        //Hacemos un constructor de la clase, que reciba el label que queremos actualizar
        someLabel=dateLabel;
        //Colocamos el label en la variable someLabel, para mas tarde referirlo, y modificarlo
    }
    @Override
    public void run() {
        /*Se sobreescribe el metodo run que es donde ira el codigo para obtener la fecha
        * y armar un string que es el que luego ira en el label de la fecha y hora*/
        while(true){
            /*Un loop infinito para mantener este hilo corriendo pues, si termina de ejecutarse, se cierra y los objetos Runnable no puede
            * voler a ser ejecutados*/
            try{
                //El try y catch es por la linea Thread.sleep, dado que esta funcon arroja InterruptedException
                Thread.sleep(999);
                //Obtenermos la hora que deseamos mostrar
                Calendar calendario = Calendar.getInstance();
                int hora, minutos, segundos, anio, mes,dia;
                hora = calendario.get(Calendar.HOUR_OF_DAY);
                minutos = calendario.get(Calendar.MINUTE);
                segundos = calendario.get(Calendar.SECOND);
                anio = calendario.get(Calendar.YEAR);
                mes = calendario.get(Calendar.MONTH)+1;
                dia = calendario.get(Calendar.DATE);
                //Y luego mandamos a actualizar, el label en otro runnable, ya que este se esta ejecutando en otro hilo
                //y por tanto no tiene acceso a la Interfaz grafica.
               // SwingUtilities.invokeLater(new Runnable() {
                  // @Override
                   // public void run() {
                        someLabel.setText("Fecha :"+dia+"/"+mes+"/"+anio+"    Hora :"+hora+":"+minutos+":"+segundos);
                   // }
               // });
            }catch (Exception ex){}
        }
    }
}