/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "dhoma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dhoma.findAll", query = "SELECT d FROM Dhoma d")
    , @NamedQuery(name = "Dhoma.findByNumri", query = "SELECT d FROM Dhoma d WHERE d.numri = :numri")
    , @NamedQuery(name = "Dhoma.findByQmimi", query = "SELECT d FROM Dhoma d WHERE d.qmimi = :qmimi")
    , @NamedQuery(name = "Dhoma.findByStatusi", query = "SELECT d FROM Dhoma d WHERE d.statusi = :statusi")
    , @NamedQuery(name = "Dhoma.findByPershkrimi", query = "SELECT d FROM Dhoma d WHERE d.pershkrimi = :pershkrimi")})
public class Dhoma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Numri")
    private Integer numri;
    @Column(name = "Qmimi")
    private BigInteger qmimi;
    @Column(name = "Statusi")
    private Boolean statusi;
    @Column(name = "Pershkrimi")
    private String pershkrimi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dhomaNumri")
    private Collection<PajisjeNeDhoma> pajisjeNeDhomaCollection;
    @JoinColumn(name = "KlinikaID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Klinika klinikaID;
    @JoinColumn(name = "OperacioniId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Operacioni operacioniId;
    @JoinColumn(name = "TipetEDhomaveTEDid", referencedColumnName = "TEDid")
    @ManyToOne(optional = false)
    private Tipetedhomave tipetEDhomaveTEDid;

    public Dhoma() {
    }

    public Dhoma(Integer numri) {
        this.numri = numri;
    }

    public Integer getNumri() {
        return numri;
    }

    public void setNumri(Integer numri) {
        this.numri = numri;
    }

    public BigInteger getQmimi() {
        return qmimi;
    }

    public void setQmimi(BigInteger qmimi) {
        this.qmimi = qmimi;
    }

    public Boolean getStatusi() {
        return statusi;
    }

    public void setStatusi(Boolean statusi) {
        this.statusi = statusi;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    @XmlTransient
    public Collection<PajisjeNeDhoma> getPajisjeNeDhomaCollection() {
        return pajisjeNeDhomaCollection;
    }

    public void setPajisjeNeDhomaCollection(Collection<PajisjeNeDhoma> pajisjeNeDhomaCollection) {
        this.pajisjeNeDhomaCollection = pajisjeNeDhomaCollection;
    }

    public Klinika getKlinikaID() {
        return klinikaID;
    }

    public void setKlinikaID(Klinika klinikaID) {
        this.klinikaID = klinikaID;
    }

    public Operacioni getOperacioniId() {
        return operacioniId;
    }

    public void setOperacioniId(Operacioni operacioniId) {
        this.operacioniId = operacioniId;
    }

    public Tipetedhomave getTipetEDhomaveTEDid() {
        return tipetEDhomaveTEDid;
    }

    public void setTipetEDhomaveTEDid(Tipetedhomave tipetEDhomaveTEDid) {
        this.tipetEDhomaveTEDid = tipetEDhomaveTEDid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numri != null ? numri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dhoma)) {
            return false;
        }
        Dhoma other = (Dhoma) object;
        if ((this.numri == null && other.numri != null) || (this.numri != null && !this.numri.equals(other.numri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.Dhoma[ numri=" + numri + " ]";
    }
    
}
