package fa.dao;

import fa.model.Detilfaktur;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author G4u521
 */
public interface Detilfakturdao {
    
    public List<Detilfaktur> dfList();
    
    public boolean simpandetilFaktur(Session sesi, Detilfaktur detilfaktur)throws Exception;
    
}
