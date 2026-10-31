package application;
import java.sql.*;

import com.ProjemMySQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MesajGiden {
	private int id;
	private String isim;
	private String soy;
	private String tel;
	private String mail;
	private String mesaj;
	
	Connection baglanti = null;
	PreparedStatement sorguIfadesi = null;
	String sql;
	    
	MesajGiden(){
		 
		baglanti=VeritabaniUtil.Baglan();

      }
	    
    public MesajGiden( int id, String isim, String soy, String tel, String mail, String mesaj) {
    	
    	this.id=id;
        this.isim = isim;
        this.soy = soy;
        this.tel = tel;
        this.mail = mail;
        this.mesaj = mesaj;

    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsim() {
		return isim;
	}
	public void setIsim(String isim) {
		this.isim = isim;
	}
	public String getSoy() {
		return soy;
	}
	public void setSoy(String soy) {
		this.soy = soy;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMesaj() {
		return mesaj;
	}
	public void setMesaj(String mesaj) {
		this.mesaj = mesaj;
	}

	
	public ObservableList<MesajGiden> GetAll(String secilenSehir) {
        ObservableList<MesajGiden> servisListesi = FXCollections.observableArrayList();

        try {
            // Veritabanından şehre göre servisleri sorgula
            String sql = "SELECT servis, adres, tel, şehir FROM servisler WHERE şehir = ?";
            PreparedStatement sorguIfadesi = baglanti.prepareStatement(sql);
            sorguIfadesi.setString(1, secilenSehir);
            ResultSet sonuc = sorguIfadesi.executeQuery();

            // Sonuçları servisListesi'ne ekle
            while (sonuc.next()) {
            	int id = sonuc.getInt("id");
                String servis = sonuc.getString("servis");
                String adres = sonuc.getString("adres");
                String tel = sonuc.getString("tel");
                String sehir = sonuc.getString("şehir");

                servisListesi.add(new MesajGiden(id, isim, soy, tel, mail, mesaj));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servisListesi;
    }
	public Boolean Update(int id) {
		this.id=id;
		
		return true;
	}


	

}
