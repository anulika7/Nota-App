package fa.faces;

import fa.dao.Detilfakturdao;
import fa.model.Detilfaktur;
import fa.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author G4u521
 */
public class Detilfakturface implements Detilfakturdao {

    @Override
    public List<Detilfaktur> dfList() {
        List<Detilfaktur> dfalist = null;
        Session sesi = HibernateUtil.getSessionFactory().openSession();
        Transaction trx = sesi.beginTransaction();
        String hql = "FROM Detilfaktur";

        try {
            dfalist = sesi.createQuery(hql).list();
            trx.commit();
            sesi.close();
        } catch (Exception e) {
            trx.rollback();
        }
        return dfalist;
    }

    @Override
    public boolean simpandetilFaktur(Session sesi, Detilfaktur detilfaktur) throws Exception {
        sesi.save(detilfaktur);
        return true;
    }

}
