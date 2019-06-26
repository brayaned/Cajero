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

public class Ventana extends JFrame {

    public JPanel panel;
    JLabel e1 = new JLabel();
    JLabel e2 = new JLabel();
    JLabel e3 = new JLabel();
    JTextField t1 = new JTextField();

    public String getNc() {
        return nc;
    }
    JButton b1 = new JButton("Ingresar");
    String nc ;

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

    public Ventana() {
        setSize(500, 500);
        setTitle("Cajero");
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(500, 500));
        iniciarComponentes();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        e1.setText("Cajero");
        e1.setBounds(165, 60, 200, 100);
        e1.setHorizontalAlignment(SwingConstants.LEFT);
        e1.setFont(new Font("Impact", Font.BOLD, 50));
        panel.add(e1);

        e2.setText("Numero Cuenta:");
        e2.setBounds(120, 200, 100, 50);
        e2.setHorizontalAlignment(SwingConstants.LEFT);
        e2.setFont(new Font("Impact", Font.PLAIN, 15));
        panel.add(e2);
        
        
        e3.setBounds(100, 350, 400, 50);
        e3.setForeground(Color.red);
        e3.setHorizontalAlignment(SwingConstants.LEFT);
        e3.setFont(new Font("Impact", Font.PLAIN, 15));
        panel.add(e3);

    }

    private void colocarCampos() {

        t1.setBounds(230, 215, 100, 20);
        panel.add(t1);
    }

    private void colocarBotones() {

        b1.setBounds(190, 300, 130, 50);
        b1.setFont(new Font("Impact", Font.PLAIN, 20));
        panel.add(b1);

        ActionListener ingresar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                nc = t1.getText();
                int h = 0;
                h = tr.verificarCuenta(h, cn, cliente, nc);
                if(h!=0){
                    InterfazMenu im=new InterfazMenu(nc);
                    setVisible(false);
                    im.setVisible(true);
                }else{
                    e3.setText("Este numero de tarjeta no pertenece a ninguna cuenta");
                }
            }
        };
        b1.addActionListener(ingresar);

    }

}
