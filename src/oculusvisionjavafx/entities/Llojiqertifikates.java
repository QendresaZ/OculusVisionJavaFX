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
@Table(name = "llojiqertifikates")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Llojiqertifikates.findAll", query = "SELECT l FROM Llojiqertifikates l")
    , @NamedQuery(name = "Llojiqertifikates.findById", query = "SELECT l FROM Llojiqertifikates l WHERE l.id = :id")
    , @NamedQuery(name = "Llojiqertifikates.findByLloji", query = "SELECT l FROM Llojiqertifikates l WHERE l.lloji = :lloji")
    , @NamedQuery(name = "Llojiqertifikates.findByPershkrimiLlojit", query = "SELECT l FROM Llojiqertifikates l WHERE l.pershkrimiLlojit = :pershkrimiLlojit")})
public class Llojiqertifikates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Lloji")
    private String lloji;
    @Column(name = "PershkrimiLlojit")
    private Integer pershkrimiLlojit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "llojiQertifikatesID")
    private Collection<Qertifikata> qertifikataCollection;

    public Llojiqertifikates() {
    }

    public Llojiqertifikates(Integer id) {
        this.id = id;
    }

    public Llojiqertifikates(Integer id, String lloji) {
        this.id = id;
        this.lloji = lloji;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLloji() {
        return lloji;
    }

    public void setLloji(String lloji) {
        this.lloji = lloji;
    }

    public Integer getPershkrimiLlojit() {
        return pershkrimiLlojit;
    }

    public void setPershkrimiLlojit(Integer pershkrimiLlojit) {
        this.pershkrimiLlojit = pershkrimiLlojit;
    }

    @XmlTransient
    public Collection<Qertifikata> getQertifikataCollection() {
        return qertifikataCollection;
    }

    public void setQertifikataCollection(Collection<Qertifikata> qertifikataCollection) {
        this.qertifikataCollection = qertifikataCollection;
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
        if (!(object instanceof Llojiqertifikates)) {
            return false;
        }
        Llojiqertifikates other = (Llojiqertifikates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.Llojiqertifikates[ id=" + id + " ]";
    }
    
}
