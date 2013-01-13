/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.rest.ressources.Commande;
import com.rest.ressources.Deal;
import com.rest.ressources.Utilisateur;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Anas
 */
@Stateless
@Path("deals")
public class DealFacadeREST extends AbstractFacade<Deal> {
    
    @PersistenceContext(unitName = "G-BUY-RESTPU")
    private EntityManager em;

    public DealFacadeREST() {
        super(Deal.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Deal entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Deal entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Deal find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Deal> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Deal> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    // methodes personnalisées
    /**
     * findByCategorie
     *
     * @param id
     * @return List<Deal>
     */
    @GET
    @Path("categorie/{id}")
    @Produces({"application/xml", "application/json"})
    public List<Deal> findByCategorie(@PathParam("id") Integer id) {
        Query q = em.createNamedQuery("Deal.findByIdCategorie");
        q.setParameter("idcategorie", id);

        List<Deal> list = q.getResultList();
        if (!list.isEmpty() && list != null) {
            return list;
        }

        return null;
    }

    /**
     * Find By Tags
     *
     * @param tag
     * @return List<Deal>
     */
    @GET
    @Path("search/{tags}")
    @Produces({"application/xml", "application/json"})
    public List<Deal> search(@PathParam("tags") String tags) {

        Query q = em.createNamedQuery("Deal.findByTags");
        q.setParameter("tags", "%" + tags + "%");
        List<Deal> list = q.getResultList();

        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    /**
     * Find By IdPrestataire
     *
     * @param id
     * @return List<Deal>
     */
    @GET
    @Path("prestataire/{id}")
    @Produces({"application/xml", "application/json"})
    public List<Deal> findByIdPrestataire(@PathParam("id") Integer id) {

        Query q = em.createNamedQuery("Deal.findByIdPrestataire");
        q.setParameter("prestataire", id);
        List<Deal> list = q.getResultList();

        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    /**
     * findByVille
     *
     * @param ville
     * @return List<Deal>
     */
    @GET
    @Path("ville/{ville}")
    @Produces({"application/xml", "application/json"})
    public List<Deal> findByVille(@PathParam("ville") String ville) {
        Query q = em.createNamedQuery("Deal.findByVille");
        q.setParameter("ville", ville);

        List<Deal> list = q.getResultList();
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    /**
     * findByPays
     *
     * @param pays
     * @return List<Pays>
     */
    @GET
    @Path("pays/{pays}")
    @Produces({"application/xml", "application/json"})
    public List<Deal> findByPays(@PathParam("pays") String pays) {
        Query q = em.createNamedQuery("Deal.findByPays");
        q.setParameter("pays", pays);

        List<Deal> list = q.getResultList();
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    /**
     * findByPrix
     *
     * @param prix
     * @return
     */
    @GET
    @Path("prix/{prix}")
    @Produces({"application/xml", "application/json"})
    public List<Deal> findByPrix(@PathParam("prix") double prix) {
        Query q = em.createNamedQuery("Deal.findByPrix");
        q.setParameter("prix", prix);

        List<Deal> list = q.getResultList();
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    /**
     * countRatingByIddeal
     *
     * @param id
     * @return
     */
    @GET
    @Path("countRating/{iddeal}")
    @Produces("text/plain")
    public String countRatingByIdDeal(@PathParam("iddeal") Integer id) {
        Query q = em.createNamedQuery("Deal.findRatingByDealId");
        q.setParameter("iddeal", id);
        List<Deal> list = q.getResultList();
        return String.valueOf(list.size());
    }
    
     /**
     * finduserCollection
     *
     * @param id
     * @return
     */
    @GET
    @Path("users/{iddeal}")
    @Produces({"application/xml", "application/json"})
    public List<Utilisateur> findUserCollectionByIdDeal(@PathParam("iddeal") Integer id) {
        Query q = em.createNamedQuery("Deal.findRatingByDealId");
        q.setParameter("iddeal", id);
        List<Utilisateur> list = q.getResultList();
        return list;
    }
    
      /**
     * find Commande Collection
     *
     * @param id
     * @return
     */
    @GET
    @Path("commandes/{iddeal}")
    @Produces({"application/xml", "application/json"})
    public List<Commande> findCommandeCollectionByIdDeal(@PathParam("iddeal") Integer id) {
        Query q = em.createNamedQuery("Deal.findCommandeCollection");
        q.setParameter("iddeal", id);
        List<Commande> list = q.getResultList();
        return list;
    }

    /**
     * findByDateAjout
     *
     * @param date
     * @return List<Deal>
     */
    @GET
    @Path("dateAjout/{date}")
    @Produces({"application/xml", "application/json"})
    public List<Deal> findByDateAjout(@PathParam("date") String date) {

        //java.sql.Date d = java.sql.Date.valueOf(date);
        Query q = em.createNamedQuery("Deal.findByDateAjout");
        q.setParameter("dateAjout", date);
        List<Deal> list = q.getResultList();

        if (!list.isEmpty() && list != null) {
            return list;
        }

        return null;
    }

    /**
     * findByDateExp
     *
     * @param date
     * @return List<Deal>
     */
    @GET
    @Path("expiration")
    @Produces({"application/xml", "application/json"})
    public List<Deal> findDealExpire() {

        Query q = em.createNamedQuery("Deal.findAll");
        List<Deal> list = q.getResultList();
        Calendar c = Calendar.getInstance();
        Date aujourdhui = new Date();
        c.setTime(aujourdhui);
        c.add(Calendar.DATE, 2);
        Date lendemain = c.getTime();
        if (!list.isEmpty() && list != null) {
            List<Deal> exp = new ArrayList<Deal>();
            for (Deal deal : list) {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
                Date dateExp = null;
                try {
                    dateExp = dateFormat.parse(deal.getDateExp());
                } catch (ParseException ex) {
                    Logger.getLogger(DealFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (dateExp.compareTo(aujourdhui) > 0 && dateExp.compareTo(lendemain) < 0 ) {
                    exp.add(deal);
                }

            }
            return exp;

        }


        return null;
    }
    
}
