package fa.handle;

import fa.dao.produkdao;
import fa.faces.produkface;
import fa.model.Produk;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author G4u521
 */
@ManagedBean
@ViewScoped
public class produkMB {

    private List<Produk> prolist;
    private Produk pro;
    public produkMB() {
    }

    public void setProlist(List<Produk> prolist) {
        this.prolist = prolist;
    }

    public Produk getPro() {
        return pro;
    }

    public void setPro(Produk pro) {
        this.pro = pro;
    }

    public List<Produk> getprolist() {
        produkdao pd=new produkface();
        prolist=pd.pList();
        return prolist;
    }
     public void prosesdata(){
        pro=new Produk();
    }
    
    public void databaru(){
        produkdao pd=new produkface();
        pd.newproduk(pro);
    }
    
    public void dataupdate(){
     produkdao pd=new produkface();
     pd.updateproduk(pro);
     pro=new Produk();
    }

    public void hapusdata() {
        produkdao pd=new produkface();
        pd.deleteproduk(pro);
        pro=new Produk();
    }
}
