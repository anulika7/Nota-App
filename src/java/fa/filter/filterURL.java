package fa.filter;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author G4u521
 */
public class filterURL implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent pe) {
     FacesContext fc=pe.getFacesContext();
     
     String currentPage=fc.getViewRoot().getViewId();
     
     boolean isPageLogin=currentPage.lastIndexOf("Login.xhtml") > -1 ?true:false;
     
     HttpSession hs=(HttpSession) fc.getExternalContext().getSession(true);
     
     Object us=hs.getAttribute("us");
     if(!isPageLogin && us==null){
         NavigationHandler nhand=fc.getApplication().getNavigationHandler();
         nhand.handleNavigation(fc, null, "/Login.xhtml");
     }
     
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        
    }

    @Override
    public PhaseId getPhaseId() {
       return PhaseId.RESTORE_VIEW;
    }
    
    
}
