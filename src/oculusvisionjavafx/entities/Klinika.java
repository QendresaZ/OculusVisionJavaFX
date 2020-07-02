/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Qendresa
 */
@Entity
@Table(name = "klinika")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klinika.findAll", query = "SELECT k FROM Klinika k")
    , @NamedQuery(name = "Klinika.findById", query = "SELECT k FROM Klinika k WHERE k.id = :id")
    , @NamedQuery(name = "Klinika.findByEmri", query = "SELECT k FROM Klinika k WHERE k.emri = :emri")
    , @NamedQuery(name = "Klinika.findByAdresa", query = "SELECT k FROM Klinika k WHERE k.adresa = :adresa")
    , @NamedQuery(name = "Klinika.findByPershkrimi", query = "SELECT k FROM Klinika k WHERE k.pershkrimi = :pershkrimi")})
public class Klinika implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Emri")
    private String emri;
    @Basic(optional = false)
    @Column(name = "Adresa")
    private String adresa;
    @Basic(optional = false)
    @Column(name = "Pershkrimi")
    private String pershkrimi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klinikaID")
    private Collection<Dhoma> dhomaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klinikaID")
    private Collection<Stock> stockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klinikaID")
    private Collection<Stafi> stafiCollection;

    public Klinika() {
    }

    public Klinika(Integer id) {
        this.id = id;
    }

    public Klinika(Integer id, String emri, String adresa, String pershkrimi) {
        this.id = id;
        this.emri = emri;
        this.adresa = adresa;
        this.pershkrimi = pershkrimi;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    @XmlTransient
    public Collection<Dhoma> getDhomaCollection() {
        return dhomaCollection;
    }

    public void setDhomaCollection(Collection<Dhoma> dhomaCollection) {
        this.dhomaCollection = dhomaCollection;
    }

    @XmlTransient
    public Collection<Stock> getStockCollection() {
        return stockCollection;
    }

    public void setStockCollection(Collection<Stock> stockCollection) {
        this.stockCollection = stockCollection;
    }

    @XmlTransient
    public Collection<Stafi> getStafiCollection() {
        return stafiCollection;
    }

    public void setStafiCollection(Collection<Stafi> stafiCollection) {
        this.stafiCollection = stafiCollection;
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
        if (!(object instanceof Klinika)) {
            return false;
        }
        Klinika other = (Klinika) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.Klinika[ id=" + id + " ]";
    }
    
}
