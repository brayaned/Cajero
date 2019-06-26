package Main;

import cajero.Banco;
import cajero.Cliente;
import cajero.BD.Conexion;
import cajero.Cuenta;
import cajero.Tarjeta;
import cajero.Transaccion;
import cajero.Vista.Ventana;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Conexion conexion = new Conexion();
        Connection con = conexion.realziarConexion();
        PreparedStatement ps = null;
        Scanner sc = new Scanner(System.in);
        Banco bn = new Banco();
        Transaccion tr = new Transaccion();
        Tarjeta tj = new Tarjeta();
        Cuenta cn = new Cuenta();
        Cuenta cn2 = new Cuenta();
        Date date = new Date();
        Cliente cliente = new Cliente();
        Cliente cliente2 = new Cliente();

        String nCuenta = null, nCuenta1 = null;
        int clave, monto = 0, j = 0, h = 0;
        Ventana v = new Ventana();
        v.setVisible(true);
        /*
        while (h == 0) {
            System.out.println("Ingrese el numero de la tarjeta asociada a su cuenta");
            nCuenta = sc.nextLine();
            h = tr.verificarCuenta(j, cn, cliente, nCuenta);
            if (h == 0) {
                System.out.println("\nEste numero de tarjeta no pertenece a ninguna cuenta\n");
            }
        }
        System.out.println("Ingrese la opcion que desea realizar");
        System.out.println("1. Transaccion\n2. Consulta\n3. Retiro");
        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                h = 0;
                while (h == 0) {
                    System.out.println("Ingrese el numero de cuenta del destinatario:");
                    nCuenta1 = sc.nextLine();
                    h = tr.verificarCuenta(j, cn2, cliente2, nCuenta1);
                    if (h == 0) {
                        System.out.println("\nEste numero no pertenece a ninguna cuenta\n");
                    }
                }
                System.out.println("Ingrese el monto a transferir: ");
                monto = sc.nextInt();
                j = 0;
                while (j <= 3) {
                    if (j < 3) {
                        System.out.println("Ingrese la clave de usuario: ");
                        clave = sc.nextInt();
                        j = tr.verificarClave(j, nCuenta, clave);
                    } else {
                        System.out.println("¡EXCEDIDO EL NUMERO DE INTENTOS POSIBLES!");
                        System.exit(1);
                    }
                }
                int sal = tr.transferencia(nCuenta, nCuenta1, monto);
                tr.setNumeroCuenta(nCuenta);
                tr.setCosto(bn.getCostoTransaccion());
                tr.setDescripcion("Transferencia");
                DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String convertido = fecha.format(date);
                tr.setFecha(convertido);
                tr.setMonto(monto);
                tr.insertarDatos(tr);
                System.out.println("Imprimiendo factura...\n\n");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                System.out.println("Banco: " + bn.getNombreBanco());
                System.out.println("Numero de Cuenta: " + tr.getNumeroCuenta());
                System.out.println("Titular de la Cuenta: " + cn.getTitutar());
                System.out.println("Tipo de Transaccion: " + tr.getDescripcion());
                System.out.println("Cuenta destino: " + cn2.getNumeroCuenta());
                System.out.println("Titular de la Cuenta destino: " + cliente2.getNombre());
                System.out.println("Monto de la transaccion: " + tr.getMonto());
                System.out.println("costo de Transaccion: " + tr.getCosto());
                System.out.println("Saldo disponible: " + sal);
                System.out.println("Fecha: " + convertido);
                break;
            case 2:
                j = 0;
                while (j <= 5) {
                    if (j < 5) {
                        System.out.println("Ingrese la clave de usuario: ");
                        clave = sc.nextInt();
                        j = tr.verificarClave(j, nCuenta, clave);
                    } else {
                        System.out.println("¡EXCEDIDO EL NUMERO DE INTENTOS POSIBLES!");
                        System.exit(1);
                    }
                }
                System.out.println("Informacion de la Cuenta");
                System.out.println("Numero de Cuenta: " + cn.getNumeroCuenta());
                System.out.println("Saldo: " + cn.getSaldo());
                System.out.println("Titular de la Cuenta: " + cliente.getNombre() + " Con Cedula: " + cliente.getCedula());
                System.out.println("Informacion de las Transacciones:");
                tr.consultarDatos(nCuenta, cn.getTitutar());
                break;
            case 3:
                while (j == 0) {
                    System.out.println("Ingrese el monto a retirar: ");
                    monto = sc.nextInt();
                    if (monto > cn.getSaldo()) {
                        System.out.println("No fue posible realizar el retiro, saldo insuficiente ");
                    } else {
                        j = 1;
                    }
                }
                j = 0;
                while (j <= 3) {
                    if (j < 3) {
                        System.out.println("Ingrese la clave de usuario: ");
                        clave = sc.nextInt();
                        j = tr.verificarClave(j, nCuenta, clave);
                    } else {
                        System.out.println("¡EXCEDIDO EL NUMERO DE INTENTOS POSIBLES!");
                        System.exit(1);
                    }
                }
                tr.setNumeroCuenta(nCuenta);
                tr.setCosto(bn.getCostoTransaccion());
                tr.setDescripcion("Retiro de efectivo");
                fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                convertido = fecha.format(date);
                tr.setFecha(convertido);
                tr.setMonto(monto);
                tr.insertarDatos(tr);
                System.out.println("Imprimiendo factura...\n\n");
                sal = tr.actualizarDatos(nCuenta, monto);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                System.out.println("Banco: " + bn.getNombreBanco());
                System.out.println("Numero de Cuenta: " + tr.getNumeroCuenta());
                System.out.println("Titular de la Cuenta: " + cn.getTitutar());
                System.out.println("Tipo de Transaccion: " + tr.getDescripcion());
                System.out.println("Monto de la transaccion: " + tr.getMonto());
                System.out.println("costo de Transaccion: " + tr.getCosto());
                System.out.println("Saldo disponible: " + sal);
                System.out.println("Fecha: " + convertido);
                break;
        }*/

    }
}
