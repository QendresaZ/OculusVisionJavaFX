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
@Table(name = "alergjite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alergjite.findAll", query = "SELECT a FROM Alergjite a")
    , @NamedQuery(name = "Alergjite.findById", query = "SELECT a FROM Alergjite a WHERE a.id = :id")
    , @NamedQuery(name = "Alergjite.findByRrezikshmeria", query = "SELECT a FROM Alergjite a WHERE a.rrezikshmeria = :rrezikshmeria")
    , @NamedQuery(name = "Alergjite.findByDataNodhjes", query = "SELECT a FROM Alergjite a WHERE a.dataNodhjes = :dataNodhjes")
    , @NamedQuery(name = "Alergjite.findByVerejtuarNga", query = "SELECT a FROM Alergjite a WHERE a.verejtuarNga = :verejtuarNga")
    , @NamedQuery(name = "Alergjite.findByPershkrimiShtes", query = "SELECT a FROM Alergjite a WHERE a.pershkrimiShtes = :pershkrimiShtes")})
public class Alergjite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Rrezikshmeria")
    private int rrezikshmeria;
    @Basic(optional = false)
    @Column(name = "DataNodhjes")
    @Temporal(TemporalType.DATE)
    private Date dataNodhjes;
    @Basic(optional = false)
    @Column(name = "VerejtuarNga")
    private String verejtuarNga;
    @Column(name = "PershkrimiShtes")
    private String pershkrimiShtes;
    @JoinColumn(name = "PacientiID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Pacienti pacientiID;
    @JoinColumn(name = "IlacID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Ilac ilacID;

    public Alergjite() {
    }

    public Alergjite(Integer id) {
        this.id = id;
    }

    public Alergjite(Integer id, int rrezikshmeria, Date dataNodhjes, String verejtuarNga) {
        this.id = id;
        this.rrezikshmeria = rrezikshmeria;
        this.dataNodhjes = dataNodhjes;
        this.verejtuarNga = verejtuarNga;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRrezikshmeria() {
        return rrezikshmeria;
    }

    public void setRrezikshmeria(int rrezikshmeria) {
        this.rrezikshmeria = rrezikshmeria;
    }

    public Date getDataNodhjes() {
        return dataNodhjes;
    }

    public void setDataNodhjes(Date dataNodhjes) {
        this.dataNodhjes = dataNodhjes;
    }

    public String getVerejtuarNga() {
        return verejtuarNga;
    }

    public void setVerejtuarNga(String verejtuarNga) {
        this.verejtuarNga = verejtuarNga;
    }

    public String getPershkrimiShtes() {
        return pershkrimiShtes;
    }

    public void setPershkrimiShtes(String pershkrimiShtes) {
        this.pershkrimiShtes = pershkrimiShtes;
    }

    public Pacienti getPacientiID() {
        return pacientiID;
    }

    public void setPacientiID(Pacienti pacientiID) {
        this.pacientiID = pacientiID;
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
        if (!(object instanceof Alergjite)) {
            return false;
        }
        Alergjite other = (Alergjite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oculusvisionjavafx.entities.Alergjite[ id=" + id + " ]";
    }
    
}
