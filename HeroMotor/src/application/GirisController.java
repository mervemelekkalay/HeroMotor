package application;
import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ProjemMySQL.Util.VeritabaniUtil;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GirisController {
	 
	public GirisController() {
		baglanti=VeritabaniUtil.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_admingiris;

    @FXML
    private Button btn_kullgiris;

    @FXML
    private PasswordField sifre_admin;

    @FXML
    private PasswordField sifre_kull;

    @FXML
    private TextField txt_adminadı;

    @FXML
    private TextField txt_kullad;
    
    Connection baglanti=null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen=null;
    String sql;

    @FXML
    void btn_admingiris_click(ActionEvent event) {
    	
    	 String adminAdi = txt_adminadı.getText();
    	 String adminSifre = sifre_admin.getText();
    	    
    	    try {
    	        // Admini veritabanında kontrol et
    	        String sql = "SELECT * FROM login WHERE ad=? AND sifre=?";
    	        sorguIfadesi = baglanti.prepareStatement(sql);
    	        sorguIfadesi.setString(1, adminAdi);
    	        sorguIfadesi.setString(2,  VeritabaniUtil.MD5Sifrele(adminSifre));
    	        getirilen = sorguIfadesi.executeQuery();
    	        
    	        if (getirilen.next()) {
    	            // Admin doğru giriş yaptıysa
    	            Alert alert = new Alert(AlertType.INFORMATION);
    	            alert.setTitle("Başarılı Giriş");
    	            alert.setHeaderText(null);
    	            alert.setContentText("Admin olarak başarılı giriş!");
    	            alert.showAndWait();
    	            
    	            // Admin panelini yükle ve göster //BURAYA ADMİN SAYFASI GELECEK
    	            try {
    	                Stage stage = new Stage();
    	                FXMLLoader loader = new FXMLLoader(getClass().getResource("ADMİN.fxml"));
    	                AnchorPane root = (AnchorPane) loader.load();
    	                
    	                Scene scene = new Scene(root);
    	                stage.setScene(scene);
    	                stage.setTitle("Yönetici Paneli");
    	                stage.show();
    	            } catch (IOException e) {
    	                e.printStackTrace();
    	            }
    	        } else {
    	            // Admin giriş bilgileri yanlışsa
    	            Alert alert = new Alert(AlertType.ERROR);
    	            alert.setTitle("Hatalı Giriş");
    	            alert.setHeaderText(null);
    	            alert.setContentText("Hatalı admin kullanıcı adı veya şifre!");
    	            alert.showAndWait();
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }

    }

    @FXML
    void btn_kullgiris_click(ActionEvent event) {
    	
    	  String kullaniciAdi = txt_kullad.getText();
    	  String sifre = sifre_kull.getText();
    	    
    	    try {
    	        // Kullanıcıyı veritabanında kontrol et
    	        String sql = "SELECT * FROM login WHERE ad=? AND sifre=?";
    	        sorguIfadesi = baglanti.prepareStatement(sql);
    	        sorguIfadesi.setString(1, kullaniciAdi);
    	        sorguIfadesi.setString(2, VeritabaniUtil.MD5Sifrele(sifre));
    	        getirilen = sorguIfadesi.executeQuery();
    	        
    	        if (getirilen.next()) {
    	            // Kullanıcı doğru giriş yaptıysa
    	            Alert alert = new Alert(AlertType.INFORMATION);
    	            alert.setTitle("Başarılı Giriş");
    	            alert.setHeaderText(null);
    	            alert.setContentText("Giriş başarılı!");
    	            alert.showAndWait();
    	            
    	          //Stage olarak dış form
    	    		try {
    	    			Stage stage1=new Stage();
    	    			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
    	    			Scene scene = new Scene(pane1,900,700);
    	    			stage1.setScene(scene);
    	    			stage1.show();
    	    		} catch(Exception e) {
    	    			e.printStackTrace();
    	    		}
    	            
    	            // Şuanki pencereyi kapat
    	            //Stage currentStage = (Stage) btn_kullgiris.getScene().getWindow();
    	           // currentStage.close();
    	    		
    	        } else {
    	            // Kullanıcı giriş bilgilerini yanlış girdiyse
    	            Alert alert = new Alert(AlertType.ERROR);
    	            alert.setTitle("Hatalı Giriş");
    	            alert.setHeaderText(null);
    	            alert.setContentText("Hatalı kullanıcı adı veya şifre!");
    	            alert.showAndWait();
    	        }
    	    } catch (SQLException e) {
    	        ((Throwable) e).printStackTrace();
    	    }
    }

    @FXML
    void initialize() {
       
    }

}
