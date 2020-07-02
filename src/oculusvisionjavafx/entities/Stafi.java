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
@Table(name = "stafi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stafi.findAll", query = "SELECT s FROM Stafi s")
    , @NamedQuery(name = "Stafi.findById", query = "SELECT s FROM Stafi s WHERE s.id = :id")
    , @NamedQuery(name = "Stafi.findByEmri", query = "SELECT s FROM Stafi s WHERE s.emri = :emri")
    , @NamedQuery(name = "Stafi.findByMbiemri", query = "SELECT s FROM Stafi s WHERE s.mbiemri = :mbiemri")
    , @NamedQuery(name = "Stafi.findByGjinia", query = "SELECT s FROM Stafi s WHERE s.gjinia = :gjinia")})
public class Stafi implements Serializable {

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
    @Column(name = "Mbiemri")
    private String mbiemri;
    @Basic(optional = false)
    @Column(name = "Gjinia")
    private Character gjinia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stafiID")
    private Collection<Termini> terminiCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stafiID")
    private Collection<HealthRecords> healthRecordsCollection;
    @JoinColumn(name = "KualifikimetID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Kualifikimet kualifikimetID;
    @JoinColumn(name = "KlinikaID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Klinika klinikaID;
    @JoinColumn(name = "RoliStafitID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Rolistafit roliStafitID;

    public Stafi() {
    }

    public Stafi(Integer id) {
        this.id = id;
    }

    public Stafi(Integer id, String emri, String mbiemri, Character gjinia) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.gjinia = gjinia;
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

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public Character getGjinia() {
        return gjinia;
    }

    public void setGjinia(Character gjinia) {
        this.gjinia = gjinia;
    }

    @XmlTransient
    public Collection<Termini> getTerminiCollection() {
        return terminiCollection;
    }

    public void setTerminiCollection(Collection<Termini> terminiCollection) {
        this.terminiCollection = terminiCollection;
    }

    @XmlTransient
    public Collection<HealthRecords> getHealthRecordsCollection() {
        return healthRecordsCollection;
    }

    public void setHealthRecordsCollection(Collection<HealthRecords> healthRecordsCollection) {
        this.healthRecordsCollection = healthRecordsCollection;
    }

    public Kualifikimet getKualifikimetID() {
        return kualifikimetID;
    }

    public void setKualifikimetID(Kualifikimet kualifikimetID) {
        this.kualifikimetID = kualifikimetID;
    }

    public Klinika getKlinikaID() {
        return klinikaID;
    }

    public void setKlinikaID(Klinika klinikaID) {
        this.klinikaID = klinikaID;
    }

    public Rolistafit getRoliStafitID() {
        return roliStafitID;
    }

    public void setRoliStafitID(Rolistafit roliStafitID) {
        this.roliStafitID = roliStafitID;
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
        if (!(object instanceof Stafi)) {
            return false;
        }
        Stafi other = (Stafi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.Stafi[ id=" + id + " ]";
    }
    
}
