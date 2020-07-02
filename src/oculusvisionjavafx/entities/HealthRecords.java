/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Qendresa
 */
@Entity
@Table(name = "health records")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HealthRecords.findAll", query = "SELECT h FROM HealthRecords h")
    , @NamedQuery(name = "HealthRecords.findById", query = "SELECT h FROM HealthRecords h WHERE h.id = :id")
    , @NamedQuery(name = "HealthRecords.findByDataRek", query = "SELECT h FROM HealthRecords h WHERE h.dataRek = :dataRek")
    , @NamedQuery(name = "HealthRecords.findByPershkrimi", query = "SELECT h FROM HealthRecords h WHERE h.pershkrimi = :pershkrimi")})
public class HealthRecords implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DataRek")
    @Temporal(TemporalType.DATE)
    private Date dataRek;
    @Basic(optional = false)
    @Column(name = "Pershkrimi")
    private String pershkrimi;
    @JoinColumn(name = "StafiID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Stafi stafiID;
    @JoinColumn(name = "PacientiID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Pacienti pacientiID;

    public HealthRecords() {
    }

    public HealthRecords(Integer id) {
        this.id = id;
    }

    public HealthRecords(Integer id, Date dataRek, String pershkrimi) {
        this.id = id;
        this.dataRek = dataRek;
        this.pershkrimi = pershkrimi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataRek() {
        return dataRek;
    }

    public void setDataRek(Date dataRek) {
        this.dataRek = dataRek;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public Stafi getStafiID() {
        return stafiID;
    }

    public void setStafiID(Stafi stafiID) {
        this.stafiID = stafiID;
    }

    public Pacienti getPacientiID() {
        return pacientiID;
    }

    public void setPacientiID(Pacienti pacientiID) {
        this.pacientiID = pacientiID;
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
        if (!(object instanceof HealthRecords)) {
            return false;
        }
        HealthRecords other = (HealthRecords) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.HealthRecords[ id=" + id + " ]";
    }
    
}
