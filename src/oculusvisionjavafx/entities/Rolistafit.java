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
@Table(name = "rolistafit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolistafit.findAll", query = "SELECT r FROM Rolistafit r")
    , @NamedQuery(name = "Rolistafit.findById", query = "SELECT r FROM Rolistafit r WHERE r.id = :id")
    , @NamedQuery(name = "Rolistafit.findByEmriRolit", query = "SELECT r FROM Rolistafit r WHERE r.emriRolit = :emriRolit")
    , @NamedQuery(name = "Rolistafit.findByPershkrimiRolit", query = "SELECT r FROM Rolistafit r WHERE r.pershkrimiRolit = :pershkrimiRolit")})
public class Rolistafit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "EmriRolit")
    private String emriRolit;
    @Basic(optional = false)
    @Column(name = "PershkrimiRolit")
    private String pershkrimiRolit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roliStafitID")
    private Collection<Stafi> stafiCollection;

    public Rolistafit() {
    }

    public Rolistafit(Integer id) {
        this.id = id;
    }

    public Rolistafit(Integer id, String emriRolit, String pershkrimiRolit) {
        this.id = id;
        this.emriRolit = emriRolit;
        this.pershkrimiRolit = pershkrimiRolit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmriRolit() {
        return emriRolit;
    }

    public void setEmriRolit(String emriRolit) {
        this.emriRolit = emriRolit;
    }

    public String getPershkrimiRolit() {
        return pershkrimiRolit;
    }

    public void setPershkrimiRolit(String pershkrimiRolit) {
        this.pershkrimiRolit = pershkrimiRolit;
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
        if (!(object instanceof Rolistafit)) {
            return false;
        }
        Rolistafit other = (Rolistafit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.Rolistafit[ id=" + id + " ]";
    }
    
}
