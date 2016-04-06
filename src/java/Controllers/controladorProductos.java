/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Producto;
import Facades.ProductoFacade;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

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
    List<Producto> listaProducto= new ArrayList<>();
    
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
    
    
    public void eliminarProducto(Producto pro){
        productoFacade.remove(pro);
    }
    
    public void editarProducuto(Producto proE){
        productoFacade.edit(proE);
        try {
            traerDatos().redirect("products.xhtml");
        } catch (Exception e) {
        }
    }
    
    
    
    
    
    //Reportes
    JasperPrint jasperPrint;
    public void init() throws JRException {
        listaProducto=productoFacade.findAll();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(productoFacade.findAll());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = (String) servletContext.getRealPath("/reportes"); // Sustituye "/" por el directorio ej: "/upload"
        realPath+="/report1.jasper"; 
        jasperPrint = JasperFillManager.fillReport(realPath, new HashMap(), beanCollectionDataSource);
    }

    public void exportarPDF() throws JRException,IOException{
        Map<String,Object> parametros = new HashMap<String, Object>();
        parametros.put("txtA", "Yared");
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportes/report1.jasper"));
        JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametros,new JRBeanCollectionDataSource(productoFacade.findAll()));
        
        HttpServletResponse response= (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition","attachment; filename=jsfReporte.pdf");
        ServletOutputStream stream= response.getOutputStream();
        
        JasperExportManager.exportReportToPdfStream(jp, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    public void PDF() throws JRException, IOException {
        init();
       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
      ServletOutputStream servletOutputStream;
        servletOutputStream = httpServletResponse.getOutputStream();
       JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

    }

    public void DOCX() throws JRException, IOException {
        init();
           HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.docx");
       ServletOutputStream servletOutputStream;
        servletOutputStream = httpServletResponse.getOutputStream();
       JRDocxExporter docxExporter=new JRDocxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
    }

    public void XLSX() throws JRException, IOException {
       init();
       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
       httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.xlsx");
       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
       JRXlsxExporter docxExporter=new JRXlsxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
    }

    public void ODT() throws JRException, IOException {
        init();
       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.odt");
       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
       JROdtExporter docxExporter=new JROdtExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
   }
    
       public void PPT() throws JRException, IOException{
       init();
       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pptx");
       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
       JRPptxExporter docxExporter=new JRPptxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
   }

    
    
       
}
