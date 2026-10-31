package application;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

import com.ProjemMySQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;



public class BayiKayitController {
	
	public BayiKayitController() {
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
    private Button btn_guncelle;

    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_yenile;
    

    @FXML
    private Label lbl_sonuc;

    @FXML
    private TableColumn<Bayi, String> col_adres;

    @FXML
    private TableColumn<Bayi, String> col_bayi;

    @FXML
    private TableColumn<Bayi, String> col_sehir;

    @FXML
    private TableColumn<Bayi, String> col_tel;
    
    @FXML
    private TableColumn<Bayi,String> col_id;

    @FXML
    private TableView<Bayi> tbl_bayikayit;

    @FXML
    private TextArea txt_adres;

    @FXML
    private TextField txt_ara;

    @FXML
    private TextField txt_bayi;
    
    @FXML
    private TextField txt_id;
    
    @FXML
    private TextField txt_sehir;

    @FXML
    private TextField txt_tel;

    Connection baglanti = null;
    PreparedStatement sorguIfadesi = null;
    String sql;
    
    @FXML
    void btn_ara_click(ActionEvent event) {

    }

    @FXML
    void btn_ekle_click(ActionEvent event) {
    	sql="insert into bayiler( bayi, adres, tel, şehir) values(?,?,?,?) ";
    	 try {
            
    		    sorguIfadesi=baglanti.prepareStatement(sql);

    	        sorguIfadesi.setString(1, txt_bayi.getText().trim());
    	        sorguIfadesi.setString(2, txt_adres.getText().trim());
    	        sorguIfadesi.setString(3, txt_tel.getText().trim());
    	        sorguIfadesi.setString(4, txt_sehir.getText().trim());
    		 
    		    sorguIfadesi.executeUpdate();
    		 
             lbl_sonuc.setText("Ekleme İşlemi Başarılı!");
             

         } catch (Exception e) {
             lbl_sonuc.setText(e.getMessage().toString());

         }

    }

    @FXML
    void btn_guncelle_click(ActionEvent event) {
        sql = "UPDATE bayiler SET bayi=?, adres=?, tel=?, şehir=? WHERE id=?";
        try {
            sorguIfadesi = baglanti.prepareStatement(sql);
            sorguIfadesi.setString(5, txt_id.getText().trim());
            sorguIfadesi.setString(1, txt_bayi.getText().trim());
            sorguIfadesi.setString(2, txt_adres.getText().trim());
            sorguIfadesi.setString(3, txt_tel.getText().trim());
            sorguIfadesi.setString(4, txt_sehir.getText().trim());
            

            int affectedRows = sorguIfadesi.executeUpdate();

            if (affectedRows > 0) {
                lbl_sonuc.setText("GÜNCELLEME İşlemi Başarılı!");
            } else {
                lbl_sonuc.setText("GÜNCELLEME İşlemi Başarısız!");
            }
        } catch (Exception e) {
            lbl_sonuc.setText(e.getMessage().toString());
        } finally {
            temizle();
        }
    }

    void temizle() { 
        txt_bayi.clear();
        txt_adres.clear();
        txt_tel.clear();
        txt_sehir.clear();
        txt_id.clear();
    }

    @FXML
    void btn_sil_click(ActionEvent event) {
    	sql="DELETE from bayiler WHERE id=?";
   	 try {
           
   		 sorguIfadesi=baglanti.prepareStatement(sql);
   		 sorguIfadesi.setString(1, txt_id.getText().trim());

   		 sorguIfadesi.executeUpdate();
   		 
            lbl_sonuc.setText("SİLME İşlemi Başarılı!");
            

        } catch (Exception e) {
            lbl_sonuc.setText(e.getMessage().toString());

        }

    }

    @FXML
    void btn_yenile_click(ActionEvent event) {
    	sql="select * from bayiler";
    	DegerleriGetir(tbl_bayikayit,sql);

    }
    
    @FXML
    void tbl_MouseClick(MouseEvent event) {
    	Bayi kayit=new Bayi();
    	kayit=(Bayi) tbl_bayikayit.getItems().get(tbl_bayikayit.getSelectionModel().getSelectedIndex());
    	
    	txt_id.setText(String.valueOf( kayit.getId()));
    	txt_bayi.setText(kayit.getBayi());
    	txt_adres.setText(kayit.getAdres());
    	txt_tel.setText(kayit.getTel());
    	txt_sehir.setText(kayit.getSehir());
    	

    }
    
    @FXML
    void txt_ara_Action(ActionEvent event) {

    }

    @FXML
    void txt_ara_KeyPressed(KeyEvent event) {
    	 if(txt_ara.getText().equals("")){
     		
     		sql="select * from bayiler";
     	}
     	else {
         	sql= "select * from bayiler where  bayi  like '%"+txt_ara.getText()+"%' or şehir like'%"+txt_ara.getText()+"%'";

     	}
     	DegerleriGetir(tbl_bayikayit,sql);


     }

    
    
    public void DegerleriGetir(TableView tablo , String sqli) {

    	
        try {
        	if(sqli!=null) {
        		sql=sqli;
        	}else {
        		sql="select * from bayiler";
        	}
        		
            	
        	ObservableList<Bayi> data = FXCollections.observableArrayList() ;
	            PreparedStatement sorguIfadesi = baglanti.prepareStatement(sql);
	            ResultSet sonuc = sorguIfadesi.executeQuery();
           while(sonuc.next()) {
        	   data.add(new Bayi(sonuc.getInt("id"),sonuc.getString("bayi"),sonuc.getString("adres"),
        			   sonuc.getString("tel"),sonuc.getString("şehir")));
        	   
           }
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_bayi.setCellValueFactory(new PropertyValueFactory<>("bayi"));
            col_adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
            col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            col_sehir.setCellValueFactory(new PropertyValueFactory<>("sehir"));
            
            tbl_bayikayit.setItems(data);
            // TableView'e verileri ekle
            // Önceki verileri temizle

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void initialize() {
    	
       DegerleriGetir(tbl_bayikayit,sql);
    }

}
