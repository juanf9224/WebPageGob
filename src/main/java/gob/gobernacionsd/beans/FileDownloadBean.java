/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.beans;

import java.io.InputStream;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author JBG INC
 */
@Named
public class FileDownloadBean {
    
    private StreamedContent file;
    private StreamedContent pdfView;
     
    public FileDownloadBean() {        
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/files/nomina.pdf");
        file = new DefaultStreamedContent(stream, "application/pdf", "nomina-gobernacionsd.pdf");
    }
 
    public StreamedContent getFile() {
        return file;
    }
    
    public StreamedContent getPdfView(){
        return pdfView;
    }
}
