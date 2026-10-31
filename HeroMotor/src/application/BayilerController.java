package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.ProjemMySQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class BayilerController {
	public BayilerController() {
		baglanti=VeritabaniUtil.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_ara;

    @FXML
    private ChoiceBox<String> cmb_sehir;

    @FXML
    private TableColumn<Bayi, String> col_adres;

    @FXML
    private TableColumn<Bayi, String> col_bayi;
    
    @FXML
    private TableColumn<Bayi, String> col_id;

    @FXML
    private TableColumn<Bayi,String> col_sehir;

    @FXML
    private TableColumn<Bayi, String> col_tel;

    @FXML
    private TableView<Bayi> tableview_bayiler;
    
    Connection baglanti = null;
    PreparedStatement sorguIfadesi = null;
    String sql;

    @FXML
    void btn_ara_click(ActionEvent event) {
    	
    	 // Seçilen şehri al
        String secilenSehir = cmb_sehir.getValue();

        try {
          
        	ObservableList<Bayi> data = FXCollections.observableArrayList() ;
        	 String sql = "SELECT id, bayi, adres, tel, şehir FROM bayiler WHERE şehir = ?";
	            PreparedStatement sorguIfadesi = baglanti.prepareStatement(sql);
	            sorguIfadesi.setString(1, secilenSehir);
	            ResultSet sonuc = sorguIfadesi.executeQuery();
           while(sonuc.next()) {
        	   data.add(new Bayi(sonuc.getInt("id"), sonuc.getString("bayi"),sonuc.getString("adres"),
        			   sonuc.getString("tel"),sonuc.getString("şehir")));
        	   
           }
            //col_id.setCellValueFactory(new PropertyValueFactory<Bayi, Integer>("id"));
            col_bayi.setCellValueFactory(new PropertyValueFactory<>("bayi"));
            col_adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
            col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            col_sehir.setCellValueFactory(new PropertyValueFactory<>("sehir"));
            
            tableview_bayiler.setItems(data);
            // TableView'e verileri ekleğ
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
