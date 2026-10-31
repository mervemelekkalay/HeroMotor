package application;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

import com.ProjemMySQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TestadminController {
	public TestadminController() {
		baglanti=VeritabaniUtil.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TableColumn<Test, String> col_id;


    @FXML
    private TableColumn<Test, String> col_isim;

    @FXML
    private TableColumn<Test, String> col_mail;

    @FXML
    private TableColumn<Test, String> col_mesaj;

    @FXML
    private TableColumn<Test, String> col_model;

    @FXML
    private TableColumn<Test, String> col_sehir;

    @FXML
    private TableColumn<Test, String> col_soy;

    @FXML
    private TableColumn<Test, String> col_tel;

    @FXML
    private TableView<Test> tbl_testadmin;
    
    Connection baglanti = null;
    PreparedStatement sorguIfadesi = null;
    String sql;

    public void DegerleriGetir(TableView tablo) {

    	sql="select * from test";
        try {
          
        	ObservableList<Test> data = FXCollections.observableArrayList() ;
	            PreparedStatement sorguIfadesi = baglanti.prepareStatement(sql);
	            ResultSet sonuc = sorguIfadesi.executeQuery();
           while(sonuc.next()) {
        	   data.add(new Test(sonuc.getInt("id"),sonuc.getString("isim"),sonuc.getString("soy"),
			        sonuc.getInt("tel"),sonuc.getString("mail") ,sonuc.getString("model"),sonuc.getString("şehir"),
			        sonuc.getString("mesaj")));
			        	   
           }
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_isim.setCellValueFactory(new PropertyValueFactory<>("isim"));
            col_soy.setCellValueFactory(new PropertyValueFactory<>("soy"));
            col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            col_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
            col_sehir.setCellValueFactory(new PropertyValueFactory<>("sehir"));
            col_mesaj.setCellValueFactory(new PropertyValueFactory<>("mesaj"));
            
            tbl_testadmin.setItems(data);
         

        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    
    @FXML
    void initialize() {
    	DegerleriGetir(tbl_testadmin);
    }

}
