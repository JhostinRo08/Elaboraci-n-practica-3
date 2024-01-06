/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.dao;

import controlador.TDA.Listas.DynamicList;

/**
 *
 * @author Jhostin Rojas
 */
public interface DaoInterface<T> {

    public Boolean persist(T dato);

    public Boolean merge(T data, Integer index);

    public DynamicList<T> all();

    public T get(Integer id);

}
