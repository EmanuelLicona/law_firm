/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import Datos.TipoPagoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Entidades.TipoPago;

/**
 *
 * @author Buddys
 */
public class TipoPagoControl {

    private final TipoPagoDAO DATOS;
    private TipoPago objeto;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public TipoPagoControl() {
        this.DATOS = new TipoPagoDAO();
        this.objeto = new TipoPago();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar() {
        List<TipoPago> listar = new ArrayList();
        listar.addAll(DATOS.listar());
        String titulos[] = {"Id", "Nombre", "Descripción"};
        this.modeloTabla = new DefaultTableModel(null, titulos);
        String registro[] = new String[3];

        this.registrosMostrados = 0;
        for (TipoPago item : listar) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getDescripcion();
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }

}
