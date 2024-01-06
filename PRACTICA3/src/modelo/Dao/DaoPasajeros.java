/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Dao;

import controlador.TDA.Listas.DynamicList;
import controlador.TDA.Listas.Exceptions.EmptyException;
import controlador.Utiles.Utiles;
import controlador.dao.DaoImplement;
import java.lang.reflect.Field;
import modelos.Pasajeros;
import modelos.VentaBoleto;


/**
 *
 * @author Jhostin Roja
 */
public class DaoPasajeros extends DaoImplement<Pasajeros> {
    private DynamicList<Pasajeros> PasajerosList = new DynamicList<>();
    private Pasajeros pasajeros;
    
    public DaoPasajeros(){
        super(Pasajeros.class);
    }

    public DynamicList<Pasajeros> getPasajerosList() {
        PasajerosList = all();
        return PasajerosList;
    }

    public void setPasajerosList(DynamicList<Pasajeros> PasajerosList) {
        this.PasajerosList = PasajerosList;
    }

    public Pasajeros getPasajero() {
        if(pasajeros == null){
            pasajeros = new Pasajeros();
        }
        return pasajeros;
    }

    public void setPasajeros(Pasajeros pasajeros) {
        this.pasajeros = pasajeros;
    }
    

    public Boolean persist() {
        pasajeros.setIdPasajeros(all().getLength()+1);
        return persist (pasajeros);
    }
    
//    // Metodo Ordenar Burbuja
//    public DynamicList<Pasajeros> ordenar(DynamicList<Pasajeros> lista, Integer tipo, String field) throws EmptyException, Exception {
//        Field attribute = Utiles.getField(Pasajeros.class, field);
//        Integer n = lista.getLength();
//        Pasajeros[] personas = lista.toArray();
//        if (attribute != null) {
//            for (int i = 0; i < n - 1; i++) {
//                int k = i;
//                Pasajeros t = personas[i];
//                for (int j = i + 1; j < n; j++) {
//                    if (personas[j].compare(t, field, tipo)) {
//                        t = personas[j];
//                        k = j;
//                    }
//                }
//                personas[k] = personas[i];
//                personas[i] = t;
//            }
//        } else {
//            throw new Exception("No existe el criterio de busqueda");
//        }
//        return lista.toList(personas);
//
//    }
//    public static void main(String[] args) {
//        try {
//            DaoPasajeros pc = new DaoPasajeros();
//            System.out.println(pc.all().toString());
//            System.out.println("-------");
//            System.out.println(pc.ordenar( pc.all(), 0, "Apellidos".toString()));
//        } catch (Exception e) {
//        }
//    }
public DynamicList<Pasajeros> metodoOrdenarQuicksort(DynamicList<Pasajeros> lista, Integer tipo, String field) throws EmptyException, Exception {
    Pasajeros[] pasajeros = lista.toArray();
    Field attribute = Utiles.getField(Pasajeros.class, field);

    if (attribute != null) {
        quickSort(pasajeros, 0, pasajeros.length - 1, tipo, field);
    } else {
        throw new Exception("El criterio de búsqueda no existe");
    }
   return lista.toList(pasajeros);
}

private void quickSort(Pasajeros[] pasajeros, int izq, int der, Integer tipo, String field) {
    if (izq < der) {
        int particionIndex = particion(pasajeros, izq, der, tipo, field);
        quickSort(pasajeros, izq, particionIndex - 1, tipo, field);
        quickSort(pasajeros, particionIndex + 1, der, tipo, field);
    }
}

private int particion(Pasajeros[] pasajeros, int izq, int der, Integer tipo, String field) {
    Pasajeros pivot = pasajeros[der];
    int i = izq - 1;
    for (int j = izq; j < der; j++) {
        if (pasajeros[j].compare(pivot, field, tipo)) {
            i++;
            Pasajeros temp = pasajeros[i];
            pasajeros[i] = pasajeros[j];
            pasajeros[j] = temp;
        }
    }
    Pasajeros temp = pasajeros[i + 1];
    pasajeros[i + 1] = pasajeros[der];
    pasajeros[der] = temp;
    return i + 1;
}

public DynamicList<Pasajeros> metodoShellSortOrden(DynamicList<Pasajeros> lista, Integer tipo, String field) throws EmptyException, Exception {
    long starTime = System.nanoTime();
    Field attribute = Utiles.getField(Pasajeros.class, field); 
    Pasajeros[] pasajeros = lista.toArray(); 

    if (attribute == null) {
        throw new Exception("El criterio de búsqueda no existe"); 
    }

    int N = pasajeros.length; 
    int salto = N / 2; 

    while (salto >= 1) { 
        for (int i = 0; i < salto; i++) { 
            for (int j = salto + i; j < N; j += salto) { 
                Pasajeros v = pasajeros[j]; 
                int n = j - salto; 

                
                while (n >= 0 && pasajeros[n].compare(v, field, tipo)) {
                    pasajeros[n + salto] = pasajeros[n];
                    n -= salto;
                }

                pasajeros[n + salto] = v; 
            }
        }
        salto /= 2; 
    }

    lista.reset(); 
    for (Pasajeros pasajeros1 : pasajeros) {
        lista.add(pasajeros1); 
    }
    long endTime = System.nanoTime();
    long duration = (endTime - starTime);
    System.out.println("Tiempo de ejecución:" + duration +"nanosegundos");
    return lista.toList(pasajeros); 
}
//public static void main(String[] args) {
//    DaoPasajeros controlPasajero = new DaoPasajeros();
//
//    try {
//        DynamicList<Pasajeros> listadePasajeros = controlPasajero.getPasajerosList();
//        DynamicList<Pasajeros> listaOrdenada = controlPasajero.shellSortOrden(listadePasajeros, 0, "nombres");
//
//        for (int i = 0; i < listaOrdenada.getLength(); i++) {
//            Pasajeros pasajeros = listaOrdenada.getInfo(i);
//            System.out.println("Nombre: " + pasajeros.getNombres() + ", ID: " + pasajeros.getIdPasajeros());
//        }
//        } catch (EmptyException e){
//            e.printStackTrace();
//    } catch (Exception ex) {
//        ex.printStackTrace();
//    }
//}

public DynamicList<VentaBoleto> ordenarQuicksort(DynamicList<VentaBoleto> lista, Integer tipo, String field) throws EmptyException, Exception {
    VentaBoleto[] ventaBoletos = lista.toArray();
    Field attribute = Utiles.getField(Pasajeros.class, field);
    if (attribute != null) {
        quickSort(ventaBoletos, 0, ventaBoletos.length - 1, tipo, field);
    } else {
        throw new Exception("El criterio de búsqueda no existe");
    }
   return lista.toList(ventaBoletos);
}

private void quickSort(VentaBoleto[] ventaBoletos, int izq, int der, Integer tipo, String field) {
    if (izq < der) {
        int particionIndex = particion(ventaBoletos, izq, der, tipo, field);
        quickSort(ventaBoletos, izq, particionIndex - 1, tipo, field);
        quickSort(ventaBoletos, particionIndex + 1, der, tipo, field);
    }
}

private int particion(VentaBoleto[] ventaBoletos, int izq, int der, Integer tipo, String field) {
    VentaBoleto pivot = ventaBoletos[der];
    int i = izq - 1;
    for (int j = izq; j < der; j++) {
        if (ventaBoletos[j].compare(pivot, field, tipo)) {
            i++;
            VentaBoleto temp = ventaBoletos[i];
            ventaBoletos[i] = ventaBoletos[j];
            ventaBoletos[j] = temp;
        }
    }
    VentaBoleto temp = ventaBoletos[i + 1];
    ventaBoletos[i + 1] = ventaBoletos[der];
    ventaBoletos[der] = temp;
    return i + 1;
}

public DynamicList<VentaBoleto> shellSortOrden(DynamicList<VentaBoleto> lista, Integer tipo, String field) throws EmptyException, Exception {
    long starTime = System.nanoTime();
    Field attribute = Utiles.getField(Pasajeros.class, field); 
    VentaBoleto[] ventaBoletos = lista.toArray(); 
    if (attribute == null) {
        throw new Exception("El criterio de búsqueda no existe"); 
    }
    int N = ventaBoletos.length; 
    int salto = N / 2; 
    while (salto >= 1) { 
        for (int i = 0; i < salto; i++) { 
            for (int j = salto + i; j < N; j += salto) { 
                VentaBoleto v = ventaBoletos[j]; 
                int n = j - salto; 
                while (n >= 0 && ventaBoletos[n].compare(v, field, tipo)) {
                    ventaBoletos[n + salto] = ventaBoletos[n];
                    n -= salto;
                }

                ventaBoletos[n + salto] = v; 
            }
        }
        salto /= 2; 
    }

    lista.reset(); 
    for (VentaBoleto ventaBoleto1 : ventaBoletos) {
        lista.add(ventaBoleto1); 
    }
    long endTime = System.nanoTime();
    long duration = (endTime - starTime);
    System.out.println("Tiempo de ejecución:" + duration +"nanosegundos");
    return lista.toList(ventaBoletos); 
}
}
