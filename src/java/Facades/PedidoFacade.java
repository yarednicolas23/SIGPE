/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Carrito;
import Entities.Pedido;
import Entities.Usuario;
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
public class PedidoFacade extends AbstractFacade<Pedido> {

    @PersistenceContext(unitName = "sigpePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }
    
    public List<Pedido> listaPorCedula(Carrito c){
        Query q = em.createNativeQuery("select * from pedido where codigoCarrito=?;", Pedido.class);
        q.setParameter(1, c.getCodigoCarrito());
        return q.getResultList();
    }
    
    public List<Pedido> listaPorEstado(Usuario u) {
        Query q = em.createNativeQuery("select * from Pedido inner join carrito on pedido.codigoCarrito = carrito.codigoCarrito where carrito.estadoPedido = 'Aprobado';", Pedido.class);
        return q.getResultList();
    }
    
}
