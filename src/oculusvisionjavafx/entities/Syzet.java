/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Qendresa
 */
@Entity
@Table(name = "syzet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Syzet.findAll", query = "SELECT s FROM Syzet s")
    , @NamedQuery(name = "Syzet.findById", query = "SELECT s FROM Syzet s WHERE s.id = :id")
    , @NamedQuery(name = "Syzet.findByNgjyra", query = "SELECT s FROM Syzet s WHERE s.ngjyra = :ngjyra")
    , @NamedQuery(name = "Syzet.findByBrendi", query = "SELECT s FROM Syzet s WHERE s.brendi = :brendi")
    , @NamedQuery(name = "Syzet.findByVendiProdhimit", query = "SELECT s FROM Syzet s WHERE s.vendiProdhimit = :vendiProdhimit")
    , @NamedQuery(name = "Syzet.findByBarcode", query = "SELECT s FROM Syzet s WHERE s.barcode = :barcode")
    , @NamedQuery(name = "Syzet.findByQmimi", query = "SELECT s FROM Syzet s WHERE s.qmimi = :qmimi")})
public class Syzet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Ngjyra")
    private String ngjyra;
    @Column(name = "Brendi")
    private String brendi;
    @Column(name = "VendiProdhimit")
    private String vendiProdhimit;
    @Column(name = "barcode")
    private String barcode;
    @Column(name = "Qmimi")
    private BigInteger qmimi;
    @JoinColumn(name = "Stockid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Stock stockid;

    public Syzet() {
    }

    public Syzet(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNgjyra() {
        return ngjyra;
    }

    public void setNgjyra(String ngjyra) {
        this.ngjyra = ngjyra;
    }

    public String getBrendi() {
        return brendi;
    }

    public void setBrendi(String brendi) {
        this.brendi = brendi;
    }

    public String getVendiProdhimit() {
        return vendiProdhimit;
    }

    public void setVendiProdhimit(String vendiProdhimit) {
        this.vendiProdhimit = vendiProdhimit;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public BigInteger getQmimi() {
        return qmimi;
    }

    public void setQmimi(BigInteger qmimi) {
        this.qmimi = qmimi;
    }

    public Stock getStockid() {
        return stockid;
    }

    public void setStockid(Stock stockid) {
        this.stockid = stockid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Syzet)) {
            return false;
        }
        Syzet other = (Syzet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.Syzet[ id=" + id + " ]";
    }
    
}
