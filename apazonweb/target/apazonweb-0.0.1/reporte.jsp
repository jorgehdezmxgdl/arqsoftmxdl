
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="mx.gdl.unedl.apazonweb.model.Producto"%>
<%@page import="mx.gdl.unedl.apazonweb.db.DbManager"%>
<%@page import="mx.gdl.unedl.apazonweb.formatos.PdfManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", 
            "attachment;filename=reporte.pdf");
    PdfManager pdfmanager = new PdfManager(
            response.getOutputStream(), 
            "usuario", "harold69");
    pdfmanager.creaParrafo("Informacion de productos");
    DbManager dbm = new DbManager();
    List<Producto> listado = dbm.getListado("");
    List<String>  encabezado = new ArrayList();
    encabezado.add("Nombre del producto");
    encabezado.add("Descripción");
    encabezado.add("Precio");
    List<String> dato = new ArrayList();
    for (Producto producto : listado) {
       dato.add(producto.getNombre());
       dato.add(producto.getDescripcion());
       dato.add("$" + String.valueOf(producto.getPrecio()));
    }
    pdfmanager.creaTabla(3, encabezado, dato);
    pdfmanager.cierraDocumento();
%>
