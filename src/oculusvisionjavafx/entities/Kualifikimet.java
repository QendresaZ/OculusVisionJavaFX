/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Qendresa
 */
@Entity
@Table(name = "kualifikimet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kualifikimet.findAll", query = "SELECT k FROM Kualifikimet k")
    , @NamedQuery(name = "Kualifikimet.findById", query = "SELECT k FROM Kualifikimet k WHERE k.id = :id")
    , @NamedQuery(name = "Kualifikimet.findByPershkrimi", query = "SELECT k FROM Kualifikimet k WHERE k.pershkrimi = :pershkrimi")
    , @NamedQuery(name = "Kualifikimet.findByVitiKualfikimit", query = "SELECT k FROM Kualifikimet k WHERE k.vitiKualfikimit = :vitiKualfikimit")
    , @NamedQuery(name = "Kualifikimet.findByFakulteti", query = "SELECT k FROM Kualifikimet k WHERE k.fakulteti = :fakulteti")
    , @NamedQuery(name = "Kualifikimet.findByShteti", query = "SELECT k FROM Kualifikimet k WHERE k.shteti = :shteti")
    , @NamedQuery(name = "Kualifikimet.findByEmri", query = "SELECT k FROM Kualifikimet k WHERE k.emri = :emri")})
public class Kualifikimet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Pershkrimi")
    private String pershkrimi;
    @Basic(optional = false)
    @Column(name = "vitiKualfikimit")
    @Temporal(TemporalType.DATE)
    private Date vitiKualfikimit;
    @Basic(optional = false)
    @Column(name = "Fakulteti")
    private String fakulteti;
    @Basic(optional = false)
    @Column(name = "Shteti")
    private String shteti;
    @Column(name = "Emri")
    private String emri;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kualifikimetID")
    private Collection<Stafi> stafiCollection;

    public Kualifikimet() {
    }

    public Kualifikimet(Integer id) {
        this.id = id;
    }

    public Kualifikimet(Integer id, String pershkrimi, Date vitiKualfikimit, String fakulteti, String shteti) {
        this.id = id;
        this.pershkrimi = pershkrimi;
        this.vitiKualfikimit = vitiKualfikimit;
        this.fakulteti = fakulteti;
        this.shteti = shteti;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public Date getVitiKualfikimit() {
        return vitiKualfikimit;
    }

    public void setVitiKualfikimit(Date vitiKualfikimit) {
        this.vitiKualfikimit = vitiKualfikimit;
    }

    public String getFakulteti() {
        return fakulteti;
    }

    public void setFakulteti(String fakulteti) {
        this.fakulteti = fakulteti;
    }

    public String getShteti() {
        return shteti;
    }

    public void setShteti(String shteti) {
        this.shteti = shteti;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
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
        if (!(object instanceof Kualifikimet)) {
            return false;
        }
        Kualifikimet other = (Kualifikimet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.Kualifikimet[ id=" + id + " ]";
    }
    
}
