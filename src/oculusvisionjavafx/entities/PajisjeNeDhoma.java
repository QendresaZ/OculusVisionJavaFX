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
@Table(name = "pajisje ne dhoma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PajisjeNeDhoma.findAll", query = "SELECT p FROM PajisjeNeDhoma p")
    , @NamedQuery(name = "PajisjeNeDhoma.findById", query = "SELECT p FROM PajisjeNeDhoma p WHERE p.id = :id")})
public class PajisjeNeDhoma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "DhomaNumri", referencedColumnName = "Numri")
    @ManyToOne(optional = false)
    private Dhoma dhomaNumri;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pajisjeNeDhomaID")
    private Collection<Paisja> paisjaCollection;

    public PajisjeNeDhoma() {
    }

    public PajisjeNeDhoma(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dhoma getDhomaNumri() {
        return dhomaNumri;
    }

    public void setDhomaNumri(Dhoma dhomaNumri) {
        this.dhomaNumri = dhomaNumri;
    }

    @XmlTransient
    public Collection<Paisja> getPaisjaCollection() {
        return paisjaCollection;
    }

    public void setPaisjaCollection(Collection<Paisja> paisjaCollection) {
        this.paisjaCollection = paisjaCollection;
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
        if (!(object instanceof PajisjeNeDhoma)) {
            return false;
        }
        PajisjeNeDhoma other = (PajisjeNeDhoma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.PajisjeNeDhoma[ id=" + id + " ]";
    }
    
}
