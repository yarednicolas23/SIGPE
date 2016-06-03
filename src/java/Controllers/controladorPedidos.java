/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Carrito;
import Entities.Pedido;
import Entities.Productosencarrito;
import Entities.Usuario;
import Facades.CarritoFacade;
import Facades.PedidoFacade;
import Facades.ProductosencarritoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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
    Carrito cart = new Carrito();
    Productosencarrito pec = new Productosencarrito();
    
    @EJB
    PedidoFacade pedidosFacade= new PedidoFacade();
    @EJB
    CarritoFacade cartFacade= new CarritoFacade();
    @EJB
    ProductosencarritoFacade pecFacade= new ProductosencarritoFacade();
    
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
    
    public void prePedido(){
        pec.setId(null);
        
        
    }
    
    public void crearPedido(int carrito, int monto){
        Carrito c = new Carrito();
        c= cartFacade.find(carrito);
        Pedido pe = new Pedido();
        BigInteger mt = BigInteger.valueOf(monto);
        pe.setId(null);
        pe.setMontoTotal(mt);
        Date ahora = new Date();
        pe.setFechaPedido(ahora);
        pe.setCodigoCarrito(c);
        pedidosFacade.create(pe);   
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
