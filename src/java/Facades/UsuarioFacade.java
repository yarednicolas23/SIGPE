/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "sigpePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public List<Usuario> consultarUsuario(String email){
        List<Usuario> listaUsuario = new ArrayList<>();
        Query q = em.createNativeQuery("SELECT * FROM usuario WHERE correo=?", Usuario.class);
        q.setParameter(1, email);
        listaUsuario= q.getResultList();
        return listaUsuario;
    }
}
