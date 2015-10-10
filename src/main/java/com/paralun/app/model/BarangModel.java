/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paralun.app.model;

import com.paralun.app.entity.Barang;
import com.paralun.app.service.BarangDao;
import com.paralun.app.utility.DatabaseUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BarangModel {
    
    private final BarangDao dao;

    public BarangModel() {
        dao = DatabaseUtil.getBarangDao();
    }
    
    public boolean insert(Barang barang){
        try {
            dao.insert(barang);
            return true;
        }catch(Throwable t){
            return false;
        }
    }
    
    public boolean update(Barang barang){
        try {
            dao.update(barang);
            return true;
        }catch(Throwable t){
            return false;
        }
    }
    
    public boolean delete(String kode){
        try {
            dao.delete(kode);
            return true;
        }catch(Throwable t){
            return false;
        }
    }
    
    public Barang getByKode(String kode){
        try {
            return dao.getByKode(kode);
        }catch(Throwable t){
            return null;
        }
    }
    
    public ObservableList<Barang> getAllItem(){
        try{
            ObservableList<Barang> data = FXCollections.observableArrayList(dao.getAllItem());
            return data;
        }catch(Throwable t){
            return null;
        }
    }
    
    public ObservableList<Barang> getByName(String nama){
        try{
            ObservableList<Barang> data = FXCollections.observableArrayList(dao.getByName(nama));
            return data;
        }catch(Throwable t){
            return null;
        }
    }
    
}
