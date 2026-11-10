package mx.gdl.unedl.apazonweb.formatos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jfaus
 */
public class PdfManager {

    private String nombre_archivo;
    private Document document;
    private String usuario;
    private String password;
    private ServletOutputStream archivo;

    public PdfManager(String nombre_archivo, String usuario,
            String password) {
        this.nombre_archivo = nombre_archivo;
        this.usuario = usuario;
        this.password = password;
        this.creaDocumento();
    }
    
    public PdfManager(ServletOutputStream archivo, String usuario,
            String password) {
        this.archivo = archivo;
        this.usuario = usuario;
        this.password = password;
        this.creaDocumento();
    }

    private void creaDocumento() {
        this.document = new Document();
        try {
            PdfWriter writer = null;
            if (this.archivo == null) {
               writer = PdfWriter.getInstance(document,
                    new FileOutputStream(this.nombre_archivo));
            } else {
               writer = 
                     PdfWriter.getInstance(document, archivo);
            }
            byte usuario[] = this.usuario.getBytes();
            byte passwd[] = this.password.getBytes();
            writer.setEncryption(usuario, passwd,
                    PdfWriter.ALLOW_PRINTING,
                    PdfWriter.ENCRYPTION_AES_256);
            this.document.open();
        } catch (DocumentException | FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void creaParrafo(String dato) {       
        try {
            this.document.add(new Paragraph(dato));
            this.document.add(new Paragraph(""));//salto de linea
        } catch (DocumentException ex) {
            System.getLogger(PdfManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }       
    }
    
    public void creaTabla(int numero_columnas, List<String>
            encabezados, List<String> data) {
      
        try {
            PdfPTable tabla = new PdfPTable(numero_columnas);
            for (String nombre_columna : encabezados) {
                tabla.addCell(nombre_columna);
            }
            for (String info : data) {
                tabla.addCell(info);
            }
            this.document.add(tabla);
        } catch (DocumentException ex) {
            System.getLogger(PdfManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }       
    }

    public void cierraDocumento() {
        this.document.close();
    }  
    
    public static void main(String[] args) {
        PdfManager pdfmanager = new PdfManager("archivo.pdf", 
                "oswaldo", "harold69");
        pdfmanager.creaParrafo("Tabla de insumos");
        List<String> encabezados = new ArrayList<>();
        encabezados.add("Nombre");
        encabezados.add("Precio");  
        encabezados.add("Maximo");
        List<String> datos = new ArrayList<>();
        for (int i=1; i<30; i++) {
            datos.add("Producto: " + i);
            datos.add("$" + String.valueOf((int)(Math.random() * 1000))
             + ".00");
            datos.add(String.valueOf((int)(Math.random() * 10)));
        }
        pdfmanager.creaTabla(3, encabezados, datos);
        pdfmanager.cierraDocumento();
        System.out.println("Archivo generado!!!");
    }
}
