/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.rest.ressources.Utilisateur;
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
import javax.ws.rs.QueryParam;

/**
 *
 * @author Anas
 */
@Stateless
@Path("utilisateurs")
public class UtilisateurFacadeREST extends AbstractFacade<Utilisateur> {
    @PersistenceContext(unitName = "G-BUY-RESTPU")
    private EntityManager em;

    public UtilisateurFacadeREST() {
        super(Utilisateur.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Utilisateur entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Utilisateur entity) {
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
    public Utilisateur find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Utilisateur> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Utilisateur> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    // Méthodes personalisée 
     
    
    /**
     * 
     * @param email
     * @param password
     * @return Utilisateur
     */
    
    @GET
    @Path("verifieer")
    @Produces({"application/xml", "application/json"})
    public Utilisateur findByEmailPassword(@QueryParam("email") String email,
                                           @QueryParam("password") String password)
    {
        Query q = em.createNamedQuery("Utilisateur.findByEmailPassword");
        q.setParameter("email", email);
        q.setParameter("password", password);
        List<Utilisateur> list =  q.getResultList();
        if(list != null && !list.isEmpty())
        {
          return list.get(0);
         }
        return null;
    }
    
    /**
     * Find By Id Parrain
     * @param idParrain
     * @return  List<Utilisateur>
     */
    @GET
    @Path("parrainer/{id}")
    @Produces({"application/xml", "application/json"})
    public List<Utilisateur> findByParrainId(@PathParam("id") Integer id){
        Query q = em.createNamedQuery("Utilisateur.findByParain");
        q.setParameter("parrainId", id);
        List<Utilisateur> list = q.getResultList();
        
        if(!list.isEmpty())
        {
            return list;
        }
        return null;
    }
        


}
