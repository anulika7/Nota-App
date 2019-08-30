package fa.dao;

import fa.model.Vendor;
import java.util.List;

/**
 *
 * @author G4u521
 */
public interface vendordao {
    public List<Vendor> vList();
    public void newvendor(Vendor vendor);
    public void updatevendor(Vendor vendor);
    public void deletevendor(Vendor vendor);
}
