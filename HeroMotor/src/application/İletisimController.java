package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ProjemMySQL.Util.VeritabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class İletisimController {

    @FXML
    private Button btn_gonder;

    @FXML
    private TextField txt_tel;

    @FXML
    private TextField txt_ad;

    @FXML
    private TextField txt_mail;

    @FXML
    private TextArea txt_mesaj;

    @FXML
    private TextField txt_soy;

    Connection baglanti = null;
    PreparedStatement sorguIfadesi = null;
    String sql;

    @FXML
    void btn_gonder_click(ActionEvent event) {
        sql = "INSERT INTO iletisim (isim, soy, tel, mail, mesaj) VALUES (?, ?, ?, ?, ?)";

        try {
            baglanti = VeritabaniUtil.Baglan(); // Veritabanı bağlantısını aç
            sorguIfadesi = baglanti.prepareStatement(sql);
            sorguIfadesi.setString(1, txt_ad.getText().trim());
            sorguIfadesi.setString(2, txt_soy.getText().trim());
            sorguIfadesi.setString(3, txt_tel.getText().trim());
            sorguIfadesi.setString(4, txt_mail.getText().trim());
            sorguIfadesi.setString(5, txt_mesaj.getText().trim());
            sorguIfadesi.executeUpdate();
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Başarılı");
            alert.setHeaderText(null);
            alert.setContentText("İletişim formunuz başarıyla gönderildi!");
            alert.showAndWait();
            
         // Formu temizle
            txt_ad.clear();
            txt_soy.clear();
            txt_tel.clear();
            txt_mail.clear();
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
}