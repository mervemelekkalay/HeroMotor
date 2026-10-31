package application;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

import com.ProjemMySQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class MesajController {
	public MesajController() {
		baglanti=VeritabaniUtil.Baglan();
	}
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_gonder;

    @FXML
    private TableColumn<MesajGiden,String> col_ad;

    @FXML
    private TableColumn<MesajGiden, String> col_id;

    @FXML
    private TableColumn<MesajGiden, String> col_mail;

    @FXML
    private TableColumn<MesajGiden, String> col_mesaj;

    @FXML
    private TableColumn<MesajGiden, String> col_soy;

    @FXML
    private TableColumn<MesajGiden, String> col_tel;

    @FXML
    private TableView<MesajGiden> tbl_mesajlar;

    @FXML
    private TextField txt_alıcı;

    @FXML
    private TextField txt_gonderen;

    @FXML
    private TextArea txt_mesaj;
    
    Connection baglanti = null;
    PreparedStatement sorguIfadesi = null;
    String sql;

    @FXML
    void btn_gonder_click(ActionEvent event) {
    	sql = "INSERT INTO giden (alıcı, gonderen, mesaj) VALUES (?, ?, ?)";

        try {
            baglanti = VeritabaniUtil.Baglan(); // Veritabanı bağlantısını aç
            sorguIfadesi = baglanti.prepareStatement(sql);
            sorguIfadesi.setString(1, txt_alıcı.getText().trim());
            sorguIfadesi.setString(2, txt_gonderen.getText().trim());
            sorguIfadesi.setString(3, txt_mesaj.getText().trim());
            sorguIfadesi.executeUpdate();
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Başarılı");
            alert.setHeaderText(null);
            alert.setContentText("Müşteriye mesaj başarıyla gönderildi!");
            alert.showAndWait();
            
         // Formu temizle
            txt_alıcı.clear();
            txt_gonderen.clear();
            txt_mesaj.clear();
            
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
    
    public void DegerleriGetir(TableView tablo) {

    	sql="select * from iletisim";
        try {
          
        	ObservableList<MesajGiden> data = FXCollections.observableArrayList() ;
	            PreparedStatement sorguIfadesi = baglanti.prepareStatement(sql);
	            ResultSet sonuc = sorguIfadesi.executeQuery();
           while(sonuc.next()) {
        	   data.add(new MesajGiden(sonuc.getInt("kİD"),sonuc.getString("isim"),sonuc.getString("soy"),
        			   sonuc.getString("tel"),sonuc.getString("mail"),sonuc.getString("mesaj") ));
        	   
           }
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_ad.setCellValueFactory(new PropertyValueFactory<>("isim"));
            col_soy.setCellValueFactory(new PropertyValueFactory<>("soy"));
            col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            col_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            col_mesaj.setCellValueFactory(new PropertyValueFactory<>("mesaj"));
            
            tbl_mesajlar.setItems(data);
         

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
    	DegerleriGetir(tbl_mesajlar);
    }

}
