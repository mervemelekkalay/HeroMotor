package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.ProjemMySQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class ServislerController {
	public ServislerController() {
		baglanti=VeritabaniUtil.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_ara;

    @FXML
    private ComboBox<String> cmb_sehir;

    @FXML
    private TableColumn<Servis, String> col_adres;

    @FXML
    private TableColumn<Servis, String> col_servis;

    @FXML
    private TableColumn<Servis, String> col_tel;
    
    @FXML
    private TableColumn<Servis, String> col_id;
    
    @FXML
    private TableColumn<Servis, String> col_sehir;


    @FXML
    private TableView<Servis> tableview_servisler;
    
    Connection baglanti = null;
    PreparedStatement sorguIfadesi = null;
    String sql;

    @FXML
    void btn_ara_click(ActionEvent event) {
        // Seçilen şehri al
        String secilenSehir = cmb_sehir.getValue();

        try {
            // Servis sınıfından verileri al
            //ObservableList<Servis> data = new Servis().GetAll(secilenSehir);
        	ObservableList<Servis> data = FXCollections.observableArrayList() ;
        	 String sql = "SELECT id, servis, adres, tel, şehir FROM servisler WHERE şehir = ?";
	            PreparedStatement sorguIfadesi = baglanti.prepareStatement(sql);
	            sorguIfadesi.setString(1, secilenSehir);
	            ResultSet sonuc = sorguIfadesi.executeQuery();
           while(sonuc.next()) {
        	   data.add(new Servis(sonuc.getInt("id"), sonuc.getString("servis"),sonuc.getString("adres"),
        			   sonuc.getString("tel"),sonuc.getString("şehir")));
        	   
           }
            col_servis.setCellValueFactory(new PropertyValueFactory<>("servis"));
            col_adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
            col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            col_sehir.setCellValueFactory(new PropertyValueFactory<>("sehir"));
            
            tableview_servisler.setItems(data);
            // TableView'e verileri ekle
            // Önceki verileri temizle

        } catch (Exception e) {
            e.printStackTrace();
        }
    }







    @FXML
    void initialize() {
    	
    	ObservableList<String> sehir=FXCollections.observableArrayList("ANKARA","ANTALYA","BURSA","İSTANBUL","İZMİR");
    	cmb_sehir.getItems().addAll(sehir);
    	
     
    }
    
    

}
