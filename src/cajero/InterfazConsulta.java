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

public class InterfazConsulta extends JFrame {

    public JPanel panel;
    JLabel e1 = new JLabel();
    JLabel e2 = new JLabel();
    JTextArea ta=new JTextArea();
    JTextField t1 = new JTextField();
    JButton b1 = new JButton("Verificar");
    JButton b2 = new JButton("Atras");
    JButton b3 = new JButton("Finalizar");
    String nc;
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

    public InterfazConsulta(String nC) {
        setSize(700, 500);
        setTitle("Consulta");
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(700, 500));
        iniciarComponentes();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        nc=nC;
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
        
        ta.setBounds(300,100,300,300);
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

        ActionListener ingresar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int clave;
                int j = 0;
                Ventana v=new Ventana();
                
                while (j <= 5) {
                    if (j < 5) {
                       
                        
                        clave = Integer.parseInt(t1.getText());
                        
                        j = tr.verificarClave(j, nc, clave);
                    } else {
                        e2.setText("¡EXCEDIDO EL NUMERO DE INTENTOS POSIBLES!");
                        System.exit(1);
                    }
                }
                tr.consultarDatos(nc, cn.getTitutar());
                String resul=("Informacion de la Cuenta \n Numero de Cuenta: " + cn.getNumeroCuenta()+"\n"+"Saldo: "+cn.getSaldo()+"\nTitular de la Cuenta: "+cliente.getNombre() + "Cedula: " + cliente.getCedula()+"\n Informacion de las Transacciones:");
                ta.setText(resul+"\n"+tr.consultarDatos(nc, cn.getTitutar()));
                t1.setText(null);
                /*
                ta.setText("Informacion de la Cuenta");
                ta.setText("Numero de Cuenta: " + cn.getNumeroCuenta());
                ta.setText("Saldo: " + cn.getSaldo());
                ta.setText("Titular de la Cuenta: " + cliente.getNombre() + " Con Cedula: " + cliente.getCedula());
                ta.setText("Informacion de las Transacciones:");*/
                //tr.consultarDatos(nc, cn.getTitutar());
                
                
            }
        };
        b1.addActionListener(ingresar);
        
        ActionListener atras=new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                InterfazMenu im=new InterfazMenu(nc);
                im.setVisible(true);
                
            }
        };
        b2.addActionListener(atras);
        
        
        ActionListener finalizar=new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        b3.addActionListener(finalizar);
    }

}
