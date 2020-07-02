/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Qendresa
 */
@Entity
@Table(name = "pacienti")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pacienti.findAll", query = "SELECT p FROM Pacienti p")
    , @NamedQuery(name = "Pacienti.findById", query = "SELECT p FROM Pacienti p WHERE p.id = :id")
    , @NamedQuery(name = "Pacienti.findByEmri", query = "SELECT p FROM Pacienti p WHERE p.emri = :emri")
    , @NamedQuery(name = "Pacienti.findByMbiemri", query = "SELECT p FROM Pacienti p WHERE p.mbiemri = :mbiemri")
    , @NamedQuery(name = "Pacienti.findByGjinia", query = "SELECT p FROM Pacienti p WHERE p.gjinia = :gjinia")
    , @NamedQuery(name = "Pacienti.findByDataLindjes", query = "SELECT p FROM Pacienti p WHERE p.dataLindjes = :dataLindjes")
    , @NamedQuery(name = "Pacienti.findByAdresa", query = "SELECT p FROM Pacienti p WHERE p.adresa = :adresa")
    , @NamedQuery(name = "Pacienti.findByPershkrim", query = "SELECT p FROM Pacienti p WHERE p.pershkrim = :pershkrim")
    , @NamedQuery(name = "Pacienti.findByEmail", query = "SELECT p FROM Pacienti p WHERE p.email = :email")
    , @NamedQuery(name = "Pacienti.findByNumriTelefonit", query = "SELECT p FROM Pacienti p WHERE p.numriTelefonit = :numriTelefonit")
    , @NamedQuery(name = "Pacienti.findByPassword", query = "SELECT p FROM Pacienti p WHERE p.password = :password")
    , @NamedQuery(name = "Pacienti.findByStatusi", query = "SELECT p FROM Pacienti p WHERE p.statusi = :statusi")})
public class Pacienti implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacientiID")
    private Collection<Qertifikata> qertifikataCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Emri")
    private String emri;
    @Basic(optional = false)
    @Column(name = "Mbiemri")
    private String mbiemri;
    @Basic(optional = false)
    @Column(name = "Gjinia")
    private Character gjinia;
    @Basic(optional = false)
    @Column(name = "DataLindjes")
    @Temporal(TemporalType.DATE)
    private Date dataLindjes;
    @Basic(optional = false)
    @Column(name = "Adresa")
    private String adresa;
    @Column(name = "Pershkrim")
    private String pershkrim;
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @Column(name = "NumriTelefonit")
    private int numriTelefonit;
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;
    @Column(name = "Statusi")
    private String statusi;

    public Pacienti() {
    }

    public Pacienti(Integer id) {
        this.id = id;
    }

    public Pacienti(Integer id, String emri, String mbiemri, Character gjinia, Date dataLindjes, String adresa, String email, int numriTelefonit, String password) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.gjinia = gjinia;
        this.dataLindjes = dataLindjes;
        this.adresa = adresa;
        this.email = email;
        this.numriTelefonit = numriTelefonit;
        this.password = password;
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

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public Character getGjinia() {
        return gjinia;
    }

    public void setGjinia(Character gjinia) {
        this.gjinia = gjinia;
    }

    public Date getDataLindjes() {
        return dataLindjes;
    }

    public void setDataLindjes(Date dataLindjes) {
        this.dataLindjes = dataLindjes;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getPershkrim() {
        return pershkrim;
    }

    public void setPershkrim(String pershkrim) {
        this.pershkrim = pershkrim;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumriTelefonit() {
        return numriTelefonit;
    }

    public void setNumriTelefonit(int numriTelefonit) {
        this.numriTelefonit = numriTelefonit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
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
        if (!(object instanceof Pacienti)) {
            return false;
        }
        Pacienti other = (Pacienti) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return emri + " " + mbiemri + " [ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Qertifikata> getQertifikataCollection() {
        return qertifikataCollection;
    }

    public void setQertifikataCollection(Collection<Qertifikata> qertifikataCollection) {
        this.qertifikataCollection = qertifikataCollection;
    }
    
}
