package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ADMİNController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor_sayfa;

    @FXML
    private Button btn_bayikayit;

    @FXML
    private Button btn_fatura;

    @FXML
    private Button btn_mesaj;

    @FXML
    private Button btn_serviskayit;

    @FXML
    private Button btn_testsurus;
    
    @FXML
    private Button btn_kulkayıt;

    @FXML
    void btn_bayikayit_click(ActionEvent event) {
    	
    	try {
    	    // Yeni içeriği yükle
    	    AnchorPane panel = FXMLLoader.load(getClass().getResource("BayiKayit.fxml"));
    	    //Temizle();

    	    // Mevcut içeriği temizle
    	    anchor_sayfa.getChildren().clear();
    	    
    	    // Yeni içeriği ekle
    	    anchor_sayfa.getChildren().add(panel);
    	} catch(Exception e) {
    	    e.printStackTrace();
    	}


    }

    @FXML
    void btn_fatura_click(ActionEvent event) {
    	try {
    	    // Yeni içeriği yükle
    	    AnchorPane panel = FXMLLoader.load(getClass().getResource("Fatura.fxml"));
    	    //Temizle();

    	    // Mevcut içeriği temizle
    	    anchor_sayfa.getChildren().clear();
    	    
    	    // Yeni içeriği ekle
    	    anchor_sayfa.getChildren().add(panel);
    	} catch(Exception e) {
    	    e.printStackTrace();
    	}


    }

    @FXML
    void btn_mesaj_click(ActionEvent event) {
    	try {
    	    // Yeni içeriği yükle
    	    AnchorPane panel = FXMLLoader.load(getClass().getResource("Mesaj.fxml"));
    	    //Temizle();

    	    // Mevcut içeriği temizle
    	    anchor_sayfa.getChildren().clear();
    	    
    	    // Yeni içeriği ekle
    	    anchor_sayfa.getChildren().add(panel);
    	} catch(Exception e) {
    	    e.printStackTrace();
    	}


    }

    @FXML
    void btn_serviskayit_click(ActionEvent event) {
    	
    	try {
    	    // Yeni içeriği yükle
    	    AnchorPane panel = FXMLLoader.load(getClass().getResource("ServisKayit.fxml"));
    	    //Temizle();

    	    // Mevcut içeriği temizle
    	    anchor_sayfa.getChildren().clear();
    	    
    	    // Yeni içeriği ekle
    	    anchor_sayfa.getChildren().add(panel);
    	} catch(Exception e) {
    	    e.printStackTrace();
    	}


    }

    @FXML
    void btn_testsurus_click(ActionEvent event) {
    	
    	try {
    	    // Yeni içeriği yükle
    	    AnchorPane panel = FXMLLoader.load(getClass().getResource("Testadmin.fxml"));
    	    //Temizle();

    	    // Mevcut içeriği temizle
    	    anchor_sayfa.getChildren().clear();
    	    
    	    // Yeni içeriği ekle
    	    anchor_sayfa.getChildren().add(panel);
    	} catch(Exception e) {
    	    e.printStackTrace();
    	}
    }
    

    @FXML
    void btn_kulkayıt_click(ActionEvent event) {
    	
    	try {
    	    // Yeni içeriği yükle
    	    AnchorPane panel = FXMLLoader.load(getClass().getResource("KullanıcıKayıt.fxml"));
    	    //Temizle();

    	    // Mevcut içeriği temizle
    	    anchor_sayfa.getChildren().clear();
    	    
    	    // Yeni içeriği ekle
    	    anchor_sayfa.getChildren().add(panel);
    	} catch(Exception e) {
    	    e.printStackTrace();
    	}

    }


    @FXML
    void initialize() {
       
    }

}
