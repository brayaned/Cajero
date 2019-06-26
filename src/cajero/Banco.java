package cajero;

public class Banco {

    public String nombreBanco="BBVA";
    private final int costoTransaccion=5000;

    public void finalize() throws Throwable {

    }


    public String getNombreBanco() {
        return nombreBanco;
    }

    public int getCostoTransaccion() {
        return costoTransaccion;
    }
}
