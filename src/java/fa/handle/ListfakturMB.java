package fa.handle;

import fa.dao.Fakturdao;
import fa.faces.Fakturface;
import fa.model.Faktur;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author G4u521
 */
@ManagedBean
@SessionScoped
public class ListfakturMB {

    private List<Faktur> listfa;
    
    public ListfakturMB() {
    }

    public void setListfa(List<Faktur> listfa) {
        this.listfa = listfa;
    }

    public List<Faktur> getListfa() {
       
        Fakturdao fado=new Fakturface();
        listfa=fado.fList();
        return listfa;
    }
    
}
