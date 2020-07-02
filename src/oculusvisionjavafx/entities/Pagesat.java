/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "pagesat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagesat.findAll", query = "SELECT p FROM Pagesat p")
    , @NamedQuery(name = "Pagesat.findById", query = "SELECT p FROM Pagesat p WHERE p.id = :id")
    , @NamedQuery(name = "Pagesat.findByDataPageses", query = "SELECT p FROM Pagesat p WHERE p.dataPageses = :dataPageses")
    , @NamedQuery(name = "Pagesat.findByShuma", query = "SELECT p FROM Pagesat p WHERE p.shuma = :shuma")
    , @NamedQuery(name = "Pagesat.findByDataFundit", query = "SELECT p FROM Pagesat p WHERE p.dataFundit = :dataFundit")
    , @NamedQuery(name = "Pagesat.findByArsyeja", query = "SELECT p FROM Pagesat p WHERE p.arsyeja = :arsyeja")
    , @NamedQuery(name = "Pagesat.findByStatusi", query = "SELECT p FROM Pagesat p WHERE p.statusi = :statusi")})
public class Pagesat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DataPageses")
    @Temporal(TemporalType.DATE)
    private Date dataPageses;
    @Basic(optional = false)
    @Column(name = "Shuma")
    private BigInteger shuma;
    @Column(name = "DataFundit")
    @Temporal(TemporalType.DATE)
    private Date dataFundit;
    @Column(name = "Arsyeja")
    private String arsyeja;
    @Column(name = "Statusi")
    private Boolean statusi;
    @JoinColumn(name = "PacientiID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Pacienti pacientiID;

    public Pagesat() {
    }

    public Pagesat(Integer id) {
        this.id = id;
    }

    public Pagesat(Integer id, BigInteger shuma) {
        this.id = id;
        this.shuma = shuma;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataPageses() {
        return dataPageses;
    }

    public void setDataPageses(Date dataPageses) {
        this.dataPageses = dataPageses;
    }

    public BigInteger getShuma() {
        return shuma;
    }

    public void setShuma(BigInteger shuma) {
        this.shuma = shuma;
    }

    public Date getDataFundit() {
        return dataFundit;
    }

    public void setDataFundit(Date dataFundit) {
        this.dataFundit = dataFundit;
    }

    public String getArsyeja() {
        return arsyeja;
    }

    public void setArsyeja(String arsyeja) {
        this.arsyeja = arsyeja;
    }

    public Boolean getStatusi() {
        return statusi;
    }

    public void setStatusi(Boolean statusi) {
        this.statusi = statusi;
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
        if (!(object instanceof Pagesat)) {
            return false;
        }
        Pagesat other = (Pagesat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.Pagesat[ id=" + id + " ]";
    }
    
}
