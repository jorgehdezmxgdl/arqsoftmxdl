<%@page import="mx.gdl.unedl.apazonweb.model.Imagen"%>
<%@page import="java.util.List"%>
<%@page import="mx.gdl.unedl.apazonweb.model.Producto"%>
<%@page import="mx.gdl.unedl.apazonweb.db.DbManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Productos disponibles</title>
    </head>
    <body>
        <h1>Productos</h1>
        <table>
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Imagen</th>
                    <th>Nombre</th>
                    <th>Descripcion</th>
                    <th>Precio</th>
                </tr>
            </thead>
            <tbody>
                <%
                    DbManager dbm = new DbManager();
                    List<Producto> listado = dbm.getListado();
                    int contador = 0;
                    for (Producto p : listado) {
                        contador += 1;
                        %>
                        <tr>
                            <td><%= contador %></td> 
                            <%
                                List<Imagen> imagen = p.getListado();
                                for (Imagen m: imagen) {
                            %>
                             <td><img src  ="<%= m.getUrl()  %>" 
                                     width ="<%= m.getAncho() %>" 
                                     height="<%= m.getLargo() %>"
                                     alt="Producto"
                                    /></td>
                            <% 
                                } 
                            %>
                            <td><%= p.getNombre()%></td>
                            <td><%= p.getDescripcion() %></td>
                            <td><%= p.getPrecio() %></td>
                        </tr>
                <%        
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
