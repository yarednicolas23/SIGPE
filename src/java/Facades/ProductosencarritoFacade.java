/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Carrito;
import Entities.Productosencarrito;
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
public class ProductosencarritoFacade extends AbstractFacade<Productosencarrito> {

    @PersistenceContext(unitName = "sigpePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductosencarritoFacade() {
        super(Productosencarrito.class);
    }
    
    public List<Productosencarrito> listaPEC(Carrito c){
        Query q = em.createNativeQuery("select * from Productosencarrito where codCarrito=?", Productosencarrito.class);
        q.setParameter(1, c.getCodigoCarrito());
        return q.getResultList();
    }
        
}
