/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Qendresa
 */
@Entity
@Table(name = "kontrolla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kontrolla.findAll", query = "SELECT k FROM Kontrolla k")
    , @NamedQuery(name = "Kontrolla.findByPershkrimi", query = "SELECT k FROM Kontrolla k WHERE k.pershkrimi = :pershkrimi")
    , @NamedQuery(name = "Kontrolla.findById", query = "SELECT k FROM Kontrolla k WHERE k.id = :id")
    , @NamedQuery(name = "Kontrolla.findByLloji", query = "SELECT k FROM Kontrolla k WHERE k.lloji = :lloji")})
public class Kontrolla implements Serializable {

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

    public Kontrolla() {
    }

    public Kontrolla(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kontrolla)) {
            return false;
        }
        Kontrolla other = (Kontrolla) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.Kontrolla[ id=" + id + " ]";
    }
    
}
