package cajero;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InterfazMenu extends JFrame {

    JPanel panel;
    JButton bconsultar = new JButton();
    JButton bretirar = new JButton();
    JButton btransfer = new JButton();
    JButton b2 = new JButton();
    JButton b3 = new JButton();
    String nc;

    public InterfazMenu(String nC) {
        setSize(700, 500);
        setTitle("Cajero- Menu");
        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        iniciarComponentes();
        nc = nC;
    }

    private void iniciarComponentes() {
        iniciarPaneles();
        iniciarBotones();
        iniciarLabels();

    }

    private void iniciarPaneles() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_GRAY);
        this.getContentPane().add(panel);

    }

    private void iniciarBotones() {

        bconsultar.setBounds(80, 300, 125, 50);
        bconsultar.setText("Consultar");
        bconsultar.setFont(new Font("Impact", Font.PLAIN, 15));
        panel.add(bconsultar);

        bretirar.setBounds(280, 300, 125, 50);
        bretirar.setText("Retirar");
        bretirar.setFont(new Font("Impact", Font.PLAIN, 15));
        panel.add(bretirar);

        btransfer.setBounds(480, 300, 125, 50);
        btransfer.setText("Transferencia");
        btransfer.setFont(new Font("Impact", Font.PLAIN, 15));
        panel.add(btransfer);
        
        
        b2.setText("Atras");
        b2.setBounds(80, 400, 100, 25);
        b2.setFont(new Font("Impact", Font.PLAIN, 18));
        panel.add(b2);

        b3.setText("Finalizar");
        b3.setBounds(550, 400, 100, 25);
        b3.setFont(new Font("Impact", Font.PLAIN, 18));
        panel.add(b3);

        ActionListener alconsultar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazConsulta ic = new InterfazConsulta(nc);
                setVisible(false);
                ic.setVisible(true);
            }
        };
        bconsultar.addActionListener(alconsultar);

        ActionListener alretirar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazRetiro ir = new InterfazRetiro(nc);
                setVisible(false);
                ir.setVisible(true);

            }
        };
        bretirar.addActionListener(alretirar);

        ActionListener altransfer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazTransfer it= new InterfazTransfer(nc);
                it.setVisible(true);                setVisible(false);
            }
        };
        btransfer.addActionListener(altransfer);

        ActionListener atras = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent q) {
                setVisible(false);
                Ventana im = new Ventana();
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

    private void iniciarLabels() {

        JLabel labelc = new JLabel();
        labelc.setBounds(250, 100, 120, 20);
        labelc.setText("Cliente:");
        labelc.setFont(new Font("Impact", Font.BOLD, 20));
        panel.add(labelc);
        Cliente c=new Cliente();
        JLabel cliente = new JLabel();
        cliente.setBounds(250, 120, 120, 20);
        cliente.setText(c.getNombre());
        cliente.setFont(new Font("Impact", Font.BOLD, 20));
        panel.add(cliente);

        JLabel labelcu = new JLabel();
        labelcu.setBounds(250, 140, 120, 20);
        labelcu.setText("No. Cuenta");
        labelcu.setFont(new Font("Impact", Font.BOLD, 20));
        panel.add(labelcu);
        Cuenta cu=new Cuenta();
        JLabel cuenta = new JLabel();
        cuenta.setBounds(250, 160, 120, 20);
        cuenta.setText(cu.getNumeroCuenta());
        cuenta.setFont(new Font("Impact", Font.BOLD, 20));
        panel.add(cuenta);

    }

}
