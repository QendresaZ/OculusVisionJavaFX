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
@Table(name = "paisja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paisja.findAll", query = "SELECT p FROM Paisja p")
    , @NamedQuery(name = "Paisja.findById", query = "SELECT p FROM Paisja p WHERE p.id = :id")
    , @NamedQuery(name = "Paisja.findByEmri", query = "SELECT p FROM Paisja p WHERE p.emri = :emri")
    , @NamedQuery(name = "Paisja.findByBrendi", query = "SELECT p FROM Paisja p WHERE p.brendi = :brendi")
    , @NamedQuery(name = "Paisja.findBySasia", query = "SELECT p FROM Paisja p WHERE p.sasia = :sasia")})
public class Paisja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Emri")
    private String emri;
    @Column(name = "Brendi")
    private String brendi;
    @Column(name = "Sasia")
    private Integer sasia;
    @JoinColumn(name = "PajisjeNeDhomaID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PajisjeNeDhoma pajisjeNeDhomaID;

    public Paisja() {
    }

    public Paisja(Integer id) {
        this.id = id;
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

    public String getBrendi() {
        return brendi;
    }

    public void setBrendi(String brendi) {
        this.brendi = brendi;
    }

    public Integer getSasia() {
        return sasia;
    }

    public void setSasia(Integer sasia) {
        this.sasia = sasia;
    }

    public PajisjeNeDhoma getPajisjeNeDhomaID() {
        return pajisjeNeDhomaID;
    }

    public void setPajisjeNeDhomaID(PajisjeNeDhoma pajisjeNeDhomaID) {
        this.pajisjeNeDhomaID = pajisjeNeDhomaID;
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
        if (!(object instanceof Paisja)) {
            return false;
        }
        Paisja other = (Paisja) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.Paisja[ id=" + id + " ]";
    }
    
}
