package mx.gdl.unedl.apazonweb.model;

import java.util.List;

/**
 *
 * @author jfaus
 */
public class Producto {
    private String nombre;
    private String descripcion;
    private float  precio; 
    private List<Imagen> listado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public List<Imagen> getListado() {
        return listado;
    }

    public void setListado(List<Imagen> listado) {
        this.listado = listado;
    }
   
    public Producto(String nombre, String descripcion, float precio,
            List<Imagen> listado) {
        this.nombre      = nombre;
        this.descripcion = descripcion;
        this.precio      = precio;
        this.listado     = listado;
    }
    
    public Producto() {}
   
}
