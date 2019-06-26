package cajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaccion {

    private String NumeroCuenta;
    private int Costo;
    private String Descripcion;
    private String Fecha;
    private int Monto;

    Conexion conexion = new Conexion();
    Connection con = conexion.realziarConexion();
    PreparedStatement ps = null;
    Date date = new Date();

    public int verificarCuenta(int j, Cuenta cuenta, Cliente cliente, String nCuenta) {
        String sql = "SELECT * FROM Cuenta";
        Statement st, stm;
        ResultSet result;
        try {
            st = con.createStatement();
            result = st.executeQuery(sql);
            while (result.next()) {
                if (nCuenta.equals(result.getString(1))) {
                    cuenta.setNumeroCuenta(result.getString(1));
                    cuenta.setSaldo(result.getInt(2));
                    cuenta.setTitutar(result.getString(3));
                    j = 1;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error de Conexion1: " + e.getMessage());
        }
        try {
            stm = con.createStatement();
            result = stm.executeQuery("SELECT * FROM Cliente WHERE numeroCuenta='" + nCuenta + "'");
            while (result.next()) {
                cliente.setCedula(result.getString(1));
                cliente.setNombre(result.getString(3));
                cliente.setnumeroCuenta(nCuenta);
            }
        } catch (SQLException e) {
            System.out.println("Error de Conexion2: " + e.getMessage());
        }
        return j;
    }

    public int verificarClave(int i, String nCuenta, int clave) {
        String sql = "SELECT * FROM Tarjeta";
        Statement st;
        try {
            st = con.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                if (nCuenta.equals(result.getString(2))) {
                    if (clave != result.getInt(3)) {
                        
                        i++;
                    } else {
                        System.out.println("Proceso realizado correctamente\n");
                        i = 6;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    public void insertarDatos(Transaccion tr) throws SQLException {
        String sql = "INSERT INTO Transaccion(numeroCuenta,costo,descripcion,fecha,monto) VALUES (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, tr.getNumeroCuenta());
            ps.setInt(2, tr.getCosto());
            ps.setString(3, tr.getDescripcion());
            ps.setString(4, tr.getFecha());
            ps.setInt(5, tr.getMonto());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String consultarDatos(String numeroCuenta, String titular) {
        String sql = "SELECT * FROM Transaccion";
        String r="";
        Statement st;
        try {
            st = con.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                if (numeroCuenta.equals(result.getString(1))) {
                    
                    r=("\nNumero de cuenta: " + result.getString(1)+
                    "\nTitular de la cuenta: " + titular+
                    "\nCosto de transaccion: " + result.getInt(2)+
                    "\nDescripcion: " + result.getString(3)+
                    "\nMonto: " + result.getInt(5)+
                    "\nFecha: " + result.getString(4));
                }
            }
        } catch (SQLException e) {
            System.out.println("Hi prro " + e.getMessage());
        }
        return r;
    }

    public int actualizarDatos(String nCuenta, int monto) {
        String sql1 = "SELECT * FROM Cuenta";
        String sql2 = "SELECT * FROM Cajero";
        Statement st;
        int total, saldo = 0;
        try {//ACTUALIZAR CUENTA
            st = con.createStatement();
            ResultSet result = st.executeQuery(sql1);
            while (result.next()) {
                //if (nCuenta.equals(result.getString(1))) {
                total = result.getInt(2) - monto - 5000;
                ps = con.prepareStatement("UPDATE Cuenta SET saldo='" + total + "' WHERE numeroCuenta='" + nCuenta + "'");
                ps.executeUpdate();
                saldo = total;
                //}
            }
        } catch (SQLException e) {
            System.out.println("Error de Conexion: " + e.getMessage());
        }

        try {//ACTUALIZAR CAJERO
            st = con.createStatement();
            ResultSet result = st.executeQuery(sql2);
            while (result.next()) {
                if ("2929".equals(result.getString(1))) {
                    total = result.getInt(3) - monto;
                    ps = con.prepareStatement("UPDATE Cajero SET saldo='" + total + "'");
                }
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error de Conexion: " + e.getMessage());
        }
        return saldo;
    }

    public int transferencia(String nCuenta, String nCuenta1, int monto) {
        String sql = "SELECT * FROM Cuenta";
        Statement st;
        int total, total1, saldo = 0;
        try {//ACTUALIZAR CUENTA1
            st = con.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                if (nCuenta.equals(result.getString(1))) {
                total = result.getInt(2) - monto - 5000;
                ps = con.prepareStatement("UPDATE Cuenta SET saldo='" + total + "' WHERE numeroCuenta='" + nCuenta + "'");
                ps.executeUpdate();
                saldo = total;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error de Conexion: " + e.getMessage());
        }

        try {//ACTUALIZAR CUENTA2
            st = con.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                if (nCuenta1.equals(result.getString(1))) {
                    total1 = result.getInt(2) + monto;
                    ps = con.prepareStatement("UPDATE Cuenta SET saldo='" + total1 + "' WHERE numeroCuenta='" + nCuenta1 + "'");
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error de Conexion: " + e.getMessage());
        }
        return saldo;
    }

    public int getCosto() {
        return Costo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getFecha() {
        return Fecha;
    }

    public int getMonto() {
        return Monto;
    }

    public void setCosto(int Costo) {
        this.Costo = Costo;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public void setMonto(int Monto) {
        this.Monto = Monto;
    }

    public String getNumeroCuenta() {
        return NumeroCuenta;
    }

    public void setNumeroCuenta(String NumeroCuenta) {
        this.NumeroCuenta = NumeroCuenta;
    }

}
