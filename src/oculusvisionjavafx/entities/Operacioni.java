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
@Table(name = "operacioni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operacioni.findAll", query = "SELECT o FROM Operacioni o")
    , @NamedQuery(name = "Operacioni.findByPershkrimi", query = "SELECT o FROM Operacioni o WHERE o.pershkrimi = :pershkrimi")
    , @NamedQuery(name = "Operacioni.findById", query = "SELECT o FROM Operacioni o WHERE o.id = :id")
    , @NamedQuery(name = "Operacioni.findByLloji", query = "SELECT o FROM Operacioni o WHERE o.lloji = :lloji")})
public class Operacioni implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "Pershkrimi")
    private String pershkrimi;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Lloji")
    private String lloji;
    @JoinColumn(name = "TerminiID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Termini terminiID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operacioniId")
    private Collection<Dhoma> dhomaCollection;

    public Operacioni() {
    }

    public Operacioni(Integer id) {
        this.id = id;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
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

    public Termini getTerminiID() {
        return terminiID;
    }

    public void setTerminiID(Termini terminiID) {
        this.terminiID = terminiID;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operacioni)) {
            return false;
        }
        Operacioni other = (Operacioni) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.Operacioni[ id=" + id + " ]";
    }
    
}
