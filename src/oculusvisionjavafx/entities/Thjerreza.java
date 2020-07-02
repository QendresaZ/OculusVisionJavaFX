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
@Table(name = "thjerreza")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Thjerreza.findAll", query = "SELECT t FROM Thjerreza t")
    , @NamedQuery(name = "Thjerreza.findById", query = "SELECT t FROM Thjerreza t WHERE t.id = :id")
    , @NamedQuery(name = "Thjerreza.findByGjeresia", query = "SELECT t FROM Thjerreza t WHERE t.gjeresia = :gjeresia")
    , @NamedQuery(name = "Thjerreza.findByGjatesia", query = "SELECT t FROM Thjerreza t WHERE t.gjatesia = :gjatesia")
    , @NamedQuery(name = "Thjerreza.findByVendiProdhimit", query = "SELECT t FROM Thjerreza t WHERE t.vendiProdhimit = :vendiProdhimit")
    , @NamedQuery(name = "Thjerreza.findByQmimi", query = "SELECT t FROM Thjerreza t WHERE t.qmimi = :qmimi")})
public class Thjerreza implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Gjeresia")
    private Float gjeresia;
    @Column(name = "Gjatesia")
    private Float gjatesia;
    @Column(name = "VendiProdhimit")
    private String vendiProdhimit;
    @Column(name = "Qmimi")
    private BigInteger qmimi;
    @JoinColumn(name = "Stockid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Stock stockid;

    public Thjerreza() {
    }

    public Thjerreza(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getGjeresia() {
        return gjeresia;
    }

    public void setGjeresia(Float gjeresia) {
        this.gjeresia = gjeresia;
    }

    public Float getGjatesia() {
        return gjatesia;
    }

    public void setGjatesia(Float gjatesia) {
        this.gjatesia = gjatesia;
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
        if (!(object instanceof Thjerreza)) {
            return false;
        }
        Thjerreza other = (Thjerreza) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.Thjerreza[ id=" + id + " ]";
    }
    
}
