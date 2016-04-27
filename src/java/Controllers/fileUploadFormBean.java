/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.Part;

/**
 *
 * @author HP PAVILION X360
 */
@Named(value = "fileUploadFormBean")
@RequestScoped
public class fileUploadFormBean {

    private Part fileUpload;
    
    public fileUploadFormBean() {
    }

    public Part getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(Part fileUpload) {
        this.fileUpload = fileUpload;
    }
            
}
