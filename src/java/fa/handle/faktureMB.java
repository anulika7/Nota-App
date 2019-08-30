package fa.handle;

import fa.dao.Detilfakturdao;
import fa.dao.Fakturdao;
import fa.dao.clientdao;
import fa.dao.produkdao;
import fa.faces.Detilfakturface;
import fa.faces.Fakturface;
import fa.faces.clientface;
import fa.faces.produkface;
import fa.model.Client;
import fa.model.Detilfaktur;
import fa.model.Faktur;
import fa.model.Produk;
import fa.model.Vendor;
import fa.util.HibernateUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author G4u521
 */
@ManagedBean
@ViewScoped
public class faktureMB {

    Session sesi = null;
    Transaction trx = null;

    private Client client;
    private Integer kodeklien;

    private Produk pro;
    private String kodeproduk;

    private List<Detilfaktur> deList;
    private String kuantitasini;
    private String kuantitas;
    private String pilihproduk;

    private Faktur faktur;
    private Long nofaktur;
    private BigDecimal Totaljual;
    private Vendor vendor;

    public faktureMB() {
        this.faktur = new Faktur();
        this.deList = new ArrayList<>();
        this.vendor = new Vendor();
        this.client = new Client();
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getKuantitas() {
        return kuantitas;
    }

    public BigDecimal getTotaljual() {
        return Totaljual;
    }

    public void setTotaljual(BigDecimal Totaljual) {
        this.Totaljual = Totaljual;
    }

    public Long getNofaktur() {
        return nofaktur;
    }

    public void setNofaktur(long nofaktur) {
        this.nofaktur = nofaktur;
    }

    public String getKuantitasini() {
        return kuantitasini;
    }

    public void setKuantitasini(String kuantitasini) {
        this.kuantitasini = kuantitasini;
    }

    public void setKuantitas(String kuantitas) {
        this.kuantitas = kuantitas;
    }

    public String getPilihproduk() {
        return pilihproduk;
    }

    public void setPilihproduk(String pilihproduk) {
        this.pilihproduk = pilihproduk;
    }

    public Faktur getFaktur() {
        return faktur;
    }

    public void setFaktur(Faktur faktur) {
        this.faktur = faktur;
    }

    public List<Detilfaktur> getDeList() {
        return deList;
    }

    public void setDeList(List<Detilfaktur> deList) {
        this.deList = deList;
    }

    public Produk getPro() {
        return pro;
    }

    public void setPro(Produk pro) {
        this.pro = pro;
    }

    public String getKodeproduk() {
        return kodeproduk;
    }

    public void setKodeproduk(String kodeproduk) {
        this.kodeproduk = kodeproduk;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getKodeklien() {
        return kodeklien;
    }

    public void setKodeklien(Integer kodeklien) {
        this.kodeklien = kodeklien;
    }

    public void getkodeklien(Integer idklien) {
        this.sesi = null;
        this.trx = null;
        try {
            this.sesi = HibernateUtil.getSessionFactory().openSession();
            clientdao cdao = new clientface();
            this.trx = this.sesi.beginTransaction();
            this.client = cdao.carikodeklien(this.sesi, idklien);

            this.trx.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ok", "data ditemukan"));

        } catch (Exception e) {
            if (this.trx != null) {
                System.out.println(e.getMessage());
                this.trx.rollback();
            }
        } finally {
            if (this.sesi != null) {
                this.sesi.close();
            }
        }
    }

    public void getkodeklienitu() {
        this.sesi = null;
        this.trx = null;
        try {
            if (this.kodeklien == null) {
                return;
            }
            this.sesi = HibernateUtil.getSessionFactory().openSession();
            clientdao cdao = new clientface();
            this.trx = this.sesi.beginTransaction();
            this.client = cdao.carikodeklien(this.sesi, this.kodeklien);

            if (this.client != null) {
                this.kodeklien = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ok", "data ditemukan"));
            } else {
                this.kodeklien = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "no data", "cek lagi data-nya"));
            }

            this.trx.commit();

        } catch (Exception e) {
            if (this.trx != null) {
                System.out.println(e.getMessage());
                this.trx.rollback();
            }
        } finally {
            if (this.sesi != null) {
                this.sesi.close();
            }
        }
    }

    public void getkuatitasproduk(String kodeproduk) {
        this.pilihproduk = kodeproduk;
    }

    public void getkodeproduk() {
        this.sesi = null;
        this.trx = null;

        try {
            if (!(this.kuantitas.matches("[0-9]*")) || this.kuantitas.equals("0") || this.kuantitas.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "no data", "cek lagi inputan kuantitas-nya"));
                this.kuantitas = "";
            } else {
                this.sesi = HibernateUtil.getSessionFactory().openSession();
                produkdao pdao = new produkface();
                this.trx = this.sesi.beginTransaction();
                this.pro = pdao.carikodeproduk(this.sesi, this.pilihproduk);

                this.deList.add(new Detilfaktur(null, null, this.pro.getKodeproduk(), this.pro.getNamaproduk(), Integer.parseInt(this.kuantitas),
                        this.pro.getHargajual(), BigDecimal.valueOf(Integer.parseInt(this.kuantitas) * this.pro.getHargajual().floatValue())));
                this.trx.commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ok", "data ditemukan"));

                this.kuantitas = "";

                //**nota getter**//
                this.totalnota();
            }

        } catch (Exception e) {
            if (this.trx != null) {
                System.out.println(e.getMessage());
                this.trx.rollback();
            }
        } finally {
            if (this.sesi != null) {
                this.sesi.close();
            }
        }
    }

    public void getkuantitasini() {
        this.sesi = null;
        this.trx = null;
        try {
            if (this.kodeproduk.equals("")) {
                return;
            }
            this.sesi = HibernateUtil.getSessionFactory().openSession();
            produkdao pdao = new produkface();
            this.trx = this.sesi.beginTransaction();

            this.pro = pdao.carikodeproduk(this.sesi, this.kodeproduk);

            if (this.pro != null) {

                RequestContext rc = RequestContext.getCurrentInstance();
                rc.execute("PF('dialogkuantitasi').show();");

                this.kodeproduk = null;
            } else {
                this.kodeproduk = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "no data", "cek lagi data-nya"));
            }
            this.trx.commit();

        } catch (Exception e) {
            if (this.trx != null) {
                System.out.println(e.getMessage());
                this.trx.rollback();
            }
        } finally {
            if (this.sesi != null) {
                this.sesi.close();
            }
        }
    }

    public void getkodeprodukini() {
        if (!(this.kuantitasini.matches("[0-9]*")) || this.kuantitasini.equals("0") || this.kuantitasini.equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "no data", "cek lagi inputan kuantitas-nya"));
            this.kuantitasini = "";
        } else {
            this.deList.add(new Detilfaktur(null, null, this.pro.getKodeproduk(), this.pro.getNamaproduk(), Integer.parseInt(this.kuantitasini),
                    this.pro.getHargajual(), BigDecimal.valueOf(Integer.parseInt(this.kuantitasini) * this.pro.getHargajual().floatValue())));

            this.kuantitasini = "";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ok", "data ditemukan"));

            ////getter nota
            this.totalnota();
        }
    }

    public void totalnota() {
        this.Totaljual = new BigDecimal("0");

        try {
            for (Detilfaktur item : deList) {
                BigDecimal totalprodukjual = item.getHargajual().multiply(new BigDecimal(item.getJumlah()));
                item.setTotalharga(totalprodukjual);
                this.Totaljual = this.Totaljual.add(totalprodukjual);
            }
            this.faktur.setTotalharga(this.Totaljual);
        } catch (Exception e) {
            System.out.println("total bayar" + this.faktur.getTotalharga());
        }
    }

    public void batalin(String kodeproduk, Integer fileterseleksi) {
        try {
            int i = 0;
            for (Detilfaktur item : this.deList) {
                if (item.getKodeproduk().equals(kodeproduk) && fileterseleksi.equals(i)) {
                    this.deList.remove(i);
                    break;
                }
                i++;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "info", "1 daftar list dihapus"));
            this.totalnota();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "error", "erroe"));
        }
    }

    public void onRowEdit(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "info", "kuantitas telah di edit"));

        this.totalnota();
    }

    public void onRowCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "info", "kuantitas batal di edit"));
    }

    public void getnomerfaktur() {
        this.sesi = null;
        this.trx = null;

        try {
            this.sesi = HibernateUtil.getSessionFactory().openSession();
            this.trx = this.sesi.beginTransaction();
            Fakturdao FD = new Fakturface();
            //get idfaktur
            this.nofaktur = FD.getobservefaktur(this.sesi);
            if (this.nofaktur <= 0 || this.nofaktur == null) {
                this.nofaktur = Long.valueOf("1");
            } else {
                this.faktur = FD.getdatafaktur(this.sesi);
                this.nofaktur = Long.valueOf(this.faktur.getIdfaktur() + 1);
                //get totalharga tabel faktur
                this.Totaljual = new BigDecimal("0");
            }
            this.trx.commit();
        } catch (Exception e) {
            if (this.trx != null) {
                this.trx.rollback();
            }
            System.out.print(e.getMessage());
        } finally {
            if (this.sesi != null) {
                this.sesi.close();
            }
        }

    }

    public void pembatalanFaktur() {
        this.client = new Client();
        this.faktur = new Faktur();
        this.deList = new ArrayList<>();
        this.nofaktur = null;
        this.Totaljual = null;

        this.disableButton();
    }

    public void savefakturnya() {
        this.sesi = null;
        this.trx = null;
        
        try {
            this.sesi = HibernateUtil.getSessionFactory().openSession();
            produkdao pdo = new produkface();
            Fakturdao fdo = new Fakturface();
            Detilfakturdao dfdo = new Detilfakturface();
            
            
            this.trx = this.sesi.beginTransaction();
            //save objek ke faktur
            this.faktur.setNofaktur(this.nofaktur.intValue());
            this.faktur.setClient(this.client);
           
            //get save ke faktur
            fdo.simpandataFaktur(this.sesi, this.faktur);
            //get insert session faktur
            this.faktur = fdo.getdatafaktur(this.sesi);
            for (Detilfaktur item : deList) {
                this.pro = pdo.carikodeproduk(this.sesi, item.getKodeproduk());
                item.setFaktur(this.faktur);
                item.setProduk(this.pro);
                //save detilfaktur
                dfdo.simpandetilFaktur(this.sesi, item);
            }
            this.trx.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ok", "data disimpan"));
            this.pembatalanFaktur();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (this.trx != null) {
                this.trx.rollback();
            }
        } finally {
            if (this.sesi != null) {
                this.sesi.close();
            }
        }
    }

    public boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void enableButton() {
        enable = true;
    }

    public void disableButton() {
        enable = false;
    }

    public String kalenderSystem;

    public String getKalenderSystem() {
        Calendar call=new GregorianCalendar();
        
        int tahun=call.get(Calendar.YEAR);
        int bulan=call.get(Calendar.MONTH);
        int hari=call.get(Calendar.DAY_OF_MONTH);
        
        this.kalenderSystem=(hari+"/"+(bulan+1)+"/"+tahun);
        
        return kalenderSystem;
    }

}
