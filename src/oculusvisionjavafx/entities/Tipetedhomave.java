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
@Table(name = "tipetedhomave")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipetedhomave.findAll", query = "SELECT t FROM Tipetedhomave t")
    , @NamedQuery(name = "Tipetedhomave.findByTEDid", query = "SELECT t FROM Tipetedhomave t WHERE t.tEDid = :tEDid")
    , @NamedQuery(name = "Tipetedhomave.findByPershkrimi", query = "SELECT t FROM Tipetedhomave t WHERE t.pershkrimi = :pershkrimi")
    , @NamedQuery(name = "Tipetedhomave.findByTipi", query = "SELECT t FROM Tipetedhomave t WHERE t.tipi = :tipi")})
public class Tipetedhomave implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TEDid")
    private Integer tEDid;
    @Column(name = "Pershkrimi")
    private String pershkrimi;
    @Basic(optional = false)
    @Column(name = "Tipi")
    private String tipi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipetEDhomaveTEDid")
    private Collection<Dhoma> dhomaCollection;

    public Tipetedhomave() {
    }

    public Tipetedhomave(Integer tEDid) {
        this.tEDid = tEDid;
    }

    public Tipetedhomave(Integer tEDid, String tipi) {
        this.tEDid = tEDid;
        this.tipi = tipi;
    }

    public Integer getTEDid() {
        return tEDid;
    }

    public void setTEDid(Integer tEDid) {
        this.tEDid = tEDid;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public String getTipi() {
        return tipi;
    }

    public void setTipi(String tipi) {
        this.tipi = tipi;
    }

    @XmlTransient
    public Collection<Dhoma> getDhomaCollection() {
        return dhomaCollection;
    }

    public void setDhomaCollection(Collection<Dhoma> dhomaCollection) {
        this.dhomaCollection = dhomaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tEDid != null ? tEDid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipetedhomave)) {
            return false;
        }
        Tipetedhomave other = (Tipetedhomave) object;
        if ((this.tEDid == null && other.tEDid != null) || (this.tEDid != null && !this.tEDid.equals(other.tEDid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.Tipetedhomave[ tEDid=" + tEDid + " ]";
    }
    
}
