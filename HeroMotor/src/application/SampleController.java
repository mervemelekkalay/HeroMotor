package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor_footer;

    @FXML
    private AnchorPane anchor_içerik;

    @FXML
    private AnchorPane anchor_navbar;

    @FXML
    private Button btn_ana;

    @FXML
    private Button btn_bayi;

    @FXML
    private Button btn_giris;

    @FXML
    private Button btn_iletisim;

    @FXML
    private Button btn_kullanım;

    @FXML
    private Button btn_servis;

    @FXML
    private Button btn_testsurus;

    @FXML
    private Button btn_urunler;
    
    @FXML
    private ImageView img_1;

    @FXML
    private ImageView img_2;

    @FXML
    private Label lbl_1;

    @FXML
    private Label lbl_2;

    @FXML
    private Label lbl_3;

    @FXML
    private Label lbl_4;

    @FXML
    private Label lbl_5;

    @FXML
    private Label lbl_6;

    @FXML
    void btn_ana_click(ActionEvent event) {
    	try {
    	    // Yeni içeriği yükle
    	    AnchorPane panel = FXMLLoader.load(getClass().getResource("Sample.fxml"));

    	    // Mevcut içeriği temizle
    	    anchor_içerik.getChildren().clear();
    	    
    	    // Yeni içeriği ekle
    	    Goster();
    	} catch(Exception e) {
    	    e.printStackTrace();
    	}

    }

    @FXML
    void btn_bayi_click(ActionEvent event) {
    	try {
    	    // Yeni içeriği yükle
    	    AnchorPane panel = FXMLLoader.load(getClass().getResource("Bayiler.fxml"));
    	    Temizle();

    	    // Mevcut içeriği temizle
    	    anchor_içerik.getChildren().clear();
    	    
    	    // Yeni içeriği ekle
    	    anchor_içerik.getChildren().add(panel);
    	} catch(Exception e) {
    	    e.printStackTrace();
    	}

    }

    @FXML
    void btn_giris_click(ActionEvent event) {
    	try {
    	    // Yeni içeriği yükle
    	    AnchorPane panel = FXMLLoader.load(getClass().getResource("Giris.fxml"));
    	    Temizle();

    	    // Mevcut içeriği temizle
    	    anchor_içerik.getChildren().clear();
    	    
    	    // Yeni içeriği ekle
    	    anchor_içerik.getChildren().add(panel);
    	} catch(Exception e) {
    	    e.printStackTrace();
    	}


    }

    @FXML
    void btn_iletisim_click(ActionEvent event) {
        
    	try {
    	    // Yeni içeriği yükle
    	    AnchorPane panel = FXMLLoader.load(getClass().getResource("İletisim.fxml"));
    	    Temizle();

    	    // Mevcut içeriği temizle
    	    anchor_içerik.getChildren().clear();
    	    
    	    // Yeni içeriği ekle
    	    anchor_içerik.getChildren().add(panel);
    	} catch(Exception e) {
    	    e.printStackTrace();
    	}

        
    }
    @FXML
    void btn_kullanım_click(ActionEvent event) {
    	try {
    	    // Yeni içeriği yükle
    	    AnchorPane panel = FXMLLoader.load(getClass().getResource("KullanıcıKosesi.fxml"));
    	    Temizle();

    	    // Mevcut içeriği temizle
    	    anchor_içerik.getChildren().clear();
    	    
    	    // Yeni içeriği ekle
    	    anchor_içerik.getChildren().add(panel);
    	} catch(Exception e) {
    	    e.printStackTrace();
    	}
    }

    @FXML
    void btn_servis_click(ActionEvent event) {

    	try {
    	    // Yeni içeriği yükle
    	    AnchorPane panel = FXMLLoader.load(getClass().getResource("Servisler.fxml"));
    	    Temizle();

    	    // Mevcut içeriği temizle
    	    anchor_içerik.getChildren().clear();
    	    
    	    // Yeni içeriği ekle
    	    anchor_içerik.getChildren().add(panel);
    	} catch(Exception e) {
    	    e.printStackTrace();
    	}

    }

    @FXML
    void btn_testsurus_click(ActionEvent event) {
    	try {
    	    // Yeni içeriği yükle
    	    AnchorPane panel = FXMLLoader.load(getClass().getResource("TestSurusİstegi.fxml"));
    	    Temizle();

    	    // Mevcut içeriği temizle
    	    anchor_içerik.getChildren().clear();
    	    
    	    // Yeni içeriği ekle
    	    anchor_içerik.getChildren().add(panel);
    	} catch(Exception e) {
    	    e.printStackTrace();
    	}
    }

    @FXML
    void btn_urunler_click(ActionEvent event) {
    	try {
    	    // Yeni içeriği yükle
    	    AnchorPane panel = FXMLLoader.load(getClass().getResource("Urunler.fxml"));
    	    Temizle();

    	    // Mevcut içeriği temizle
    	    anchor_içerik.getChildren().clear();
    	    
    	    // Yeni içeriği ekle
    	    anchor_içerik.getChildren().add(panel);
    	} catch(Exception e) {
    	    e.printStackTrace();
    	}

    }

    @FXML
    void initialize() {
     
    }

    public void Temizle() {
    	lbl_1.setVisible(false);
	    lbl_2.setVisible(false);
	    lbl_3.setVisible(false);
	    lbl_4.setVisible(false);
	    lbl_5.setVisible(false);
	    lbl_6.setVisible(false);
	    img_1.setVisible(false);
	    img_2.setVisible(false);
    }
    
    public void Goster() {
    	lbl_1.setVisible(true);
	    lbl_2.setVisible(true);
	    lbl_3.setVisible(true);
	    lbl_4.setVisible(true);
	    lbl_5.setVisible(true);
	    lbl_6.setVisible(true);
	    img_1.setVisible(true);
	    img_2.setVisible(true);
    }
}
