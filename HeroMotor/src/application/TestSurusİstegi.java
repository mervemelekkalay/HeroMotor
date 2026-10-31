package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.ProjemMySQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TestSurusİstegi {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_gonder;

    @FXML
    private ComboBox<String> cmb_motor;

    @FXML
    private ComboBox<String> cmb_sehir;

    @FXML
    private TextField txt_isim;

    @FXML
    private TextField txt_mail;

    @FXML
    private TextArea txt_msj;

    @FXML
    private TextField txt_soy;

    @FXML
    private TextField txt_tel;
    
    Connection baglanti = null;
    PreparedStatement sorguIfadesi = null;
    String sql;

    @FXML
    void btn_gonder_click(ActionEvent event) {
    	  sql = "INSERT INTO test (isim, soy, tel, mail, model, şehir, mesaj) VALUES (?, ?, ?, ?, ?, ?, ?)";

          try {
              // Veritabanı bağlantısını aç
              baglanti = VeritabaniUtil.Baglan(); 
              sorguIfadesi = baglanti.prepareStatement(sql);
              // Formdaki alanlardan değerleri alarak SQL sorgusuna parametre olarak ekleyin
              sorguIfadesi.setString(1, txt_isim.getText().trim());
              sorguIfadesi.setString(2, txt_soy.getText().trim());
              sorguIfadesi.setString(3, txt_tel.getText().trim());
              sorguIfadesi.setString(4, txt_mail.getText().trim());
              sorguIfadesi.setString(5, cmb_motor.getValue());
              sorguIfadesi.setString(6, cmb_sehir.getValue());
              sorguIfadesi.setString(7, txt_msj.getText().trim());
              // SQL sorgusunu çalıştır
              sorguIfadesi.executeUpdate();
              
              // Kullanıcıya başarılı bir şekilde gönderildiğine dair bildirim gösterin
              Alert alert = new Alert(AlertType.INFORMATION);
              alert.setTitle("Başarılı");
              alert.setHeaderText(null);
              alert.setContentText("Test sürüş talebiniz başarıyla gönderildi!");
              alert.showAndWait();
              
              // Formu temizle
              txt_isim.clear();
              txt_soy.clear();
              txt_tel.clear();
              txt_mail.clear();
              cmb_motor.getSelectionModel().clearSelection();
              cmb_sehir.getSelectionModel().clearSelection();
              txt_msj.clear();
              
          } catch (SQLException e) {
              e.printStackTrace();
          } finally {
              // Bağlantıyı kapat
              if (baglanti != null) {
                  try {
                      baglanti.close();
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
              }
          }
      }
    	

    

    @FXML
    void initialize() {
    	
    	//cmb_motor.setPromptText("Model Seçiniz");
    	
    	ObservableList<String> model=FXCollections.observableArrayList("DASH 125","DASH 110","XPULSE 200 4V");
    	cmb_motor.getItems().addAll(model);
    	
    	ObservableList<String> sehir=FXCollections.observableArrayList("İSTANBUL","ANKARA","ANTALYA","İZMİR","BURSA");
    	cmb_sehir.getItems().addAll(sehir);
    	
   

    }

}
