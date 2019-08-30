package fa.faces;

import fa.dao.produkdao;
import fa.model.Produk;
import fa.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author G4u521
 */
public class produkface implements produkdao {
    
    @Override
    public List<Produk> pList() {
        List<Produk> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hql = "FROM Produk";
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
    public void newproduk(Produk pro) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(pro);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public void updateproduk(Produk pro) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(pro);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public void deleteproduk(Produk pro) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(pro);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Produk carikodeproduk(Session sesi, String kodeproduk) throws Exception {
     String hql="FROM Produk WHERE kodeproduk=:kodeproduk";
     Query q=sesi.createQuery(hql);
     q.setParameter("kodeproduk", kodeproduk);
     
     return (Produk) q.uniqueResult();        
    }
    
}
