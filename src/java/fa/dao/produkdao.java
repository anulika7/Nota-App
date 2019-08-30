package fa.dao;

import fa.model.Produk;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author G4u521
 */
public interface produkdao {
    public List<Produk> pList();
    public void newproduk(Produk pro);
    public void updateproduk(Produk pro);
    public void deleteproduk(Produk pro);
    
    public Produk carikodeproduk(Session sesi, String kodeproduk)throws Exception;
}
