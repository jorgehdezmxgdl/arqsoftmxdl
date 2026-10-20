<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="mx.gdl.unedl.apazonweb.model.Producto"%>
<%@page import="mx.gdl.unedl.apazonweb.db.DbManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    DbManager dbm = new DbManager();
    String dato = request.getParameter("producto");
    List<Producto> listado = dbm.getListado(dato);
   
    Gson gson   = new Gson();
    String json = gson.toJson(listado);
    dbm         = null;
    
    out.println(json);
%>