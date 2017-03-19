/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfm.data.sets.base;

/**
 *
 * @author Misho
 * @param <DTOtype>
 */
public interface IViewable<DTOtype> {
    public DTOtype[] GetAll();
    public DTOtype GetById(int id);
}
