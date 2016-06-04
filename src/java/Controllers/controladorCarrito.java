/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Carrito;
import Entities.Pedido;
import Entities.Producto;
import Entities.Productosencarrito;
import Entities.Usuario;
import Facades.CarritoFacade;
import Facades.PedidoFacade;
import Facades.ProductosencarritoFacade;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
    int posicion;
    String mensajeScript="";

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
    PedidoFacade pedidosFacade = new PedidoFacade();
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

    public String getMensajeScript() {
        return mensajeScript;
    }

    public void setMensajeScript(String mensajeScript) {
        this.mensajeScript = mensajeScript;
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

    public void addToList(Producto p, Carrito car) {
        Productosencarrito pec2 = new Productosencarrito();
        int identificador = 0;
        if (pec.getId() != null) {
            identificador = pec.getId();
        }
        if (identificador == 0) {
            pec2.setId(1);
        } else if (identificador >= 1) {
            pec2.setId(identificador + 1);
        }
        pec2.setCantidad(1);
        pec2.setCodCarrito(car);
        pec2.setRefereciaProducto(p);
        total = total + p.getPrecio();
        pcl.add(pec2);
    }

    public boolean productIsInCar(Producto pr) {
        int ref;
        for (int i = 0; i < pcl.size(); i++) {
            ref = pcl.get(i).getRefereciaProducto().getReferecia();
            if (ref == pr.getReferecia()) {
                pec = pcl.get(i);
                posicion = i;
                return true;
            }
        }
        return false;
    }

    public void addToCart(Producto pd) {
        if (user.getCedula() != null) {
            carrito = carritoFacade.listCartUser(user);
        } else {
            carrito = carritoFacade.find(1);
        }
        boolean productoInicial = false;
        if (pcl.isEmpty()) {
            addToList(pd, carrito);
            productoInicial = true;
        }
        if (productoInicial == false) {
            if (productIsInCar(pd)) {
                int cantidad = pcl.get(posicion).getCantidad();
                pcl.get(posicion).setCantidad(cantidad + 1);
                total = total + pd.getPrecio();
            } else if (productIsInCar(pd) == false) {
                addToList(pd, carrito);
            }
        }
    }

    public List<Productosencarrito> listOfProducts() {
        List<Productosencarrito> pcl2 = new ArrayList<>();
        if (user.getCedula() == null) {
            return pcl;
        } else {
            for (int i = 0; i < pcl.size(); i++) {
                long ced = pcl.get(i).getCodCarrito().getCedula().getCedula();
                if (Objects.equals(ced, user.getCedula())) {
                    pcl2.add(pcl.get(i));
                }
            }
        }
        return pcl2;
    }
    
    public void deleteProductOfList(Producto pro){
        productIsInCar(pro);
        
    }

    public void createShip(Carrito ca, int monto) {
        Pedido pe = new Pedido();
        BigInteger mt = BigInteger.valueOf(monto);
        pe.setId(null);
        pe.setMontoTotal(mt);
        Date ahora = new Date();
        pe.setFechaPedido(ahora);
        pe.setCodigoCarrito(ca);
        pedidosFacade.create(pe);
        for (Productosencarrito listOfProduct : listOfProducts()) {
            pec = listOfProduct;
            pecFacade.create(pec);
        }
    }

    public void createShipment() {
        if (user.getCedula() != null) {
            carrito = carritoFacade.listCartUser(user);
            createShip(carrito, total);
        } else {
            mensajeScript="$('#pedidoNull').openModal();" ;
        }
    }
    public void resetMessaje(){
        mensajeScript="" ;
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
}
