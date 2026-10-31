package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.ProjemMySQL.Util.VeritabaniUtil;
import java.sql.*;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

public class FatruaController {
	public FatruaController() {
		baglanti=VeritabaniUtil.Baglan();
	}
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_ara;

    @FXML
    private Button btn_ekle;

    @FXML
    private TableColumn<Fatura, String> col_acıklama;

    @FXML
    private TableColumn<Fatura, String> col_ad;

    @FXML
    private TableColumn<Fatura, String> col_id;

    @FXML
    private TableColumn<Fatura,String> col_sase;

    @FXML
    private TableColumn<Fatura, String> col_soy;

    @FXML
    private TableColumn<Fatura, Date> col_tarih;

    @FXML
    private TableColumn<Fatura, Double> col_tutat;

    @FXML
    private TableColumn<Fatura, String> col_urun;

    @FXML
    private DatePicker date_time;

    @FXML
    private Label lbl_sonuc;
    
    @FXML
    private Label lbl_id;

    @FXML
    private TableView<Fatura> tbl_fatura;

    @FXML
    private TextField txt_acıklama;

    @FXML
    private TextField txt_ad;

    @FXML
    private TextField txt_ara;

    @FXML
    private TextField txt_model;

    @FXML
    private TextField txt_sase;

    @FXML
    private TextField txt_soyad;

    @FXML
    private TextField txt_tutar;
    
    Connection baglanti = null;
    PreparedStatement sorguIfadesi = null;
    String sql;

    @FXML
    void btn_ara_click(ActionEvent event) {
    	sql="select * from fatura ";
    	DegerleriGetir(tbl_fatura,sql);

    }
  
    @FXML
    void btn_ekle_click(ActionEvent event) {
    	sql="insert into fatura(ad, soy, ürün, şase, tarih, tutar, açıklama) values(?,?,?,?,?,?,?) ";
      	 try {
              
      		    sorguIfadesi=baglanti.prepareStatement(sql);
      	        sorguIfadesi.setString(1, txt_ad.getText().trim());
      	        sorguIfadesi.setString(2, txt_soyad.getText().trim());
      	        sorguIfadesi.setString(3, txt_model.getText().trim());
      	        sorguIfadesi.setString(4, txt_sase.getText().trim());
      	        //sorguIfadesi.setString(5, date_time.);
      	        
      	      LocalDate localDate = date_time.getValue(); // datePicker_tarih sizin DatePicker bileşeninizin ID'si olmalı
              if (localDate != null) {
                  Date sqlDate = Date.valueOf(localDate);
                  sorguIfadesi.setDate(5, sqlDate);
              } else {
                  sorguIfadesi.setNull(5, java.sql.Types.DATE); // Tarih seçilmediyse NULL olarak ayarlanır
              }
      	        sorguIfadesi.setString(6, txt_tutar.getText().trim());
                sorguIfadesi.setString(7, txt_acıklama.getText().trim());


      		 
      		 sorguIfadesi.executeUpdate();
      		 
             
      		 
      	// Ekleme işlemi başarılı olduğunda bilgi mesajı göster
             Alert alert = new Alert(AlertType.INFORMATION);
             alert.setTitle("Başarılı");
             alert.setHeaderText(null);
             alert.setContentText("Ekleme İşlemi Başarılı!");
             alert.showAndWait();
               

           } catch (Exception e) {
             
               Alert alert = new Alert(AlertType.ERROR);
               alert.setTitle("Hata");
               alert.setHeaderText(null);
               alert.setContentText("Ekleme İşlemi Başarısız: " + e.getMessage());
               alert.showAndWait();

           }

    }
   
   
    @FXML
    void txt_ara_KeyPressed(KeyEvent event) {
        if(txt_ara.getText().equals("")){
    		
    		sql="select * from fatura";
    	}
    	else {
        	sql= "select * from fatura where  soy  like '%"+txt_ara.getText()+"%' or şase like'%"+txt_ara.getText()+"%'";

    	}
    	DegerleriGetir(tbl_fatura,sql);


    }

    @FXML
    void txt_ara_Action(ActionEvent event) {
    	

    }
    
    public void DegerleriGetir(TableView tablo,String sql) {

        try {
          
        	ObservableList<Fatura> data = FXCollections.observableArrayList() ;
	            PreparedStatement sorguIfadesi = baglanti.prepareStatement(sql);
	            ResultSet sonuc = sorguIfadesi.executeQuery();
           while(sonuc.next()) {
        	   data.add(new Fatura(sonuc.getInt("id"),sonuc.getString("ad"),sonuc.getString("soy"),
  sonuc.getString("ürün"),sonuc.getString("şase") ,sonuc.getDate("tarih"),sonuc.getDouble("tutar"),sonuc.getString("açıklama") ));
        	   
           }
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
            col_soy.setCellValueFactory(new PropertyValueFactory<>("soy"));
            col_urun.setCellValueFactory(new PropertyValueFactory<>("urun"));
            col_sase.setCellValueFactory(new PropertyValueFactory<>("sase"));
            col_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
            col_tutat.setCellValueFactory(new PropertyValueFactory<>("tutar"));
            col_acıklama.setCellValueFactory(new PropertyValueFactory<>("acıklama"));
            
            tbl_fatura.setItems(data);
         

        } catch (Exception e) {
            e.printStackTrace();
        }
        
     }

    @FXML
    void initialize() {
    	sql="select * from fatura";
    	DegerleriGetir(tbl_fatura,sql);
    
    }

}
