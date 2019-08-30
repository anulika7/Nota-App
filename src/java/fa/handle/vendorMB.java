package fa.handle;

import fa.dao.vendordao;
import fa.faces.vendorface;
import fa.model.Vendor;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author G4u521
 */
@ManagedBean
@ViewScoped
public class vendorMB {

    private List<Vendor> venlist;
    private Vendor vendor;

    public vendorMB() {
    }

    public void setVenlist(List<Vendor> venlist) {
        this.venlist = venlist;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public List<Vendor> getvenlist() {
        vendordao vd = new vendorface();
        venlist = vd.vList();
        return venlist;
    }

    public void prosesdata() {
        vendor = new Vendor();
    }

    public void databaru() {
        vendordao vd = new vendorface();
        vd.newvendor(vendor);
    }

    public void dataupdate() {
        vendordao vd = new vendorface();
        vd.updatevendor(vendor);
        vendor = new Vendor();
    }

    public void hapusdata() {
        vendordao vd = new vendorface();
        vd.deletevendor(vendor);
        vendor =new Vendor();
    }
}
