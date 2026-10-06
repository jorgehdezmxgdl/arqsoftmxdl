package mx.gdl.unedl.apazonweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author jfaus
 */
public class Procesa extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clave    = request.getParameter("clave");
        String password = request.getParameter("passw");
     
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Validando acceso...</title>");
            out.println("</head>");
            out.println("<body>");
            if (clave.isEmpty()) {
                out.println("<p style='color:red;'>Falta especificar la clave</p>");
                out.println("<p><a href='javascript:history.go(-1);'>Regresar</a></p>");
            }else if (password.isEmpty()) {
                out.println("<p style='color:red;'>Falta especificar el password</p>");
                out.println("<p><a href='javascript:history.go(-1);'>Regresar</a></p>");
            }else if (clave.equals("admin") && (password.equals("12345"))) {
                //out.println("<p style='color:blue;'>Acceso concedido</p>");
                HttpSession session = request.getSession();
                session.setAttribute("valido", "1");
                response.sendRedirect("dashboard.jsp");
            }else {
                out.println("<p style='color:red;'>Credenciales inválidas</p>");
                out.println("<p><a href='javascript:history.go(-1);'>Regresar</a></p>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
