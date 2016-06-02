/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Carrito;
import Entities.Rol;
import Entities.Usuario;
import Facades.CarritoFacade;
import Facades.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
    Rol rol = new Rol();
    Carrito carrito= new Carrito();

    List<Usuario> listaUser = new ArrayList<>();
    List<Usuario> usuarioSesion = new ArrayList<>();

    @EJB
    UsuarioFacade userFacade = new UsuarioFacade();
    @EJB
    CarritoFacade carritoFacade= new CarritoFacade();

    //Variable para mensajes emergentes de validación
    private String mensaje;
    //Variable tipo Mensaje para activa y desactivar el mensaje por medio de "activate"
    private String tipoMensaje;
    //variable Script Mensaje envia el mensaje por medio de un script del framework
    private String scriptMensaje;
    //variable Sesion activa activa las opciones de perfil de Usuario 
    private String sesionActiva;
    private String inactive;

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

    public String getSesionActiva() {
        return sesionActiva;
    }

    public void setSesionActiva(String sesionActiva) {
        this.sesionActiva = sesionActiva;
    }

    public String getInactive() {
        return inactive;
    }

    public void setInactive(String inactive) {
        this.inactive = inactive;
    }    

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }   

    //Metodo para traer el ExternalContext y Mapear los datos.
    public ExternalContext traerDatos() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        return ec;
    }

    //Metodo para autentificar la sesion y los permisos del administrador
    public void autentificar() {
        HttpServletRequest sr = (HttpServletRequest) traerDatos().getRequest();
        if (sr.getSession().getAttribute("admin") == null) {
            try {
                traerDatos().redirect("../index.xhtml");
            } catch (Exception e) {
            }
        }
    }

    //Metodo para autentificar la sesion y los permisos del usuario
    public void autentificarUser() {
        HttpServletRequest sr = (HttpServletRequest) traerDatos().getRequest();
        if (sr.getSession().getAttribute("user") == null) {
            try {
                traerDatos().redirect("../index.xhtml");
            } catch (Exception e) {
            }
        }
    }
    
    //Registro usuario nuevo
    public void usuarioNuevo() {
        controladorCarrito cc = new controladorCarrito();
        
        Map datos = traerDatos().getRequestParameterMap();

        user.setCedula(Long.parseLong("" + datos.get("cedula")));
        user.setCorreo("" + datos.get("correo"));
        user.setNombres("" + datos.get("nombres"));
        user.setApellidos("" + datos.get("apellidos"));
        user.setSexo("" + datos.get("sexo"));
        user.setTelefono(Long.parseLong("" + datos.get("telefono")));
        user.setContrasena("" + datos.get("clave"));
        rol.setIdRol(1);
        user.setRol(rol);
        user.setFoto("img/profile/avatar.png");
        userFacade.create(user);
        
        carrito.setCodigoCarrito(null);
        Date ahora = new Date();
        carrito.setFechaCarrito(ahora);
        carrito.setEstadoPedido("1");
        carrito.setCedula(user);
        carritoFacade.create(carrito);
        
    }

    public void registrarse() {
        Map datos = traerDatos().getRequestParameterMap();
        listaUser = userFacade.consultarUsuario("" + datos.get("correo"));
        if (listaUser.isEmpty()) {
            usuarioNuevo();
            try {
                traerDatos().redirect("index.xhtml");
            } catch (Exception e) {
            }
        }
        if (!listaUser.isEmpty()) {
            tipoMensaje = "activate";
        }
    }

    //Metodo de inicio de sesion  
    public void iniciarSesion() {
        Map d = traerDatos().getRequestParameterMap();
        HttpServletRequest sr = (HttpServletRequest) traerDatos().getRequest();
        listaUser = userFacade.consultarUsuario("" + d.get("email"));
        if (listaUser.isEmpty() == true) {
            tipoMensaje = "activate";
            scriptMensaje = "$('#sesion').openModal();";
            mensaje = "¡Error! El correo no esta registrado";
        }
        for (int i = 0; i < listaUser.size(); i++) {
            if (listaUser.get(i).getContrasena().equals("" + d.get("password"))) {
                if (listaUser.get(i).getRol().getIdRol() == 2) {
                    sr.getSession().setAttribute("admin", listaUser);
                    usuarioSesion = (List<Usuario>) sr.getSession().getAttribute("admin");
                    scriptMensaje = "";
                    try {
                        traerDatos().redirect("admin/index.xhtml");
                    } catch (Exception e) {
                    }
                }
                if (listaUser.get(i).getRol().getIdRol() == 1) {
                    sr.getSession().setAttribute("user", listaUser);
                    usuarioSesion = (List<Usuario>) sr.getSession().getAttribute("user");
                    sesionActiva = "activate";
                    inactive = "inactive";
                    scriptMensaje = "";
                    try {
                        traerDatos().redirect("index.xhtml");
                    } catch (Exception e) {
                    }
                }
            } else {
                tipoMensaje = "activate";
                mensaje = "¡Error! La contraseña no coincide";
                scriptMensaje = "$('#sesion').openModal();";
            }
        }
    }
    
    public void idUsuarioEdit(long id){
        user= userFacade.find(id);
        try {
            traerDatos().redirect("editUser.xhtml");
        } catch (Exception e) {
        }
    }

    public void editarUsuario(Usuario u) {
        userFacade.edit(u);
        try {
            traerDatos().responseReset();
        } catch (Exception e) {
        }
    }

    public void cerrarSesion() {
        HttpServletRequest sr = (HttpServletRequest) traerDatos().getRequest();
        sr.getSession().getAttribute("admin");
        if (sr.getSession().getAttribute("user") != null) {
            sr.getSession().invalidate();
            sesionActiva = "false";
            try {
                traerDatos().redirect("index.xhtml");
            } catch (Exception e) {
            }
        }
        if (sr.getSession().getAttribute("admin") != null) {
            sr.getSession().invalidate();
            try {
                traerDatos().redirect("../index.xhtml");
            } catch (Exception e) {
            }
        }
    }
    
    public List<Usuario> listaUsersAll(){
        return userFacade.findAll();
    }
    
}
