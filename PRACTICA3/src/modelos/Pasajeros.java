/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Jhostin Roja
 */
public class Pasajeros {
    private Integer idPasajeros;
    private String Dni;
    private String nombres;
    private String apellidos;
    private VentaBoleto datosboleto;
   

    public Pasajeros(Integer idPasajeros, String DNI, String nombres,String apellidos,VentaBoleto datosboleto) {
        this.Dni = Dni;
        this.apellidos = apellidos;
        this.datosboleto = datosboleto;
        this.nombres = nombres;
        this.idPasajeros = idPasajeros;
    }

    public Pasajeros() {
        
    }

    public Integer getIdPasajeros() {
        return idPasajeros;
    }

    public void setIdPasajeros(Integer idPasajeros) {
        this.idPasajeros = idPasajeros;
    }
    
    public String getDNI() {
        return Dni;
    }

    public void setDNI(String DNI) {
        this.Dni= DNI;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


    public VentaBoleto getDatosboleto() {
        return datosboleto;
    }

    public void setDatosboleto(VentaBoleto datosboleto) {
        this.datosboleto = datosboleto;
    }
    @Override
    public String toString() {
        return "\n"+nombres+apellidos;
    }
    
    public Boolean compare(Pasajeros p, String field, Integer type){
        switch (type) {
            case 0:
                if(field.equalsIgnoreCase("apellidos")){
                    return apellidos.compareTo(p.getApellidos()) < 0;
                } else if(field.equalsIgnoreCase("nombres")){
                    return nombres.compareTo(p.getNombres()) < 0;
                }else if(field.equalsIgnoreCase("DNI")){
                    return Dni.compareTo(p.getDNI()) < 0;
                } else if (field.equalsIgnoreCase("idPasajeros")){
                    return idPasajeros.compareTo(p.getIdPasajeros()) < 0;
                }
            case 1:
            if(field.equalsIgnoreCase("apellidos")){
                    return apellidos.compareTo(p.getApellidos()) > 0;
                } else if(field.equalsIgnoreCase("nombres")){
                    return nombres.compareTo(p.getNombres()) > 0;
                }else if(field.equalsIgnoreCase("DNI")){
                    return Dni.compareTo(p.getDNI()) > 0;
                }else if (field.equalsIgnoreCase("idPasajeros")){
                    return idPasajeros.compareTo(p.getIdPasajeros()) > 0;
                }
                default:
                throw new AssertionError();
        }
    }
    
}