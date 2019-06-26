package cajero.Vista;

import cajero.Banco;
import cajero.Cliente;
import cajero.BD.Conexion;
import cajero.Cuenta;
import cajero.Tarjeta;
import cajero.Transaccion;
import java.awt.*;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class InterfazTClave extends JFrame {

    public JPanel panel;
    JLabel e1 = new JLabel();
    JLabel e2 = new JLabel();
    JTextArea ta = new JTextArea();
    JTextField t1 = new JTextField();
    JButton b1 = new JButton("Verificar");
    JButton b2 = new JButton("Atras");
    JButton b3 = new JButton("Finalizar");
    String nc, nc1;

    int monto, j = 0, clave;
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
    Cliente cl = new Cliente();
    

    public InterfazTClave(String nC, String nC1, int m) {
        setSize(700, 500);
        setTitle("Consulta");
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(700, 500));
        iniciarComponentes();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        nc = nC;
        nc1 = nC1;
        monto = m;
    }

    private void iniciarComponentes() {
        colocarPaneles();
        colocarEtiquetas();
        colocarBotones();
        colocarCampos();
    }

    private void colocarPaneles() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_GRAY);
        this.getContentPane().add(panel);
    }

    private void colocarEtiquetas() {

        e1.setText("Ingrese Clave");
        e1.setBounds(80, 80, 200, 30);
        e1.setHorizontalAlignment(SwingConstants.LEFT);
        e1.setFont(new Font("Impact", Font.BOLD, 20));
        panel.add(e1);

        e2.setText("");
        e2.setBounds(80, 200, 100, 50);
        e2.setHorizontalAlignment(SwingConstants.LEFT);
        e2.setFont(new Font("Impact", Font.PLAIN, 15));
        panel.add(e2);

        ta.setBounds(300, 100, 300, 300);
        ta.setFont(new Font("Impact", Font.PLAIN, 15));
        panel.add(ta);

    }

    private void colocarCampos() {

        t1.setBounds(80, 140, 100, 30);
        panel.add(t1);
    }

    private void colocarBotones() {

        b1.setBounds(80, 255, 120, 30);
        b1.setFont(new Font("Impact", Font.PLAIN, 20));
        panel.add(b1);

        b2.setBounds(80, 430, 100, 25);
        b2.setFont(new Font("Impact", Font.PLAIN, 18));
        panel.add(b2);

        b3.setBounds(550, 430, 100, 25);
        b3.setFont(new Font("Impact", Font.PLAIN, 18));
        panel.add(b3);

        ActionListener ingresar;
        ingresar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent q) {
                try {
                    if (monto > cn.getSaldo()) {
                        e2.setText("No fue posible realizar el retiro, saldo insuficiente ");
                    } else {
                        j = 1;
                    }

                    j = 0;
                    while (j <= 3) {
                        if (j < 3) {

                            clave = Integer.parseInt(t1.getText());
                            j = tr.verificarClave(j, nc, clave);
                        } else {
                            e2.setText("¡EXCEDIDO EL NUMERO DE INTENTOS POSIBLES!");

                        }
                    }

                    int sal = tr.transferencia(nc, nc1, monto);
                    tr.setNumeroCuenta(nc);
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
                    String r;
                    r="\nBanco: " + bn.getNombreBanco()+
                    "\nNumero de Cuenta: " + tr.getNumeroCuenta()+
                    "\nTitular de la Cuenta: " + cn.getTitutar()+
                    "\nTipo de Transaccion: " + tr.getDescripcion()+
                    "\nCuenta destino: " + cn2.getNumeroCuenta()+
                    "\nTitular de la Cuenta destino: " + cliente2.getNombre()+
                    "\nMonto de la transaccion: " + tr.getMonto()+
                    "\ncosto de Transaccion: " + tr.getCosto()+
                    "\nSaldo disponible: " + sal+
                    "\nFecha: " + convertido;
                    ta.setText(r);
                } catch (SQLException ex) {
                    Logger.getLogger(InterfazClave.class.getName()).log(Level.SEVERE, null, ex);

                }

            }
        };
        b1.addActionListener(ingresar);

        ActionListener atras = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent q) {
                setVisible(false);
                InterfazMenu im = new InterfazMenu(nc, cliente, cn);
                im.setVisible(true);

            }
        };
        b2.addActionListener(atras);

        ActionListener finalizar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        b3.addActionListener(finalizar);
    }

}
