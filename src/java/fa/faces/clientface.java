package fa.faces;

import fa.dao.clientdao;
import fa.model.Client;
import fa.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author G4u521
 */
public class clientface implements clientdao {

    @Override
    public List<Client> cList() {
        List<Client> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hql = "FROM Client";
        try {
            lista = session.createQuery(hql).list();
            tx.commit();
            session.close();
        } catch (Exception e) {
            tx.rollback();
        }
        return lista;
    }

    @Override
    public void newclient(Client client) {
        Session session=null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public void updateclient(Client client) {
        Session session=null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(client);
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public void deleteclient(Client client) {
        Session session=null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(client);
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public Client carikodeklien(Session session, Integer idklien) throws Exception {
       String hql="FROM Client WHERE idklien=:idklien";
       Query q=session.createQuery(hql);
       q.setParameter("idklien", idklien);
       
       return (Client) q.uniqueResult();
    }

}
