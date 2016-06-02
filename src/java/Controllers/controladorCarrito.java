/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Carrito;
import Entities.Producto;
import Entities.Productosencarrito;
import Entities.Usuario;
import Facades.CarritoFacade;
import Facades.ProductosencarritoFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

public class controladorCarrito implements Serializable {

    int total;

    controladorUsuarios cUser = new controladorUsuarios();
    List<Producto> listaProductos = new ArrayList<>();
    List<Productosencarrito> pcl = new ArrayList<>();

    Carrito carrito = new Carrito();
    Usuario user = new Usuario();
    Producto producto = new Producto();
    Productosencarrito pec = new Productosencarrito();

    @EJB
    CarritoFacade carritoFacade;
    @EJB
    ProductosencarritoFacade pecFacade = new ProductosencarritoFacade();

    public controladorCarrito() {
    }

    public List<Producto> getListaProductos() {
        return this.listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ExternalContext traerDatos() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        return ec;
    }

    public void asignarUsuarioSesion() {
        List<Usuario> listUser = new ArrayList<>();
        HttpServletRequest sr = (HttpServletRequest) traerDatos().getRequest();
        listUser = (List<Usuario>) sr.getSession().getAttribute("user");
        for (int i = 0; i < listUser.size(); i++) {
            user = listUser.get(i);
        }
    }

    //Metodo para identificar la sesion de un usuario
    public boolean userSession() {
        HttpServletRequest sr = (HttpServletRequest) traerDatos().getRequest();
        if (sr.getSession().getAttribute("user") == null) {
            return false;
        } else {
            return true;
        }
    }

    public void crearCarrito() {
        carrito.setCodigoCarrito(null);
        Date ahora = new Date();
        carrito.setFechaCarrito(ahora);
        carrito.setEstadoPedido("1");
        carrito.setCedula(user);
        carritoFacade.create(carrito);
    }

    public List<Carrito> listaCarritosUser() {
        List<Carrito> lc = new ArrayList<>();
        if (userSession() == true) {
            asignarUsuarioSesion();
            return carritoFacade.listaPorCedula(user);
        } else if (user.getCedula() == null) {
            lc.add(carritoFacade.find(1));
            return lc;
        }
        return null;
    }
    
    public List<Productosencarrito> listProductsInCart() {
        pec=pecFacade.find(1);
        pcl= pecFacade.listaPorCedula(pec.getCodCarrito());
        return pcl;
    }

    public void traerID(int id) {
        carrito = carritoFacade.find(id);
        cUser.setScriptMensaje("$('#eliminar').openModal();");
    }

    public void eleminarCarrito(Carrito c) {
        carritoFacade.remove(c);
    }

    public void agregarAlCarrito(Producto p) {
        listaProductos.add(p);
    }

    public List<Producto> traerListaP() {
        return listaProductos;
    }

    public void addToList(Producto p, int car) {
        carrito = carritoFacade.find(car);
        pec.setId(1);
        pec.setCantidad(1);
        pec.setCodCarrito(carrito);
        pec.setRefereciaProducto(p);
        total = total + p.getPrecio();
        pcl.add(pec);
    }

    public boolean productIsInCar(Producto pr) {
        int ref;
        for (int i = 0; i < pcl.size(); i++) {
            ref = pcl.get(i).getRefereciaProducto().getReferecia();
            if (ref == pr.getReferecia()) {
                pec = pcl.get(i);
                return true;
            }
        }
        return false;
    }

    public void addToCart(Producto pd, int car) {

        boolean productoInicial = false;
        if (pcl.isEmpty()) {
            addToList(pd, car);
            productoInicial = true;
        }
        if (productoInicial == false) {
            for (int i = 0; i < pcl.size(); i++) {
                int ref = pcl.get(i).getRefereciaProducto().getReferecia();
                int cantid = pcl.get(i).getCantidad();
                if (ref == pd.getReferecia()) {
                    pcl.get(i).setCantidad(cantid + 1);
                    total = total + pd.getPrecio();
                }
            }
        }
    }

    public List<Productosencarrito> listOfProducts() {
        return pcl;
    }

}
