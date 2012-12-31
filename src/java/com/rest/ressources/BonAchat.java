/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.ressources;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Anas
 */
@Entity
@Table(name = "bon_achat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BonAchat.findAll", query = "SELECT b FROM BonAchat b"),
    @NamedQuery(name = "BonAchat.findByIdbonAchat", query = "SELECT b FROM BonAchat b WHERE b.idbonAchat = :idbonAchat"),
    @NamedQuery(name = "BonAchat.findByDateExp", query = "SELECT b FROM BonAchat b WHERE b.dateExp = :dateExp"),
    @NamedQuery(name = "BonAchat.findByValeur", query = "SELECT b FROM BonAchat b WHERE b.valeur = :valeur")})
public class BonAchat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbon_achat")
    private Integer idbonAchat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "date_exp")
    private String dateExp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valeur")
    private int valeur;
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idutilisateur;
    @OneToMany(mappedBy = "idbonAchat")
    private Collection<Commande> commandeCollection;

    public BonAchat() {
    }

    public BonAchat(Integer idbonAchat) {
        this.idbonAchat = idbonAchat;
    }

    public BonAchat(Integer idbonAchat, String dateExp, int valeur) {
        this.idbonAchat = idbonAchat;
        this.dateExp = dateExp;
        this.valeur = valeur;
    }

    public Integer getIdbonAchat() {
        return idbonAchat;
    }

    public void setIdbonAchat(Integer idbonAchat) {
        this.idbonAchat = idbonAchat;
    }

    public String getDateExp() {
        return dateExp;
    }

    public void setDateExp(String dateExp) {
        this.dateExp = dateExp;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public Utilisateur getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Utilisateur idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    @XmlTransient
    public Collection<Commande> getCommandeCollection() {
        return commandeCollection;
    }

    public void setCommandeCollection(Collection<Commande> commandeCollection) {
        this.commandeCollection = commandeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbonAchat != null ? idbonAchat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BonAchat)) {
            return false;
        }
        BonAchat other = (BonAchat) object;
        if ((this.idbonAchat == null && other.idbonAchat != null) || (this.idbonAchat != null && !this.idbonAchat.equals(other.idbonAchat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rest.ressources.BonAchat[ idbonAchat=" + idbonAchat + " ]";
    }
    
}
