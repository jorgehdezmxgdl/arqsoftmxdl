<%@page import="mx.gdl.unedl.apazonweb.model.Imagen"%>
<%@page import="java.util.List"%>
<%@page import="mx.gdl.unedl.apazonweb.model.Producto"%>
<%@page import="mx.gdl.unedl.apazonweb.db.DbManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1>Productos</h1>
<div style="text-align: right">
    <input type="text" id="buscar" placeholder="Ej nombre producto" />
</div>
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
            String dato   = request.getParameter("producto");
            List<Producto> listado = dbm.getListado(dato);
            int contador = 0;
            for (Producto p : listado) {
                contador += 1;
        %>
        <tr>
            <td><%= contador%></td> 
            <%
                List<Imagen> imagen = p.getListado();
                for (Imagen m : imagen) {
            %>
            <td><img src  ="<%= m.getUrl()%>" 
                     width ="<%= m.getAncho()%>" 
                     height="<%= m.getLargo()%>"
                     alt="Producto"
                     /></td>
                <%
                    }
                %>
            <td><%= p.getNombre()%></td>
            <td><%= p.getDescripcion()%></td>
            <td><%= p.getPrecio()%></td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>
<script>
    $(document).ready(function () {
        $("#buscar").keypress(function (e) {
            const palabra = $(this).val().length;
            if (palabra >= 2) {
                $.ajax({
                    url: "procesa.jsp",
                    data: {
                        "producto" : palabra
                    },
                    type: "POST",
                    cache: false,
                    success: function (result) {
                       console.log(result);
                    }
                });
            }
        });
    });
</script>
