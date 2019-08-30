package fa.handle;

import fa.dao.clientdao;
import fa.faces.clientface;
import fa.model.Client;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author G4u521
 */
@ManagedBean
@ViewScoped
public class clientMB {
   
    private List<Client> calist;
    private Client client;

    public clientMB() {
    }

    public void setCalist(List<Client> calist) {
        this.calist = calist;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Client> getcalist() {
        clientdao cdao=new clientface();
        calist=cdao.cList();
        return calist;
    }
    public void prosesdataclient(){
        client=new Client();
    }
    
    public void databaru(){
        clientdao cdao=new clientface();
        cdao.newclient(client);
    }
    
    public void dataupdate(){
     clientdao cdao=new clientface();
     cdao.updateclient(client);
     client=new Client();
    }

    public void hapusdata() {
        clientdao cdao = new clientface();
        cdao.deleteclient(client);
        client = new Client();
    }
}
