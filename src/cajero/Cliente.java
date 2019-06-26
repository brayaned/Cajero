package cajero;

public class Cliente {

    private String Cedula;
    private String numeroCuenta;
    private String Nombre;

    public String getCedula() {
        return Cedula;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public void setnumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
}
