/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.MailService;
import Facades.UsuarioFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.mail.MessagingException;

/**
 *
 * @author stiven
 */
@Named(value = "correo")
@RequestScoped
public class correo {

    @EJB
    UsuarioFacade usucarioFacade = new UsuarioFacade();

    private String tipoMensaje;
    private String mensaje;
    private List<MailService> lista = new ArrayList<>();

    public List<MailService> getLista() {
        return lista;
    }

    public void setLista(List<MailService> lista) {
        this.lista = lista;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String send() throws IOException {
        String correo;
        for (int i = 0; i < usucarioFacade.findAll().size(); i++) {
            correo = usucarioFacade.findAll().get(i).getCorreo();

            try {
                MailService.sendMessage(correo, subject, message);

            } catch (MessagingException ex) {

            }
        }

        return "mails";  // redisplay page with status message
    }

    private String recipient;
    private String subject;
    private String message;
    private String statusMessage = "";

}
