package fa.faces;

import fa.dao.usesdao;
import fa.filter.enkripnya;
import fa.model.Users;
import fa.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author G4u521
 */
public class usesface implements usesdao{

    @Override
    public Users finduser(Users us) {
      Users model=null;
      Session sesi=HibernateUtil.getSessionFactory().openSession();
      String hql="FROM Users WHERE nama=:nama";
      Query q=sesi.createQuery(hql);
      q.setParameter("nama", us.getNama());
      
      return (Users) q.uniqueResult();
    }

    @Override
    public Users Login(Users us) {
       Users model=this.finduser(us);
       if(model !=null){
           if(!model.getPass().equals(enkripnya.sha512(us.getPass()))){
               model=null;
           }
       }
       return model;
    }    
}
