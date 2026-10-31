package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ProjemMySQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Servis {
	    private int id;
		private String servis;
	    private String adres;
	    private String tel;
		private String sehir;
		
		 Connection baglanti = null;
		 PreparedStatement sorguIfadesi = null;
	     String sql;
		    
         Servis(){
        	 
    		baglanti=VeritabaniUtil.Baglan();

	    }

	    public Servis(int id, String servis, String adres, String tel, String sehir) {
	    	
	    	this.id = id;
	        this.servis = servis;
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
	    
	    public String getServis() {
			return servis;
		}

		public void setServis(String servis) {
			this.servis = servis;
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
		
		public ObservableList<Servis> GetAll(String secilenSehir) {
	        ObservableList<Servis> servisListesi = FXCollections.observableArrayList();

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

	                servisListesi.add(new Servis(id, servis, adres, tel, sehir));
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
