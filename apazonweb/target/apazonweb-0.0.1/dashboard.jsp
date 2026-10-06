<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HttpSession mi_session = request.getSession();
            Object valor  = mi_session.getAttribute("valido");
            if (valor == null || (!valor.toString().equals("1"))) {
                response.sendRedirect("index.html");
            } 
        %>
        <h1 style="color: blue; text-align: center;">Apazon Web</h1>
        <% 
            mi_session.removeAttribute("valido");
            mi_session.invalidate();
            out.println("<p>Tu sesión se ha cerrado </p>");
        %>
    </body>
</html>
