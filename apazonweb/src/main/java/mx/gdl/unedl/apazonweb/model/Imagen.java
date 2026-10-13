package mx.gdl.unedl.apazonweb.model;

/**
 *
 * @author jfaus
 */
public class Imagen {
    private String url;
    private int  ancho;
    private int  largo;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public Imagen(String url, int ancho, int largo) {
        this.url = url;
        this.ancho = ancho;
        this.largo = largo;
    }

    public Imagen() {
        this.url   = "";
        this.ancho = 46;
        this.largo = 46;
    }
}
