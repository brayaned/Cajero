package cajero;

public class Tarjeta {

    private int Clave;
    private int CuentaAsociada;
    private String IdTarjeta;

    public Cliente getCliente() {
        return null;
    }

    public Cuenta getCuenta() {
        return null;
    }

    public int getClave() {
        return Clave;
    }

    public int getCuentaAsociada() {
        return CuentaAsociada;
    }

    public String getIdTarjeta() {
        return IdTarjeta;
    }

    public void setClave(int Clave) {
        this.Clave = Clave;
    }

    public void setCuentaAsociada(int CuentaAsociada) {
        this.CuentaAsociada = CuentaAsociada;
    }

    public void setIdTarjeta(String IdTarjeta) {
        this.IdTarjeta = IdTarjeta;
    }
}
