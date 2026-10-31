package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ProjemMySQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Bayi {
	
	private int id;
	private String bayi;
    private String adres;
    private String tel;
	private String sehir;
	
	 Connection baglanti = null;
	 PreparedStatement sorguIfadesi = null;
	 String sql;
	    
	 Bayi(){
		 
		baglanti=VeritabaniUtil.Baglan();

      }
	    
    public Bayi( int id, String bayi, String adres, String tel, String sehir) {
    	
    	this.id=id;
        this.bayi = bayi;
        this.adres = adres;
        this.tel = tel;
        this.sehir = sehir;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    public String getBayi() {
		return bayi;
	}
	public void setServis(String bayi) {
		this.bayi = bayi;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public String getTel() {
			return tel;
		}

    public void setTel(String tel) {
			this.tel = tel;
		}
	public String getSehir() {
		return sehir;
	}

	public void setSehir(String sehir) {
		this.sehir = sehir;
	}
	public ObservableList<Bayi> GetAll(String secilenBayi) {

        ObservableList<Bayi> bayiListesi = FXCollections.observableArrayList();

        try {
            // Veritabanından şehre göre servisleri sorgula
            String sql = "SELECT bayi, adres, tel, şehir FROM bayiler WHERE şehir = ?";
            PreparedStatement sorguIfadesi = baglanti.prepareStatement(sql);
            sorguIfadesi.setString(1, secilenBayi);
            ResultSet sonuc = sorguIfadesi.executeQuery();

            // Sonuçları servisListesi'ne ekle
            while (sonuc.next()) {
            	int id = sonuc.getInt("id");
                String bayi = sonuc.getString("bayi");
                String adres = sonuc.getString("adres");
                String tel = sonuc.getString("tel");
                String sehir = sonuc.getString("şehir");

                bayiListesi.add(new Bayi(id, bayi, adres, tel, sehir));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bayiListesi;

	} 
	
	public Boolean Update(int id) {
		this.id=id;
		
		return true;
	}
}
