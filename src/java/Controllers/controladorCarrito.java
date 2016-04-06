

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Carrito;
import Entities.Producto;
import Entities.Usuario;
import Facades.CarritoFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author HP PAVILION X360
 */
@Named(value = "controladorCarrito")
@SessionScoped

public class controladorCarrito implements Serializable{

    
    controladorUsuarios cUser= new controladorUsuarios();
    List<Producto> listaProductos = new ArrayList<>();

    Carrito carrito = new Carrito();
    Usuario user= new Usuario();
    Producto producto= new Producto();

    @EJB
    CarritoFacade carritoFacade;

    public controladorCarrito() {
    }

    public List<Producto> getListaProductos() {
        return this.listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
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
    
    public void crearCarrito(){
        asignarUsuarioSesion();
        Map datos = traerDatos().getRequestParameterMap();
        carrito.setCodigoCarrito(null);
        Date ahora = new Date();
        carrito.setFechaCarrito(ahora);
        carrito.setEstadoPedido("1");
        carrito.setCedula(user);
        carritoFacade.create(carrito);
    }  
    
    public List<Carrito> listaCarritosUser(){
        if (user.getCedula()==null) {
            asignarUsuarioSesion();
        }
        return carritoFacade.listaPorCedula(user);
    }
    
    public void traerID(int id){
        carrito = carritoFacade.find(id);
        cUser.setScriptMensaje("$('#eliminar').openModal();");
        String algo="";
    }
    
    public void eleminarCarrito(Carrito c){
        carritoFacade.remove(c);
    }
    
    public void agregarAlCarrito(Producto p){
        listaProductos.add(p);
    }
    
    public List<Producto> traerListaP(){
        return listaProductos;
    }
}
