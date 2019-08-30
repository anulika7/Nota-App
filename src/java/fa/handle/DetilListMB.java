package fa.handle;

import fa.dao.Detilfakturdao;
import fa.faces.Detilfakturface;
import fa.model.Detilfaktur;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author G4u521
 */
@ManagedBean
@SessionScoped
public class DetilListMB {

    private List<Detilfaktur> delistfa;
    
    public DetilListMB() {
    }
   
    public void setDelistfa(List<Detilfaktur> delistfa) {
        this.delistfa = delistfa;
    }

    public List<Detilfaktur> getDelistfa() {
        
        Detilfakturdao dfdo=new Detilfakturface();
        delistfa=dfdo.dfList();
        return delistfa;
    }
    
}
