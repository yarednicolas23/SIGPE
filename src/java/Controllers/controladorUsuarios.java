/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Usuario;
import Facades.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author HP PAVILION X360
 */
@Named(value = "controladorUsuarios")
@SessionScoped
public class controladorUsuarios implements Serializable {

    Usuario user = new Usuario();

    List<Usuario> listaUser = new ArrayList<>();
    List<Usuario> usuarioSesion = new ArrayList<>();

    @EJB
    UsuarioFacade userFacade = new UsuarioFacade();

    private String mensaje;
    private String tipoMensaje;
    private String scriptMensaje;

    public controladorUsuarios() {
    }

    public List<Usuario> getListaUser() {
        return listaUser;
    }

    public void setListaUser(List<Usuario> listaUser) {
        this.listaUser = listaUser;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getScriptMensaje() {
        return scriptMensaje;
    }

    public void setScriptMensaje(String scriptMensaje) {
        this.scriptMensaje = scriptMensaje;
    }

    public List<Usuario> getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(List<Usuario> usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    //Metodo para traer el ExternalContext y Mapear los datos.
    public ExternalContext traerDatos() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        return ec;
    }

    //Metodo para autentificar la sesion y los permisos del usuario
    public void autentificar() {
        HttpServletRequest sr = (HttpServletRequest) traerDatos().getRequest();
        if (sr.getSession().getAttribute("admin") == null) {
            try {
                traerDatos().redirect("../index.xhtml");
            } catch (Exception e) {
            }
        }
    }

    //Metodo de inicio de sesion  
    public void iniciarSesion() {
        Map d = traerDatos().getRequestParameterMap();
        HttpServletRequest sr = (HttpServletRequest) traerDatos().getRequest();
        listaUser = userFacade.consultarUsuario("" + d.get("email"));
        if (listaUser.isEmpty() == true) {
            tipoMensaje = "mensaje-sesion-active";
            scriptMensaje = "$('#sesion').openModal();";
            mensaje = "¡Error! El correo no esta registrado";
        }
        for (int i = 0; i < listaUser.size(); i++) {
            if (listaUser.get(i).getContrasena().equals("" + d.get("password"))) {
                if (listaUser.get(i).getRol().equals("Administrador")) {
                    sr.getSession().setAttribute("admin", listaUser);
                    usuarioSesion = (List<Usuario>) sr.getSession().getAttribute("admin");
                    try {
                        traerDatos().redirect("admin/index.xhtml");
                    } catch (Exception e) {
                    }
                }
            } else {
                tipoMensaje = "mensaje-sesion-active";
                mensaje = "¡Error! La contraseña no coincide";
                scriptMensaje = "$('#sesion').openModal();";
            }
        }
    }

    public void cerrarSesion() {
        HttpServletRequest sr = (HttpServletRequest) traerDatos().getRequest();
        sr.getSession().invalidate();
        try {
            traerDatos().redirect("../index.xhtml");
        } catch (Exception e) {
        }
    }
}
