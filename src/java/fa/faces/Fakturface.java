package fa.faces;

import fa.dao.Fakturdao;
import fa.model.Faktur;
import fa.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author G4u521
 */
public class Fakturface implements Fakturdao {

    @Override
    public List<Faktur> fList() {
        List<Faktur> falist = null;
        Session sesi = HibernateUtil.getSessionFactory().openSession();
        Transaction trx = sesi.beginTransaction();
        String hql = "FROM Faktur";

        try {
            falist = sesi.createQuery(hql).list();
            trx.commit();
            sesi.close();
        } catch (Exception e) {
            trx.rollback();
        }

        return falist;
    }

    @Override
    public Faktur getdatafaktur(Session sesi) throws Exception {
        String hql = "FROM Faktur ORDER BY idfaktur DESC";
        Query q = sesi.createQuery(hql).setMaxResults(1);

        return (Faktur) q.uniqueResult();
    }

    @Override
    public long getobservefaktur(Session sesi) {
        String hql = "SELECT COUNT(*)FROM Faktur";
        Query q = sesi.createQuery(hql);

        return (long) q.uniqueResult();
    }

    @Override
    public boolean simpandataFaktur(Session sesi, Faktur faktur) throws Exception {
        sesi.save(faktur);
        return true;
    }

}
