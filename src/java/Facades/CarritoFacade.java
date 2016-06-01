/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Carrito;
import Entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HP PAVILION X360
 */
@Stateless
public class CarritoFacade extends AbstractFacade<Carrito> {

    @PersistenceContext(unitName = "sigpePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarritoFacade() {
        super(Carrito.class);
    }
    
    public List<Carrito> listaPorCedula(Usuario u){
        Query q = em.createNativeQuery("select * from carrito where cedula=?", Carrito.class);
        q.setParameter(1, u.getCedula());
        return q.getResultList();
    }
    
}
