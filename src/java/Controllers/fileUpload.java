/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author HP PAVILION X360
 */
@Named(value = "fileUpload")
@SessionScoped
public class fileUpload implements Serializable {

    private Part arch;

    public fileUpload() {
    }

    public Part getArch() {
        return arch;
    }

    public void setArch(Part arch) {
        this.arch = arch;
    }

    public ExternalContext traerDatos() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        return ec;
    }

    public void subir() throws IOException {
        //Ruta donde se almacena el archivo
        String uploadPath = traerDatos().getRealPath("../../web/img/") + File.separator + "profile";
        //almacena en un File la ruta
        File uploadDir = new File(uploadPath);
        //crea la carpeta en caso de que no exista
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            //lectura del archivo en el servidor
            InputStream inputS = arch.getInputStream();            
            //nombre del archivo
            String name = arch.getSubmittedFileName();
            //ruta del almacenamiento y nombre del archivo
            String filePath = uploadPath + File.separator + name;
            //ruta final más el archivo en cuestión
            File storeFile = new File(filePath);
            //salida final del archivo
            FileOutputStream ous2 = new FileOutputStream(storeFile);
            //velocidad a la que se procesa el archivo en bytes
            byte[] buffer = new byte[1024 * 1024 * 4];
            int bytesRead = 0;
            while (true) {
                bytesRead = inputS.read(buffer);
                if (bytesRead > 0) {
                    //guardado final del archivo
                    ous2.write(buffer, 0, bytesRead);
                }
                //cerrar el input y el ouput
                inputS.close();
                ous2.close();
            }
        } catch (Exception e) {
        }
    }

}
