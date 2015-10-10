package com.paralun.app.service;

import com.paralun.app.entity.Barang;
import java.util.List;

public interface BarangDao {
    public void insert(Barang b) throws Exception;
    public void update(Barang b) throws Exception;
    public void delete(String kode) throws Exception;
    public Barang getByKode(String kode) throws Exception;
    public List<Barang> getAllItem() throws Exception;
    public List<Barang> getByName(String nama) throws Exception;
}
