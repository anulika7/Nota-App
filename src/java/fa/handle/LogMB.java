package fa.handle;

import fa.dao.usesdao;
import fa.faces.usesface;
import fa.model.Users;
import java.awt.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author G4u521
 */
@ManagedBean
@SessionScoped
public class LogMB {

    private Users us;
    private String nama;
    private String pass;

    public LogMB() {
      this.us=new Users(); 
    }

    public Users getUs() {
        return us;
    }

    public void setUs(Users us) {
        this.us = us;
    }

  
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void Login(ActionEvent event){
        RequestContext rc=RequestContext.getCurrentInstance();
        FacesMessage message=null;
        boolean loggedIn=false;
        String rules="";
        
        usesdao usdo=new usesface();
        this.us=usdo.Login(this.us);
                
        if(this.us!=null){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("us", this.us.getNama());
            
            loggedIn=true;
            message=new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome@", this.us.getNama());
            rules="/JBossFaktur/planing/planing.xhtml";
        }else{
            loggedIn=false;
            message=new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Failed", "Invalid username atau password");
            this.us=new Users();
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        rc.addCallbackParam("loggedIn", loggedIn);
        rc.addCallbackParam("rules", rules);
    }
    
    public String cerrarSession(){
        this.nama=null;
        this.pass=null;
        
        HttpSession hts=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        hts.invalidate();
        
        return"/Login";
    }
}
