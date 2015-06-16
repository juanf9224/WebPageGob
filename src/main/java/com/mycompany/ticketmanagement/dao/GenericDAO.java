/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketmanagement.dao;

import java.util.List;

/**
 *
 * @author juanf_000
 * @param <T>
 */
public interface GenericDAO<T> {
    
    public T create(T t);
    public T retreive(T t);
    public T update(T t);
    public T delete(T t);
    public List<T> findAll();
}
