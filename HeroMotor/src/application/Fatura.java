package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import com.ProjemMySQL.Util.VeritabaniUtil;

public class Fatura {
	
	private int id;
	private String ad;
	private String soy;
	private String urun;
	private String sase;
	private Date tarih;
	private double tutar;
	private String acıklama;
	

	Connection baglanti = null;
	PreparedStatement sorguIfadesi = null;
	String sql;
	    
	Fatura(){
		 
		baglanti=VeritabaniUtil.Baglan();

      }
	    
    public Fatura( int id, String ad, String soy, String urun, String sase, Date tarih, double tutar, String acıklama) {
    	
    	this.id=id;
        this.ad = ad;
        this.soy = soy;
        this.urun = urun;
        this.sase = sase;
        this.tarih = tarih;
        this.tutar = tutar;
        this.acıklama = acıklama;

    }
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getSoy() {
		return soy;
	}
	public void setSoy(String soy) {
		this.soy = soy;
	}
	public String getUrun() {
		return urun;
	}
	public void setUrun(String urun) {
		this.urun = urun;
	}
	public String getSase() {
		return sase;
	}
	public void setSase(String sase) {
		this.sase = sase;
	}
	public Date getTarih() {
		return tarih;
	}
	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}
	public double getTutar() {
		return tutar;
	}
	public void setTutar(double tutar) {
		this.tutar = tutar;
	}
	public String getAcıklama() {
		return acıklama;
	}
	public void setAcıklama(String acıklama) {
		this.acıklama = acıklama;
	}

	

}
