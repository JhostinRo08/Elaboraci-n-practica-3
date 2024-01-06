/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Dao;


import controlador.TDA.Listas.DynamicList;
import controlador.dao.DaoImplement;
import modelos.VentaBoleto;

/**
 *
 * @author Jhostin Roja
 */
public class DaoVentaBoleto extends DaoImplement<VentaBoleto>{
    
    private DynamicList<VentaBoleto> BoletosLista = new DynamicList<>();
    private VentaBoleto ventaboletos;

    public DaoVentaBoleto() {
        super(VentaBoleto.class);
    }

    public DynamicList<VentaBoleto> getBolestosLista() {
        BoletosLista = all();
        return BoletosLista;
    }

    public void setBolestosLista(DynamicList<VentaBoleto> BoletosLista) {
        this.BoletosLista = BoletosLista;
    }

    public VentaBoleto getVentaboletos() {
        if(ventaboletos == null){
            ventaboletos = new VentaBoleto();
        }
        return ventaboletos;
    }

    public void setVentaboletos(VentaBoleto ventaboletos) {
        this.ventaboletos = ventaboletos;
    }
    public Boolean persist(){
        ventaboletos.setIdBoletos(all().getLength()+1);
        return persist(ventaboletos);
    }
}
