package cajero;

public class Cajero {

    private String idCajero;
    private String Estado;
    private double SaldoCajero;

    public String getEstado() {
        return Estado;
    }

    public double getSaldoCajero() {
        return SaldoCajero;
    }

    public String getIdCajero() {
        return idCajero;
    }

    public void setIdCajero(String idCajero) {
        this.idCajero = idCajero;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public void setSaldoCajero(double SaldoCajero) {
        this.SaldoCajero = SaldoCajero;
    }

}
