/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author HP PAVILION X360
 */
@Named(value = "controladorReportes")
@RequestScoped
public class controladorReportes {

    /**
     * Creates a new instance of controladorReportes
     */
    public controladorReportes() {
    }
    
        public void pdfConsulta() throws ClassNotFoundException, SQLException, JRException, IOException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/sigpe", "root", "1234");
        
        
        // Parametros para enviar al reporte
        Map<String,Object> parametros = new HashMap<String, Object>();
        parametros.put("texto", "nicolas");
        
        //Cargar el archivo .jasper
        File archivo = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportes/report1.jasper"));
        // Enviar el archivo, los parametros y la conexion o los datos a llenar
        JasperPrint jp= JasperFillManager.fillReport(archivo.getPath(), parametros,conexion);
        
        // inicializar la descarga del archivo
        HttpServletResponse sr = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        sr.addHeader("Content-disposition", "attachment; filename=reporteUsuarios.pdf");
                
        ServletOutputStream stream = sr.getOutputStream();
        
        // Descarga del pdf
        JasperExportManager.exportReportToPdfStream(jp, stream);
        stream.flush();
        stream.close();
        
        FacesContext.getCurrentInstance().responseComplete();
    }
    
}
