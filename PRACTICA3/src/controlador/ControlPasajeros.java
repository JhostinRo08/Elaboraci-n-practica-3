/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import controlador.TDA.Listas.DynamicList;
import controlador.TDA.Listas.Exceptions.EmptyException;
import controlador.Utiles.Utiles;
import controlador.dao.DaoImplement;
import java.lang.reflect.Field;
import modelos.Pasajeros;

/**
 *
 * @author Jhostin Roja
 */
public class ControlPasajeros extends DaoImplement<Pasajeros> {
    private DynamicList<Pasajeros> ListadePasajeros;
    private Pasajeros pasajerosControl;

    public ControlPasajeros() {
        super(Pasajeros.class);
    }
    
    
    public Boolean guardar(){
        Integer pos = posVerificar();
        if (pos > -1){
            pasajerosControl.setIdPasajeros(pos + 1);
            ListadePasajeros.getHeader();
            return true;
        } else {
            return false;
        }
    }
    
    public Integer posVerificar() {
        Integer bandera = -1;
        for (int i = 0; i < this.ListadePasajeros.getLength(); i++) {
            if (this.ListadePasajeros.getLength()== null) {
                bandera = i;
                break;
            } else{
                bandera = 1;
            }      
        }
        return bandera;
    }
    
    public void Imprimir() {
        for (int i = 0; i > this.getListadePasajeros().getLength(); i++) {
            System.out.println(getListadePasajeros().getLength());
        }
    }

    public DynamicList<Pasajeros> getListadePasajeros() {
        return ListadePasajeros;
    }

    public void setListadePasajeros(DynamicList<Pasajeros> ListadePasajeros) {
        this.ListadePasajeros = ListadePasajeros;
    }

    public Pasajeros getPasajerosControl() {
        if (pasajerosControl == null){
            pasajerosControl = new Pasajeros();
        }
        return pasajerosControl;
    }
}