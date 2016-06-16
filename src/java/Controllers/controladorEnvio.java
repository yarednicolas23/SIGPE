/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Empresa;
import Entities.Envio;
import Entities.Pedido;
import Entities.Usuario;
import Facades.EnvioFacade;
import Facades.PedidoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
@Named(value = "controladorEnvio")
@SessionScoped
public class controladorEnvio implements Serializable {

    /**
     * Creates a new instance of controladorEnvio
     */
    public controladorEnvio() {
    }
    Usuario user = new Usuario();
    Pedido pedido = new Pedido();
    Empresa empresa = new Empresa();
    Envio envio = new Envio();

    private Integer codigoEnvio;
    private Integer codigoPedido;
    private Date fechaEnvio;
    private Integer codigoEmpresa;

    @EJB
    PedidoFacade pedidosFacade = new PedidoFacade();
    @EJB
    EnvioFacade envioFacade = new EnvioFacade();

    public Integer getCodigoEnvio() {
        return codigoEnvio;
    }

    public void setCodigoEnvio(Integer codigoEnvio) {
        this.codigoEnvio = codigoEnvio;
    }

    public Integer getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Integer codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public Integer getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(Integer codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public EnvioFacade getEnvioFacade() {
        return envioFacade;
    }

    public void setEnvioFacade(EnvioFacade envioFacade) {
        this.envioFacade = envioFacade;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public PedidoFacade getPedidosFacade() {
        return pedidosFacade;
    }

    public void setPedidosFacade(PedidoFacade pedidosFacade) {
        this.pedidosFacade = pedidosFacade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Envio> listaEnvios() {
        return envioFacade.findAll();
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

    public void eliminarPedido(Pedido p) {
        pedidosFacade.remove(p);
    }

    public String eliminarPed(Pedido lista) {
        pedidosFacade.remove(lista);
        return "index";
    }

    public void crearEnvio(Pedido p) {
        envio.setCodigoEnvio(null);
        envio.setCodigoPedido(p);
        Date ahora = new Date();
        envio.setFechaEnvio(ahora);
        Empresa emp = new Empresa();
        emp.setCodigoEmpresa(codigoEmpresa);
        envio.setEmpresa(emp);
        envioFacade.create(envio);
    }
    
    public void create(){
        String algo="";
    }
}
