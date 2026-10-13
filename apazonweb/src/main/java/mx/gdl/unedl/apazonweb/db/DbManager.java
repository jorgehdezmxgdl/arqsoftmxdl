package mx.gdl.unedl.apazonweb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.gdl.unedl.apazonweb.model.Imagen;
import mx.gdl.unedl.apazonweb.model.Producto;

/**
 *
 * @author jfaus
 */
public class DbManager {
    private String url     = "jdbc:mysql://localhost:3306/"
            + "apazondb?autoReconnect=true&useSSL=false&serverTimezone=UTC";
    private String usuario = "root";
    private String passwd  = "12345";
    
    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cp = DriverManager.getConnection(url, usuario, passwd);
            return cp;
        } catch (SQLException | ClassNotFoundException ex) {
            System.getLogger(DbManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }   
    
    public boolean es_usuarioValido(String clave, String passwd){
        Connection cp = this.getConnection();
        boolean valido= false;
        if (cp != null) {
            try {
                try (PreparedStatement ps = cp.prepareStatement("select 1 from usuarios"
                        + " where clave = ? and passwd = ? and activo = 1")) {
                    ps.clearParameters();
                    ps.setString(1, clave);
                    ps.setString(2, passwd);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            valido = true;
                        }
                    }
                }
            } catch (SQLException ex) {
                System.getLogger(DbManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        return valido; 
    }
    
    public List<Producto> getListado() {
        List<Producto> listado = new ArrayList();
        Connection   cp        = this.getConnection();
        if (cp != null) {
            try {
                Statement st = cp.createStatement();
                ResultSet rs = st.executeQuery("SELECT p.nombre, p.descripcion, p.precio, " +
"                         i.nombre_imagen, i.ancho, i.largo FROM productos p INNER JOIN " +
"                         prod_imagenes i ON i.productos_id = p.id ORDER BY p.nombre");
                List<Imagen> misImagenes = new ArrayList<>();
                while (rs.next()) {
                    misImagenes.add(new Imagen(rs.getString(4), rs.getInt(5), 
                            rs.getInt(6)));
                    listado.add(new Producto(rs.getString(1), rs.getString(2), 
                            rs.getFloat(3), misImagenes));
                    misImagenes.clear();
                }
                rs.close();
                st.close();
            } catch (SQLException ex) {
                System.getLogger(DbManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        return listado;
    }
    
}
