package cajero.BD;

import java.sql.*;

public class Conexion {

    Connection conexion;
    public Connection realziarConexion() {
        String url = "jdbc:postgresql://localhost:5432/Cajero";
        String usuario = "postgres";
        String pass = "postgres2019";
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url, usuario, pass);
            java.sql.Statement st = conexion.createStatement();
            /*String sql = "SELECT * FROM Cuenta";
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                String numCuenta = result.getString("numerocuenta");
                int saldo = result.getInt("saldo");
                String titular = result.getString("titular");
                System.out.println("Cuenta: " + numCuenta + " Saldo: " + saldo + " Titular: " + titular);
            }
            result.close();
            st.close();
            conexion.close();*/
        } catch (Exception e) {
            System.out.println("Error de Conexion " + e.getMessage());
        }return conexion;
    }
}
