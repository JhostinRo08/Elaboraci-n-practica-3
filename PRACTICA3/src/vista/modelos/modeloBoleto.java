/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modelos;

import controlador.TDA.Listas.DynamicList;
import modelos.Pasajeros;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jhostin Roja
 */
public class modeloBoleto extends AbstractTableModel {
    private DynamicList<Pasajeros> listadePasajeros;

    public DynamicList<Pasajeros> getListadePasajeros() {
        return listadePasajeros;
    }

    public void setListadePasajeros(DynamicList<Pasajeros> listadePasajeros) {
        this.listadePasajeros = listadePasajeros;
    }
    
    public int getRowCount(){
        return listadePasajeros.getLength();
    }
    
    public int getColumnCount(){
        return 10;
    }
    @Override
    public Object getValueAt(int Fila, int Columna) {

        try {
            Pasajeros p = listadePasajeros.getInfo(Fila);

            switch (Columna) {
                case 0:
                    return (p != null) ? p.getIdPasajeros() : "";
                case 1:
                    return (p != null) ? p.getDNI() : "";
                case 2:
                    return (p != null) ? p.getApellidos() : "";
                case 3:
                    return (p != null) ? p.getNombres() : "";
                case 4:
                    return (p != null) ? p.getDatosboleto().getSalida(): "";
                case 5:
                    return (p != null) ? p.getDatosboleto().getLlegada(): "";
                case 6:
                    return (p != null) ? p.getDatosboleto().getFechadeSalida(): "";
                case 7:
                    return (p != null) ? p.getDatosboleto().getBoletosComprados(): "";
                case 8:
                    return (p != null) ? p.getDatosboleto().getHoradeEmbarque(): "";
                case 9:
                    return (p != null) ? p.getDatosboleto().getPrecioTotal(): "";
                default:
                    return null;
            }
        } catch (Exception ex) {
        }
        return listadePasajeros;
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID VENTA";
            case 1:
                return "DNI";
            case 2:
                return "APELLIDO";
            case 3:
                return "NOMBRE";
            case 4:
                return "SALIDA";
            case 5:
                return "LLEGADA";
            case 6:
                return "FECHA DE SALIDA";
            case 7:
                return "BOLETOS COMPRADOS";
            case 8:
                return "HORA DE EMBARQUE";
            case 9:
                return "PRECIO TOTAL";       
            default:
                return null;
        }
    }

    public double SumarTotalGanancias(int columna) {
        double Ganancias = 0.0;

        for (int fila = 0; fila < getRowCount(); fila++) {
            try {
                Object Calculo = getValueAt(fila, columna);

                if (Calculo instanceof Number) {
                    Ganancias += ((Number) Calculo).doubleValue();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Ganancias;
    }
}
