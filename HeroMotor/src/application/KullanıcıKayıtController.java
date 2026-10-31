package application;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

import com.ProjemMySQL.Util.VeritabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class KullanıcıKayıtController {
	public KullanıcıKayıtController() {
		baglanti=VeritabaniUtil.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_kaydet;

    @FXML
    private TextField txt_kulad;

    @FXML
    private TextField txt_sifre;

    @FXML
    private TextField txt_yetki;
    
    Connection baglanti = null;
    PreparedStatement sorguIfadesi = null;
    String sql;

    @FXML
    void btn_kaydet_click(ActionEvent event) {
    	 sql = "INSERT INTO login (ad, sifre, yetki) VALUES (?, ?, ?)";

         try {
             baglanti = VeritabaniUtil.Baglan(); // Veritabanı bağlantısını aç
             sorguIfadesi = baglanti.prepareStatement(sql);
             sorguIfadesi.setString(1, txt_kulad.getText().trim());
             sorguIfadesi.setString(2, VeritabaniUtil.MD5Sifrele(txt_sifre.getText().trim()));
             sorguIfadesi.setString(3, txt_yetki.getText().trim());
      
             sorguIfadesi.executeUpdate();
             
             Alert alert = new Alert(AlertType.INFORMATION);
             alert.setTitle("Başarılı");
             alert.setHeaderText(null);
             alert.setContentText("Başarıyla kayıt oluştu. ");
             alert.showAndWait();
             
          // Formu temizle
             txt_kulad.clear();
             txt_sifre.clear();
             txt_yetki.clear();
           
             
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
     
    }

}
