/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.rest.ressources.Commande;
import java.util.List;
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
 * @author Anas-Yassine
 */
@Stateless
@Path("commandes")
public class CommandeFacadeREST extends AbstractFacade<Commande> {

    @PersistenceContext(unitName = "G-BUY-RESTPU")
    private EntityManager em;

    public CommandeFacadeREST() {
        super(Commande.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Commande entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Commande entity) {
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
    public Commande find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Commande> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Commande> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    /**
     * Find By User Id
     *
     * @param idutilisateur
     * @return List<Commande>
     */
    @GET
    @Path("utilisateur/{idutilisateur}")
    @Produces({"application/xml", "application/json"})
    public List<Commande> findByUserid(@PathParam("idutilisateur") Integer idutilisateur) {
        Query q = em.createNamedQuery("Commande.findByUserid");
        q.setParameter("idutilisateur", idutilisateur);
        List<Commande> list = q.getResultList();

        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    /**
     * Find By Deal Id
     *
     * @param iddeal
     * @return List<Commande>
     */
    @GET
    @Path("deal/{iddeal}")
    @Produces({"application/xml", "application/json"})
    public List<Commande> findByDealid(@PathParam("iddeal") Integer id) {
        Query q = em.createNamedQuery("Commande.findByDealid");
        q.setParameter("iddeal", id);
        List<Commande> list = q.getResultList();

        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    /**
     * Find By Deal Id
     *
     * @param iddeal
     * @return List<Commande>
     */
    @GET
    @Path("count/{iddeal}")
    @Produces("text/plain")
    public String countByDealid(@PathParam("iddeal") Integer id) {
        Query q = em.createNamedQuery("Commande.findByDealid");
        q.setParameter("iddeal", id);
        List<Commande> list = q.getResultList();

        return String.valueOf(list.size());

    }
  
}
