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
@Table(name = "produkti")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produkti.findAll", query = "SELECT p FROM Produkti p")
    , @NamedQuery(name = "Produkti.findById", query = "SELECT p FROM Produkti p WHERE p.id = :id")
    , @NamedQuery(name = "Produkti.findByEmri", query = "SELECT p FROM Produkti p WHERE p.emri = :emri")
    , @NamedQuery(name = "Produkti.findBySasia", query = "SELECT p FROM Produkti p WHERE p.sasia = :sasia")
    , @NamedQuery(name = "Produkti.findByBarcode", query = "SELECT p FROM Produkti p WHERE p.barcode = :barcode")
    , @NamedQuery(name = "Produkti.findByVendiProdhimit", query = "SELECT p FROM Produkti p WHERE p.vendiProdhimit = :vendiProdhimit")
    , @NamedQuery(name = "Produkti.findByQmimi", query = "SELECT p FROM Produkti p WHERE p.qmimi = :qmimi")})
public class Produkti implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Emri")
    private String emri;
    @Column(name = "Sasia")
    private Integer sasia;
    @Column(name = "Barcode")
    private String barcode;
    @Column(name = "VendiProdhimit")
    private String vendiProdhimit;
    @Column(name = "Qmimi")
    private BigInteger qmimi;
    @JoinColumn(name = "Stockid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Stock stockid;

    public Produkti() {
    }

    public Produkti(Integer id) {
        this.id = id;
    }

    public Produkti(Integer id, String emri) {
        this.id = id;
        this.emri = emri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public Integer getSasia() {
        return sasia;
    }

    public void setSasia(Integer sasia) {
        this.sasia = sasia;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getVendiProdhimit() {
        return vendiProdhimit;
    }

    public void setVendiProdhimit(String vendiProdhimit) {
        this.vendiProdhimit = vendiProdhimit;
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
        if (!(object instanceof Produkti)) {
            return false;
        }
        Produkti other = (Produkti) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.Produkti[ id=" + id + " ]";
    }
    
}
