/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Empresa;
import Entities.Envio;
import Entities.Usuario;
import Facades.EmpresaFacade;
import Facades.EnvioFacade;
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
@Named(value = "controladorEmpresa")
@SessionScoped
public class controladorEmpresa implements Serializable {

    Usuario user = new Usuario();
    Empresa empresa = new Empresa();
    Envio envio = new Envio();

    List<Empresa> listaEmpresa = new ArrayList<>();

    @EJB
    EmpresaFacade empresaFacade = new EmpresaFacade();
    @EJB
    EnvioFacade envioFacade = new EnvioFacade();

    /**
     * Creates a new instance of controladorEmpresa
     */
    public controladorEmpresa() {
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
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

    public List<Empresa> getListaEmpresa() {
        return empresaFacade.findAll();
    }

    public void setListaEmpresa(List<Empresa> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }

    public EmpresaFacade getEmpresaFacade() {
        return empresaFacade;
    }

    public void setEmpresaFacade(EmpresaFacade empresaFacade) {
        this.empresaFacade = empresaFacade;
    }

    public EnvioFacade getEnvioFacade() {
        return envioFacade;
    }

    public void setEnvioFacade(EnvioFacade envioFacade) {
        this.envioFacade = envioFacade;
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

}
