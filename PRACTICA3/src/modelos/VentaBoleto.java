/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Jhostin Roja
 */
public class VentaBoleto {
    private String Salida;
    private String Llegada;
    private Integer idBoletos;
    private Integer BoletosComprados;
    private String FechadeSalida;
    private String HoradeEmbarque;
    private Float PrecioTotal;
    
    
    public VentaBoleto() { 
    }

public VentaBoleto(Integer idBoletos, String Salida, Integer BoletosComprados, String Llegada, 
        String FechadeSalida, String HoradeEmbarque, Float PrecioTotal){
    this.Salida = Salida;
    this.Llegada = Llegada; 
    this.idBoletos = idBoletos;
    this.BoletosComprados = BoletosComprados;
    this.FechadeSalida = FechadeSalida;
    this.HoradeEmbarque = HoradeEmbarque;
    this.PrecioTotal = PrecioTotal;
}

    public String getSalida() {
        return Salida;
    }

    public void setSalida(String Salida) {
        this.Salida = Salida;
    }

    public String getLlegada() {
        return Llegada;
    }

    public void setLlegada(String Llegada) {
        this.Llegada = Llegada;
    }

    public Integer getIdBoletos() {
        return idBoletos;
    }

    public void setIdBoletos(Integer idBoletos) {
        this.idBoletos = idBoletos;
    }

    public Integer getBoletosComprados() {
        return BoletosComprados;
    }

    public void setBoletosComprados(Integer BoletosComprados) {
        this.BoletosComprados = BoletosComprados;
    }

    public String getFechadeSalida() {
        return FechadeSalida;
    }

    public void setFechadeSalida(String FechadeSalida) {
        this.FechadeSalida = FechadeSalida;
    }

    public String getHoradeEmbarque() {
        return HoradeEmbarque;
    }

    public void setHoradeEmbarque(String HoradeEmbarque) {
        this.HoradeEmbarque = HoradeEmbarque;
    }

    public Float getPrecioTotal() {
        return PrecioTotal;
    }

    public void setPrecioTotal(Float PrecioTotal) {
        this.PrecioTotal = PrecioTotal;
    }
    
    
    public Boolean compare(VentaBoleto otroBoleto, String field, Integer tipo) {
    switch (field.toLowerCase()) {
        case "idBoletos":
            if (tipo == 0) {
                return this.getIdBoletos().intValue() < otroBoleto.getIdBoletos().intValue();
            } else if (tipo == 1) {
                return this.getIdBoletos().intValue() > otroBoleto.getIdBoletos().intValue();
            }
        case "Salida":
            if (tipo == 0) {
                return this.getSalida().toLowerCase().compareTo(otroBoleto.getSalida().toLowerCase()) < 0;
            } else if (tipo == 1) {
                return this.getFechadeSalida().toLowerCase().compareTo(otroBoleto.getSalida().toLowerCase()) > 0;
            }
        case "Llegada":
            if (tipo == 0) {
                return this.getLlegada().toLowerCase().compareTo(otroBoleto.getLlegada().toLowerCase()) < 0;
            } else if (tipo == 1) {
                return this.getLlegada().toLowerCase().compareTo(otroBoleto.getLlegada().toLowerCase()) > 0;
            }
        case "FechadeSalida":
            if (tipo == 0) {
                return this.getFechadeSalida().toLowerCase().compareTo(otroBoleto.getFechadeSalida().toLowerCase()) < 0;
            } else if (tipo == 1) {
                return this.getFechadeSalida().toLowerCase().compareTo(otroBoleto.getFechadeSalida().toLowerCase()) > 0;
            }
        case "BoletosComprados":
            if (tipo == 0) {
                return this.getBoletosComprados().intValue() < otroBoleto.getBoletosComprados().intValue();
            } else if (tipo == 1) {
                return this.getBoletosComprados().intValue() > otroBoleto.getBoletosComprados().intValue();
            }
            break;
        default:
            break;
    }
    return false; 
}
    @Override
   public String toString() {
        return "VentaBoleto{" + "idBoletos=" + idBoletos + ", Salida=" + Salida + ", Llegada=" + Llegada + ", BoletosComprados=" + BoletosComprados + ", FechadeSalida=" + FechadeSalida + ", HoradeEmbarque=" + HoradeEmbarque + ", PrecioTotal=" + PrecioTotal + '}';
    }
    
}




