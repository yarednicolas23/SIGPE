/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Producto;
import java.util.ArrayList;
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
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "sigpePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    public boolean consultarProducto(String nombre){
        List<Producto> lista= new ArrayList<>();
        Query q = em.createNativeQuery("select * from producto where nombre=?", Producto.class);
        q.setParameter(1, nombre);
        lista=q.getResultList();
        return lista.isEmpty();
    }
}
