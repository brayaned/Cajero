
package cajero;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InterfazMenu extends JFrame{
    JPanel panel;
    JButton bconsultar = new JButton();
    JButton bretirar = new JButton();
    JButton btransfer = new JButton();
    String nc;
    
    public InterfazMenu(String nC){
     setSize(700,500);
     setTitle("Cajero- Menu");
     setLocationRelativeTo(null);
     
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     iniciarComponentes();
     nc=nC;
    }
    
    private void iniciarComponentes(){
        iniciarPaneles();
        iniciarBotones();
        iniciarLabels();
        
    }
    
    private void iniciarPaneles(){
        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_GRAY);
        this.getContentPane().add(panel);
        
    }
    private void iniciarBotones(){
        
        bconsultar.setBounds(80,300,125,50);
        bconsultar.setText("Consultar");
        bconsultar.setFont(new Font("Impact",Font.PLAIN,15));
        panel.add(bconsultar);
        
        
        bretirar.setBounds(280,300,125,50);
        bretirar.setText("Retirar");
        bretirar.setFont(new Font("Impact",Font.PLAIN,15));
        panel.add(bretirar);
        
        
        btransfer.setBounds(480,300,125,50);
        btransfer.setText("Transferencia");
        btransfer.setFont(new Font("Impact",Font.PLAIN,15));
        panel.add(btransfer);
        
        ActionListener alconsultar=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            InterfazConsulta ic=new InterfazConsulta(nc);
            setVisible(false);
            ic.setVisible(true);
        }
        };
        bconsultar.addActionListener(alconsultar);
        
        ActionListener alretirar=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazRetiro ir=new InterfazRetiro(nc);
                setVisible(false);
                ir.setVisible(true);
                
            }
        };
        bretirar.addActionListener(alretirar);
        
        ActionListener altransfer=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        };
        btransfer.addActionListener(altransfer);
    
    }
    
    private void iniciarLabels(){
        
        JLabel labelc=new JLabel();
        labelc.setBounds(250, 100, 120, 20);
        labelc.setText("Cliente:");
        labelc.setFont(new Font("Impact",Font.BOLD,20));
        panel.add(labelc);
        
        JLabel cliente=new JLabel();
        cliente.setBounds(250, 120, 120, 20);
        cliente.setText("-");
        cliente.setFont(new Font("Impact",Font.BOLD,20));
        panel.add(cliente);
        
        JLabel labelcu=new JLabel();
        labelcu.setBounds(250, 140, 120, 20);
        labelcu.setText("No. Cuenta");
        labelcu.setFont(new Font("Impact",Font.BOLD,20));
        panel.add(labelcu);
        
        JLabel cuenta=new JLabel();
        cuenta.setBounds(250, 160, 120, 20);
        cuenta.setText("-");
        cuenta.setFont(new Font("Impact",Font.BOLD,20));
        panel.add(cuenta);
        
    }
    
    
    
}