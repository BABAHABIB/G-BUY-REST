/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.rest.ressources.Deal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.print.attribute.standard.DateTimeAtCompleted;
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
     * @param id
     * @return List<Deal>
     */
    @GET
    @Path("categorie/{id}")
    @Produces({"application/xml", "application/json"})
    public List<Deal> findByCategorie(@PathParam("id") Integer id )
    {
        Query q=em.createNamedQuery("Deal.findByIdCategorie");
        q.setParameter("idcategorie", id);
        
       List<Deal> list = q.getResultList();
       if(!list.isEmpty() && list != null)
           return list;
    
    return null;
    }
    
     /**
     * Find By Tags
     * @param tag
     * @return  List<Deal>
     */
    
    @GET
    @Path("tags/{tags}")
    @Produces({"application/xml", "application/json"})
    public List<Deal> findByTags(@PathParam("tags") String tags){
        
        Query q = em.createNamedQuery("Deal.findByTags");
        q.setParameter("tags", tags);
        List<Deal> list = q.getResultList();
        
        if(!list.isEmpty())
        {
            return list;
        }
        return null;
    }
        
          /**
     * Find By IdPrestataire
     * @param id
     * @return  List<Deal>
     */
    
    @GET
    @Path("prestataire/{id}")
    @Produces({"application/xml", "application/json"})
    public List<Deal> findByIdPrestataire(@PathParam("id") Integer id){
        
        Query q = em.createNamedQuery("Deal.findByIdPrestataire");
        q.setParameter("prestataire", id);
        List<Deal> list = q.getResultList();
        
        if(!list.isEmpty())
        {
            return list;
        }
        return null;
    }   
        
}
