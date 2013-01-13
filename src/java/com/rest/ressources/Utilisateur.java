/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.ressources;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "utilisateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
    @NamedQuery(name = "Utilisateur.findByIdutilisateur", query = "SELECT u FROM Utilisateur u WHERE u.idutilisateur = :idutilisateur"),
    @NamedQuery(name = "Utilisateur.findByEmail", query = "SELECT u FROM Utilisateur u WHERE u.email = :email"),
    @NamedQuery(name = "Utilisateur.findByPassword", query = "SELECT u FROM Utilisateur u WHERE u.password = :password"),
    @NamedQuery(name = "Utilisateur.findByNom", query = "SELECT u FROM Utilisateur u WHERE u.nom = :nom"),
    @NamedQuery(name = "Utilisateur.findByPrenom", query = "SELECT u FROM Utilisateur u WHERE u.prenom = :prenom"),
    @NamedQuery(name = "Utilisateur.findByAdresse", query = "SELECT u FROM Utilisateur u WHERE u.adresse = :adresse"),
    @NamedQuery(name = "Utilisateur.findByVille", query = "SELECT u FROM Utilisateur u WHERE u.ville = :ville"),
    @NamedQuery(name = "Utilisateur.findByPays", query = "SELECT u FROM Utilisateur u WHERE u.pays = :pays"),
    @NamedQuery(name = "Utilisateur.findByCodePostale", query = "SELECT u FROM Utilisateur u WHERE u.codePostale = :codePostale"),
    @NamedQuery(name = "Utilisateur.findByDateNaissance", query = "SELECT u FROM Utilisateur u WHERE u.dateNaissance = :dateNaissance"),
    @NamedQuery(name = "Utilisateur.findByType", query = "SELECT u FROM Utilisateur u WHERE u.type = :type"),
    @NamedQuery(name = "Utilisateur.findByEmailPassword", query = "SELECT u FROM Utilisateur u WHERE u.email = :email AND u.password = :password"),
    @NamedQuery(name = "Utilisateur.findByParain", query = "SELECT u FROM Utilisateur u WHERE u.parrain.idutilisateur = :parrainId"),
    @NamedQuery(name = "Utilisateur.findRatedDeal", query = "SELECT d FROM Utilisateur u JOIN u.dealCollection d WHERE u.idutilisateur = :idutilisateur")
    
})
public class Utilisateur implements Serializable {
    @Size(max = 45)
    @Column(name = "date_naissance")
    private String dateNaissance;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idutilisateur")
    private Integer idutilisateur;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ville")
    private String ville;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pays")
    private String pays;
    @Size(max = 45)
    @Column(name = "code_postale")
    private String codePostale;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @JoinTable(name = "rate", joinColumns = {
        @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")}, inverseJoinColumns = {
        @JoinColumn(name = "iddeal", referencedColumnName = "iddeal")})
    @ManyToMany
    private Collection<Deal> dealCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idutilisateur")
    private Collection<BonAchat> bonAchatCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idutilisateur")
    private Collection<Commande> commandeCollection;
    @OneToMany(mappedBy = "parrain")
    private Collection<Utilisateur> utilisateurCollection;
    @JoinColumn(name = "parrain", referencedColumnName = "idutilisateur")
    @ManyToOne
    private Utilisateur parrain;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idutilisateur")
    private Collection<Commentaire> commentaireCollection;

    public Utilisateur() {
    }

    public Utilisateur(Integer idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public Utilisateur(Integer idutilisateur, String email, String password, String nom, String prenom, String adresse, String ville, String pays) {
        this.idutilisateur = idutilisateur;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.ville = ville;
        this.pays = pays;
    }

    public Integer getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Integer idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<Deal> getDealCollection() {
        return dealCollection;
    }

    public void setDealCollection(Collection<Deal> dealCollection) {
        this.dealCollection = dealCollection;
    }

    @XmlTransient
    public Collection<BonAchat> getBonAchatCollection() {
        return bonAchatCollection;
    }

    public void setBonAchatCollection(Collection<BonAchat> bonAchatCollection) {
        this.bonAchatCollection = bonAchatCollection;
    }

    @XmlTransient
    public Collection<Commande> getCommandeCollection() {
        return commandeCollection;
    }

    public void setCommandeCollection(Collection<Commande> commandeCollection) {
        this.commandeCollection = commandeCollection;
    }

    @XmlTransient
    public Collection<Utilisateur> getUtilisateurCollection() {
        return utilisateurCollection;
    }

    public void setUtilisateurCollection(Collection<Utilisateur> utilisateurCollection) {
        this.utilisateurCollection = utilisateurCollection;
    }

    public Utilisateur getParrain() {
        return parrain;
    }

    public void setParrain(Utilisateur parrain) {
        this.parrain = parrain;
    }

    @XmlTransient
    public Collection<Commentaire> getCommentaireCollection() {
        return commentaireCollection;
    }

    public void setCommentaireCollection(Collection<Commentaire> commentaireCollection) {
        this.commentaireCollection = commentaireCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idutilisateur != null ? idutilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.idutilisateur == null && other.idutilisateur != null) || (this.idutilisateur != null && !this.idutilisateur.equals(other.idutilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rest.ressources.Utilisateur[ idutilisateur=" + idutilisateur + " ]";
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
}
