package fa.dao;

import fa.model.Faktur;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author G4u521
 */
public interface Fakturdao {
    
    public List<Faktur> fList();

    public Faktur getdatafaktur(Session sesi) throws Exception;

    public long getobservefaktur(Session sesi);

    public boolean simpandataFaktur(Session sesi, Faktur faktur) throws Exception;

}
