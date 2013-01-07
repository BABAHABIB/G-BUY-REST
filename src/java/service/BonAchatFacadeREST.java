/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.rest.ressources.BonAchat;
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
 * @author Anas
 */
@Stateless
@Path("bonachats")
public class BonAchatFacadeREST extends AbstractFacade<BonAchat> {
    @PersistenceContext(unitName = "G-BUY-RESTPU")
    private EntityManager em;

    public BonAchatFacadeREST() {
        super(BonAchat.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(BonAchat entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(BonAchat entity) {
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
    public BonAchat find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<BonAchat> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<BonAchat> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
     * @param idutilisateur
     * @return  List<BonAchat>
     */
    @GET
    @Path("user/{idutilisateur}")
    @Produces({"application/xml", "application/json"})
    public List<BonAchat> findByUserid(@PathParam("idutilisateur") Integer id){
        Query q = em.createNamedQuery("BonAchat.findByUserid");
        q.setParameter("idutilisateur", id);
        List<BonAchat> list = q.getResultList();
      
        if(!list.isEmpty())
        {
            return list;
        }
        return null;
    }
    
    
}
