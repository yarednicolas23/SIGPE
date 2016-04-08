/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Carrito;
import Entities.Pedido;
import Entities.Usuario;
import Facades.PedidoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author HP PAVILION X360
 */
@Named(value = "controladorPedidos")
@SessionScoped
public class controladorPedidos implements Serializable {

    
    Usuario user = new Usuario();
    Pedido pedido = new Pedido();
    @EJB
    PedidoFacade pedidosFacade= new PedidoFacade();
    
    public controladorPedidos() {
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
       
    public List<Pedido> listaPedidos(){
        return pedidosFacade.findAll();
    }
    
    public ExternalContext traerDatos() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        return ec;
    }
    
    public void asignarUsuarioSesion(){
        List<Usuario> listUser = new ArrayList<>();
        HttpServletRequest sr= (HttpServletRequest) traerDatos().getRequest();
        listUser=(List<Usuario>) sr.getSession().getAttribute("user");
        for (int i = 0; i < listUser.size(); i++) {
            user=listUser.get(i);
        }
    }
    
    public List<Pedido> listaPedidosUser(){
        if (user.getCedula()==null) {
            asignarUsuarioSesion();
        }
        return pedidosFacade.listaPorCedula(user);
    }
    
    public void crearPedido(){
        
    }
    
    public void idPedido(int id){
        pedido= pedidosFacade.find(id);
        try {
            traerDatos().redirect("editShipment.xhtml");
        } catch (Exception e) {
        }
    }
    
    public void eliminarPedido(Pedido p){
        pedidosFacade.remove(p);
    }
    
}
