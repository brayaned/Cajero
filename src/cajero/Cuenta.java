package cajero;

public class Cuenta {

    private String NumeroCuenta;
    private int Saldo;
    private String Titutar;

    public Cuenta() {

    }

    public Cliente getCliente() {
        return null;
    }

    public Tarjeta getTarjeta() {
        return null;
    }

    public String getNumeroCuenta() {
        return NumeroCuenta;
    }

    public int getSaldo() {
        return Saldo;
    }

    public String getTitutar() {
        return Titutar;
    }

    public void setNumeroCuenta(String NumeroCuenta) {
        this.NumeroCuenta = NumeroCuenta;
    }

    public void setSaldo(int Saldo) {
        this.Saldo = Saldo;
    }

    public void setTitutar(String Titutar) {
        this.Titutar = Titutar;
    }

}
