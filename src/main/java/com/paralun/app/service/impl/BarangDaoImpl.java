package com.paralun.app.service.impl;

import com.paralun.app.entity.Barang;
import com.paralun.app.service.BarangDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BarangDaoImpl implements BarangDao{
    
    private final Connection connection;
    private final String INSERT_SQL = "insert into barang(kode,nama,kategori,stok,harga) values (?,?,?,?,?)";
    private final String UPDATE_SQL = "update barang set nama = ?, kategori = ?, stok = ?, harga = ? where kode = ?";
    private final String DELETE_SQL = "delete from barang where kode = ?";
    private final String SELECT_KODE_SQL = "select * from barang where kode = ?";
    private final String SELECT_NAMA_SQL = "select * from barang where nama LIKE '%'?'%'";
    private final String SELECT_ALL_SQL = "select * from barang";

    public BarangDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Barang b) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)){
            connection.setAutoCommit(false);
            statement.setString(1, b.getKodeBarang());
            statement.setString(2, b.getNamaBarang());
            statement.setString(3, b.getKategori());
            statement.setInt(4, b.getStok());
            statement.setBigDecimal(5, b.getHarga());
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException ex){
            connection.rollback();
            throw new Exception(ex.getMessage());
        }finally{
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void update(Barang b) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)){
            connection.setAutoCommit(false);
            statement.setString(1, b.getNamaBarang());
            statement.setString(2, b.getKategori());
            statement.setInt(3, b.getStok());
            statement.setBigDecimal(4, b.getHarga());
            statement.setString(5, b.getKodeBarang());
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException ex){
            connection.rollback();
            throw new Exception(ex.getMessage());
        }finally{
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void delete(String kode) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_SQL)){
            connection.setAutoCommit(false);
            statement.setString(1, kode);
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException ex){
            connection.rollback();
            throw new Exception(ex.getMessage());
        }finally{
            connection.setAutoCommit(true);
        }
    }

    @Override
    public Barang getByKode(String kode) throws Exception {
        Barang barang = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_KODE_SQL)){
            connection.setAutoCommit(false);
            statement.setString(1, kode);
            try (ResultSet result = statement.executeQuery()){
                if(result.next()){
                    barang = new Barang(
                            result.getString(1), 
                            result.getString(2), 
                            result.getString(3), 
                            result.getInt(4), 
                            result.getBigDecimal(5));
                }
            }
            connection.commit();
        }catch(SQLException ex){
            connection.rollback();
            throw new Exception(ex.getMessage());
        }finally{
            connection.setAutoCommit(true);
            return barang;
        }
    }

    @Override
    public List<Barang> getAllItem() throws Exception {
        List<Barang> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            try (ResultSet result = statement.executeQuery(SELECT_ALL_SQL)){
                while(result.next()){
                    Barang barang = new Barang(
                            result.getString(1), 
                            result.getString(2), 
                            result.getString(3), 
                            result.getInt(4), 
                            result.getBigDecimal(5));
                    list.add(barang);
                }
            }
        }catch(SQLException ex){
            throw new Exception(ex.getMessage());
        }finally{
            return list;
        }
    }

    @Override
    public List<Barang> getByName(String nama) throws Exception {
        List<Barang> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_NAMA_SQL)){
            connection.setAutoCommit(false);
            statement.setString(1, nama);
            try (ResultSet result = statement.executeQuery()){
                while(result.next()){
                    Barang barang = new Barang(
                            result.getString(1), 
                            result.getString(2), 
                            result.getString(3), 
                            result.getInt(4), 
                            result.getBigDecimal(5));
                    list.add(barang);
                }
            }
            connection.commit();
        }catch(SQLException ex){
            connection.rollback();
        }finally{
            connection.setAutoCommit(true);
            return list;
        }
    }
}
