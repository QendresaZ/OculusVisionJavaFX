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
@Table(name = "perberesitilacit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perberesitilacit.findAll", query = "SELECT p FROM Perberesitilacit p")
    , @NamedQuery(name = "Perberesitilacit.findById", query = "SELECT p FROM Perberesitilacit p WHERE p.id = :id")
    , @NamedQuery(name = "Perberesitilacit.findByPerberesit", query = "SELECT p FROM Perberesitilacit p WHERE p.perberesit = :perberesit")})
public class Perberesitilacit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "perberesit")
    private String perberesit;
    @JoinColumn(name = "IlacID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Ilac ilacID;

    public Perberesitilacit() {
    }

    public Perberesitilacit(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerberesit() {
        return perberesit;
    }

    public void setPerberesit(String perberesit) {
        this.perberesit = perberesit;
    }

    public Ilac getIlacID() {
        return ilacID;
    }

    public void setIlacID(Ilac ilacID) {
        this.ilacID = ilacID;
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
        if (!(object instanceof Perberesitilacit)) {
            return false;
        }
        Perberesitilacit other = (Perberesitilacit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.Perberesitilacit[ id=" + id + " ]";
    }
    
}
