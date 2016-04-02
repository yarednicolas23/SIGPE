/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Producto;
import Facades.ProductoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author HP PAVILION X360
 */
@Named(value = "controladorProductos")
@SessionScoped
public class controladorProductos implements Serializable {

    Producto producto= new Producto();
    
    private String tipoMensaje;
    private String mensaje;
    
    @EJB
    ProductoFacade productoFacade = new ProductoFacade();
    
    public controladorProductos() {
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
        
    public ExternalContext traerDatos() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        return ec;
    }
    
    public List<Producto> listaProductos(){
        return productoFacade.findAll();
    }
    
    public void crearProducto(){
        Map datos= traerDatos().getRequestParameterMap();
        if (productoFacade.consultarProducto(""+datos.get("nombreP"))) {
            producto.setReferecia(null);
            producto.setNombre(""+datos.get("nombreP"));
            producto.setPrecio(Integer.parseInt(""+datos.get("precio")));
            producto.setDescripcion(""+datos.get("descripcion"));
            producto.setFoto("../img/productos/default.png");
            producto.setCantidadDisponible(Integer.parseInt(""+datos.get("cantidad")));
            producto.setEstadoProducto("1");
            productoFacade.create(producto);                        
        }else{
            mensaje="¡Error! El producto ya existe";
            tipoMensaje="activate";
        }            
    }
    
    public void c(){
        String algo = "a";
    }
       
}
