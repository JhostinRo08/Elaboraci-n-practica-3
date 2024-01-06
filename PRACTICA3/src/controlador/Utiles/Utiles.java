/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.Utiles;

import java.lang.reflect.Field;

/**
 *
 * @author Jhostin Roja
 */
public class Utiles {
    public static Field getField(Class clazz, String attribute){
        Field field = null;
//        Field[] fields = clazz.getFields();
        for (Field f : clazz.getSuperclass().getDeclaredFields()) {
            System.out.println(f.getName() + " " + f.getType().getName());
            if (f.getName().equalsIgnoreCase(attribute)) {
                field = f;
                break;
            }
        }
        for (Field f : clazz.getDeclaredFields()) {
            System.out.println(f.getName() + " " + f.getType().getName());
            if (f.getName().equalsIgnoreCase(attribute)) {
                field = f;
                break;
            }
        }
        return field;
    } 
}
