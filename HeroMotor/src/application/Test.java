package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ProjemMySQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Test {
	    private int id;
		private String isim;
	    private String soy;
	    private int tel;
		private String mail;
		private String model;
		private String sehir;
		private String mesaj;
		
		
		 Connection baglanti = null;
		 PreparedStatement sorguIfadesi = null;
	     String sql;
		    
	     Test(){
        	 
    		baglanti=VeritabaniUtil.Baglan();

	    }

	    public Test(int id, String isim, String soy, int tel, String mail, String model,String sehir, String mesaj) {
	    	
	    	this.id = id;
	        this.isim = isim;
	        this.soy = soy;
	        this.tel = tel;
	        this.mail = mail;
	        this.model = model;
	        this.sehir = sehir;
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

		public int getTel() {
			return tel;
		}

		public void setTel(int tel) {
			this.tel = tel;
		}

		public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getSehir() {
			return sehir;
		}

		public void setSehir(String sehir) {
			this.sehir = sehir;
		}

		public String getMesaj() {
			return mesaj;
		}

		public void setMesaj(String mesaj) {
			this.mesaj = mesaj;
		}

		
	    
		public Boolean Update(int id) {
			this.id=id;
			
			return true;
		}

}
