package fa.model;
// Generated May 20, 2016 11:09:48 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Produk generated by hbm2java
 */
public class Produk  implements java.io.Serializable {


     private Integer idproduk;
     private String namaproduk;
     private BigDecimal hargajual;
     private int stokminimal;
     private int stokterkini;
     private String kodeproduk;
     private Set detilfakturs = new HashSet(0);

    public Produk() {
    }

	
    public Produk(String namaproduk, BigDecimal hargajual, int stokminimal, int stokterkini, String kodeproduk) {
        this.namaproduk = namaproduk;
        this.hargajual = hargajual;
        this.stokminimal = stokminimal;
        this.stokterkini = stokterkini;
        this.kodeproduk = kodeproduk;
    }
    public Produk(String namaproduk, BigDecimal hargajual, int stokminimal, int stokterkini, String kodeproduk, Set detilfakturs) {
       this.namaproduk = namaproduk;
       this.hargajual = hargajual;
       this.stokminimal = stokminimal;
       this.stokterkini = stokterkini;
       this.kodeproduk = kodeproduk;
       this.detilfakturs = detilfakturs;
    }
   
    public Integer getIdproduk() {
        return this.idproduk;
    }
    
    public void setIdproduk(Integer idproduk) {
        this.idproduk = idproduk;
    }
    public String getNamaproduk() {
        return this.namaproduk;
    }
    
    public void setNamaproduk(String namaproduk) {
        this.namaproduk = namaproduk;
    }
    public BigDecimal getHargajual() {
        return this.hargajual;
    }
    
    public void setHargajual(BigDecimal hargajual) {
        this.hargajual = hargajual;
    }
    public int getStokminimal() {
        return this.stokminimal;
    }
    
    public void setStokminimal(int stokminimal) {
        this.stokminimal = stokminimal;
    }
    public int getStokterkini() {
        return this.stokterkini;
    }
    
    public void setStokterkini(int stokterkini) {
        this.stokterkini = stokterkini;
    }
    public String getKodeproduk() {
        return this.kodeproduk;
    }
    
    public void setKodeproduk(String kodeproduk) {
        this.kodeproduk = kodeproduk;
    }
    public Set getDetilfakturs() {
        return this.detilfakturs;
    }
    
    public void setDetilfakturs(Set detilfakturs) {
        this.detilfakturs = detilfakturs;
    }




}


