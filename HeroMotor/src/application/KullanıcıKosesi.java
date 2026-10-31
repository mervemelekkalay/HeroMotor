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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;



public class KullanıcıKosesi {
	
	public KullanıcıKosesi() {
		baglanti=VeritabaniUtil.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_sorgula;

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
    private TableView<Fatura> tbl_fatura;

    @FXML
    private TextField txt_sorgu;
    
    Connection baglanti = null;
    PreparedStatement sorguIfadesi = null;
    String sql;

    @FXML
    void btn_sorgula_click(ActionEvent event) {
        String saseNumarasi = txt_sorgu.getText().trim();

        try {
            ObservableList<Fatura> data = FXCollections.observableArrayList();
            String sql = "SELECT * FROM fatura WHERE şase = ?";
            PreparedStatement preparedStatement = baglanti.prepareStatement(sql);
            preparedStatement.setString(1, saseNumarasi);
            ResultSet sonuc = preparedStatement.executeQuery();
            
            if (!sonuc.next()) {
                // Şase numarasına ait fatura bulunamadı uyarısı göster
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Uyarı");
                alert.setHeaderText("Fatura Bulunamadı");
                alert.setContentText("Girilen şase numarasına ait bir fatura bulunamadı.");
                alert.showAndWait();
            } else {
                do {
                    data.add(new Fatura(sonuc.getInt("id"), sonuc.getString("ad"), sonuc.getString("soy"),
                            sonuc.getString("ürün"), sonuc.getString("şase"), sonuc.getDate("tarih"), 
                            sonuc.getDouble("tutar"), sonuc.getString("açıklama")));
                } while (sonuc.next());
                
                // Tablo kolonlarına değer atama
                col_ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
                col_soy.setCellValueFactory(new PropertyValueFactory<>("soy"));
                col_urun.setCellValueFactory(new PropertyValueFactory<>("urun"));
                col_sase.setCellValueFactory(new PropertyValueFactory<>("sase"));
                col_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
                col_tutat.setCellValueFactory(new PropertyValueFactory<>("tutar"));
                col_acıklama.setCellValueFactory(new PropertyValueFactory<>("acıklama"));

                tbl_fatura.setItems(data);
            }

            // Kullanılan kaynakları kapat
            sonuc.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Veritabanı hatası uyarısı göster
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText("Veritabanı Hatası");
            alert.setContentText("Veritabanından veri alınırken bir hata oluştu: " + e.getMessage());
            alert.showAndWait();
        }
    }


    @FXML
    void initialize() {
    
    }

}

