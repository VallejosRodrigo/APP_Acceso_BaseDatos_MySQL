package modelo;

//TODO------------CLASE PARA ENCAPSULAR LOS DATOS-----------------
public class Producto {
    //-------------------------------------
    private String numArticulo, seccionArticulo, paisOrigenArticulo,
    precioArticulo;
    //-------------------------------------

    public Producto(){
        numArticulo = "";
        seccionArticulo = "";
        paisOrigenArticulo = "";
        precioArticulo = "";
    }

    //------------------------GETTER AND SETTER-------------------------------
    public String getNumArticulo() {
        return numArticulo;
    }

    public void setNumArticulo(String numArticulo) {
        this.numArticulo = numArticulo;
    }

    public String getSeccionArticulo() {
        return seccionArticulo;
    }

    public void setSeccionArticulo(String seccionArticulo) {
        this.seccionArticulo = seccionArticulo;
    }

    public String getPaisOrigenArticulo() {
        return paisOrigenArticulo;
    }

    public void setPaisOrigenArticulo(String paisOrigenArticulo) {
        this.paisOrigenArticulo = paisOrigenArticulo;
    }

    public String getPrecioArticulo() {
        return precioArticulo;
    }

    public void setPrecioArticulo(String precioArticulo) {
        this.precioArticulo = precioArticulo;
    }
}
