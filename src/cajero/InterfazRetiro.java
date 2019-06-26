package cajero;

import java.awt.*;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Scanner;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class InterfazRetiro extends JFrame {

    public JPanel panel;
    JLabel e1 = new JLabel();
    JLabel e2 = new JLabel();
    JTextArea ta = new JTextArea();
    JTextField t1 = new JTextField();
    JButton b1 = new JButton("400.000");
    JButton b4 = new JButton("200.000");
    JButton b5 = new JButton("100.000");
    JButton b6 = new JButton("50.000");
    JButton b7 = new JButton("20.000");
    JButton b8 = new JButton("Otro");
    JButton b2 = new JButton("Atras");
    JButton b3 = new JButton("Finalizar");
    String nc;
    int monto;
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

    public InterfazRetiro(String nC, Cliente cl, Cuenta cuenta) {
        setSize(700, 500);
        setTitle("Retiro");
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(700, 500));
        iniciarComponentes();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        nc = nC;
        cliente = cl;
        cn = cuenta;
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

        e1.setText("Ingrese Monto");
        e1.setBounds(250, 80, 200, 40);
        e1.setHorizontalAlignment(SwingConstants.LEFT);
        e1.setFont(new Font("Impact", Font.BOLD, 25));
        panel.add(e1);

        e2.setText("");
        e2.setBounds(80, 200, 100, 50);
        e2.setHorizontalAlignment(SwingConstants.LEFT);
        e2.setFont(new Font("Impact", Font.PLAIN, 15));
        panel.add(e2);

        ta.setBounds(300, 100, 300, 300);
        ta.setFont(new Font("Impact", Font.PLAIN, 15));

    }

    private void colocarCampos() {

        t1.setBounds(350, 260, 100, 30);
        panel.add(t1);
    }

    private void colocarBotones() {

        b1.setBounds(80, 140, 120, 30);
        b1.setFont(new Font("Impact", Font.PLAIN, 20));
        panel.add(b1);

        b4.setBounds(80, 200, 120, 30);
        b4.setFont(new Font("Impact", Font.PLAIN, 20));
        panel.add(b4);

        b5.setBounds(80, 260, 120, 30);
        b5.setFont(new Font("Impact", Font.PLAIN, 20));
        panel.add(b5);

        b6.setBounds(450, 140, 120, 30);
        b6.setFont(new Font("Impact", Font.PLAIN, 20));
        panel.add(b6);

        b7.setBounds(450, 200, 120, 30);
        b7.setFont(new Font("Impact", Font.PLAIN, 20));
        panel.add(b7);

        b8.setBounds(450, 260, 120, 30);
        b8.setFont(new Font("Impact", Font.PLAIN, 20));
        panel.add(b8);

        b2.setBounds(80, 430, 100, 25);
        b2.setFont(new Font("Impact", Font.PLAIN, 18));
        panel.add(b2);

        b3.setBounds(550, 430, 100, 25);
        b3.setFont(new Font("Impact", Font.PLAIN, 18));
        panel.add(b3);

        ActionListener br1 = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                monto = 400000;
                InterfazClave ic = new InterfazClave(nc, monto);
                setVisible(false);
                ic.setVisible(true);
            }
        };
        b1.addActionListener(br1);

        ActionListener br4 = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                monto = 200000;
                InterfazClave ic = new InterfazClave(nc, monto);
                setVisible(false);
                ic.setVisible(true);

            }
        };
        b4.addActionListener(br4);

        ActionListener br5 = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                monto = 100000;
                InterfazClave ic = new InterfazClave(nc, monto);
                setVisible(false);
                ic.setVisible(true);
            }
        };
        b5.addActionListener(br5);

        ActionListener br6 = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                monto = 50000;
                InterfazClave ic = new InterfazClave(nc, monto);
                setVisible(false);
                ic.setVisible(true);
            }
        };
        b6.addActionListener(br6);

        ActionListener br7 = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                monto = 20000;
                InterfazClave ic = new InterfazClave(nc, monto);
                setVisible(false);
                ic.setVisible(true);
            }
        };
        b7.addActionListener(br7);

        ActionListener br8 = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                monto = Integer.parseInt(t1.getText());
                if (monto > cn.getSaldo()-5000) {
                    JOptionPane.showMessageDialog(null, "No fue posible realizar el retiro, saldo insuficiente ");
                } else {
                    InterfazClave ic = new InterfazClave(nc, monto);
                    setVisible(false);
                    ic.setVisible(true);
                }
            }
        };
        b8.addActionListener(br8);

        ActionListener atras = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
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
