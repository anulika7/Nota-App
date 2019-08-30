package fa.dao;

import fa.model.Client;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author G4u521
 */
public interface clientdao {
    public List<Client> cList();
    public void newclient(Client client);
    public void updateclient(Client client);
    public void deleteclient(Client client);
    
    //get session data klien
    
    public Client carikodeklien(Session session, Integer idklien)throws Exception;
}
