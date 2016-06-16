/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Envio;
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
public class EnvioFacade extends AbstractFacade<Envio> {

    @PersistenceContext(unitName = "sigpePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnvioFacade() {
        super(Envio.class);
    }
    
    public boolean consultarEnvio(int codigoEnvio) {
        List<Envio> lista = new ArrayList<>();
        Query q = em.createNativeQuery("select * from envio where codigoEnvio=?", Envio.class);
        q.setParameter(1, codigoEnvio);
        lista = q.getResultList();
        return lista.isEmpty();
    }
    
}
