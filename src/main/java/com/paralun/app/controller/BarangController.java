package com.paralun.app.controller;

import com.paralun.app.entity.Barang;
import com.paralun.app.model.BarangModel;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;

public class BarangController implements Initializable{
    
    @FXML
    private TextField textKode;
    @FXML
    private TextField textNama;
    @FXML
    private TextField textKategori;
    @FXML
    private TextField textStok;
    @FXML
    private TextField textHarga;
    @FXML
    private TableView<Barang> tabelBarang;
    @FXML 
    private TableColumn<Barang, String> columnKode;
    @FXML 
    private TableColumn<Barang, String> columnNama;
    @FXML 
    private TableColumn<Barang, String> columnKategori;
    @FXML 
    private TableColumn<Barang, String> columnStok;
    @FXML 
    private TableColumn<Barang, String> columnHarga;
    @FXML
    private Button btnSimpan;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnRefresh;
    
    private ObservableList<Barang> data;
    BarangModel model = new BarangModel();
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnKode.setCellValueFactory(new PropertyValueFactory("kodeBarang"));
        columnNama.setCellValueFactory(new PropertyValueFactory("namaBarang"));
        columnKategori.setCellValueFactory(new PropertyValueFactory("kategori"));
        columnStok.setCellValueFactory(new PropertyValueFactory("stok"));
        columnHarga.setCellValueFactory(new PropertyValueFactory("harga"));
        
        aksiTable();
        tampilData();
    }
    
    @FXML
    public void insert(ActionEvent event){
        if(textKode.getText().trim().isEmpty()){
            showMessage(Alert.AlertType.INFORMATION, "Kode masih kosong!!!");
        }else if(textNama.getText().trim().isEmpty()){
            showMessage(Alert.AlertType.INFORMATION, "Nama masih kosong!!!");
        }else if(textKategori.getText().trim().isEmpty()){
            showMessage(Alert.AlertType.INFORMATION, "Kategori masih kosong!!!");
        }else if(textStok.getText().trim().isEmpty()){
            showMessage(Alert.AlertType.INFORMATION, "Stok masih kosong!!!");
        }else if(textHarga.getText().trim().isEmpty()){
            showMessage(Alert.AlertType.INFORMATION, "Harga masih kosong!!!");
        }else{
            if(model.insert(b())){
                showMessage(Alert.AlertType.INFORMATION, "Simpan Data Sukses!!!");
                clear();
                tampilData();
            }else{
                showMessage(Alert.AlertType.ERROR, "Simpan Data Gagal!!!");
            } 
        }
    }
    
    @FXML
    public void update(ActionEvent event){
        if(tabelBarang.getSelectionModel().getSelectedIndex() != -1){
            if(textKode.getText().trim().isEmpty()){
                showMessage(Alert.AlertType.INFORMATION, "Kode masih kosong!!!");
            }else if(textNama.getText().trim().isEmpty()){
                showMessage(Alert.AlertType.INFORMATION, "Nama masih kosong!!!");
            }else if(textKategori.getText().trim().isEmpty()){
                showMessage(Alert.AlertType.INFORMATION, "Kategori masih kosong!!!");
            }else if(textStok.getText().trim().isEmpty()){
                showMessage(Alert.AlertType.INFORMATION, "Stok masih kosong!!!");
            }else if(textHarga.getText().trim().isEmpty()){
                showMessage(Alert.AlertType.INFORMATION, "Harga masih kosong!!!");
            }else{
                if(model.update(b())){
                    showMessage(Alert.AlertType.INFORMATION, "Update Data Sukses!!!");
                    clear();
                    tampilData();
                }else{
                    showMessage(Alert.AlertType.ERROR, "Update Data Gagal!!!");
                } 
            }
        }else{
            showMessage(Alert.AlertType.INFORMATION, "Pilih baris terlebih dahulu!!!");
        }
    }
    
    @FXML
    public void delete(){
        if(tabelBarang.getSelectionModel().getSelectedIndex() != -1){
            if(model.delete(textKode.getText())){
                showMessage(Alert.AlertType.INFORMATION, "Delete Data Sukses!!!");
                clear();
                tampilData();
            }else{
                showMessage(Alert.AlertType.ERROR, "Delete Data Gagal!!!");
            }
        }else{
            showMessage(Alert.AlertType.INFORMATION, "Pilih baris terlebih dahulu!!!");
        }
    }
    
    @FXML
    public void refresh(ActionEvent event){
        clear();
        tampilData();
    }
    
    public void clear(){
        textKode.setText("");
        textNama.setText("");
        textKategori.setText("");
        textStok.setText("");
        textHarga.setText("");
    }
    
    public void showMessage(Alert.AlertType alertType, String pesan){
        Alert alert = new Alert(alertType, pesan);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Informasi");
        alert.showAndWait();
    }
    
    public void tampilData(){
        if(data!=null){
            data.clear();
        }
        data = model.getAllItem();
        tabelBarang.setItems(data);
    }
    
    public Barang b(){
        return new Barang(
                textKode.getText(), 
                textNama.getText(), 
                textKategori.getText(), 
                Integer.parseInt(textStok.getText()), 
                new BigDecimal(textHarga.getText()));
    }
    
    public void aksiTable(){
        tabelBarang.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    Barang b = data.get(tabelBarang.getSelectionModel().getSelectedIndex());
                    textKode.setText(b.getKodeBarang());
                    textNama.setText(b.getNamaBarang());
                    textKategori.setText(b.getKategori());
                    textStok.setText(String.valueOf(b.getStok()));
                    textHarga.setText(String.valueOf(b.getHarga()));
                }catch(ArrayIndexOutOfBoundsException ex){}
            }
        });
    }
}
