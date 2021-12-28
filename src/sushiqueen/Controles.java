package sushiqueen;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Controles extends JFrame implements ActionListener {

    private JLabel mesa1;
    private JLabel mesa2;
    private JLabel labelMesa1;
    private JLabel labelCook1;
    private JLabel labelCook2;
    private JLabel labelC1;
    private JLabel labelC2;
    private JLabel labelC3;
    private JLabel labelC4;
    private JLabel labelC5;
    private JLabel labelC6;
    private JTextField vMesa1;
    private JTextField vCocinero1;
    private JTextField vCocinero2;
    private JTextField vCliente1;
    private JTextField vCliente2;
    private JTextField vCliente3;
    private JTextField vCliente4;
    private JTextField vCliente5;
    private JTextField vCliente6;
    private JButton bMesa1;
    private JButton bCook1;
    private JButton bCook2;
    private JButton bC1;
    private JButton bC2;
    private JButton bC3;
    private JButton bC4;
    private JButton bC5;
    private JButton bC6;
    private Mesa mesa;
    private Cocinero cocinero;
    private ArrayList<Cliente> clientes;

    public Controles(Mesa mesa, Cocinero cocinero, ArrayList clientes) {
        this.mesa = mesa;
        this.cocinero = cocinero;
        this.clientes = clientes;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        this.setContent(container);

        this.pack();
        this.setLocation(10, 50);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.bMesa1) {
            this.setVmesa1();
        } else if (ae.getSource() == this.bCook1) {
            this.setVcook1();
        }  else if (ae.getSource() == this.bC1) {
            this.setVc1();
        } else if (ae.getSource() == this.bC2) {
            this.setVc2();            
        } else if (ae.getSource() == this.bC3) {
            this.setVc3();            
        }
    }
    
    private void setVmesa1 () {
        int v = Integer.parseInt(this.vMesa1.getText());
        //this.mesa.setV(v);
    }
    
    private void setVcook1 () {
        int v = Integer.parseInt(this.vCocinero1.getText());
        this.cocinero.setV(v);
    }
    
    private void setVc1 () {
        int v = Integer.parseInt(this.vCliente1.getText());
        this.clientes.get(0).setV(v);
    }
    
    private void setVc2 () {
        int v = Integer.parseInt(this.vCliente2.getText());
        this.clientes.get(1).setV(v);        
    }
    
    private void setVc3 () {
        int v = Integer.parseInt(this.vCliente3.getText());
        this.clientes.get(2).setV(v);        
    }

    private void setContent(Container container) {
        JPanel panel = new JPanel();
        GridBagLayout grid = new GridBagLayout();
        panel.setLayout(grid);
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;

        Font font = new Font("Courier New", Font.BOLD, 25);
        
        this.mesa1 = new JLabel("MESA 1");
        this.mesa1.setFont(font);
        panel.add(mesa1, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.labelMesa1 = new JLabel("velocidad mesa 1");
        this.labelMesa1.setFont(font);
        panel.add(labelMesa1, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.vMesa1 = new JTextField("7000");
        this.vMesa1.setFont(font);
        panel.add(vMesa1, constraints);
        
        constraints.gridx = 2;
        constraints.gridy = 1;
        this.bMesa1 = new JButton("SET");
        this.bMesa1.setFont(font);
        this.bMesa1.addActionListener(this);
        panel.add(bMesa1, constraints);
        
        // ---------------------
        
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.labelCook1 = new JLabel("velocidad cocinero 1");
        this.labelCook1.setFont(font);
        panel.add(labelCook1, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.vCocinero1 = new JTextField("20");
        this.vCocinero1.setFont(font);
        panel.add(vCocinero1, constraints);
        
        constraints.gridx = 2;
        constraints.gridy = 2;
        this.bCook1 = new JButton("SET");
        this.bCook1.setFont(font);
        this.bCook1.addActionListener(this);
        panel.add(bCook1, constraints);
        
        // ---------------------
        
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.labelC1 = new JLabel("velocidad cliente 1");
        this.labelC1.setFont(font);
        panel.add(labelC1, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 3;
        this.vCliente1 = new JTextField("8");
        this.vCliente1.setFont(font);
        panel.add(vCliente1, constraints);
        
        constraints.gridx = 2;
        constraints.gridy = 3;
        this.bC1 = new JButton("SET");
        this.bC1.setFont(font);
        this.bC1.addActionListener(this);
        panel.add(bC1, constraints);
        
        // ---------------------
        
        constraints.gridx = 0;
        constraints.gridy = 4;
        this.labelC2 = new JLabel("velocidad cliente 2");
        this.labelC2.setFont(font);
        panel.add(labelC2, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 4;
        this.vCliente2 = new JTextField("20");
        this.vCliente2.setFont(font);
        panel.add(vCliente2, constraints);
        
        constraints.gridx = 2;
        constraints.gridy = 4;
        this.bC2 = new JButton("SET");
        this.bC2.setFont(font);
        this.bC2.addActionListener(this);
        panel.add(bC2, constraints);
        
        // ---------------------
        
        constraints.gridx = 0;
        constraints.gridy = 5;
        this.labelC3 = new JLabel("velocidad cliente 3");
        this.labelC3.setFont(font);
        panel.add(labelC3, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 5;
        this.vCliente3 = new JTextField("10");
        this.vCliente3.setFont(font);
        panel.add(vCliente3, constraints);
        
        constraints.gridx = 2;
        constraints.gridy = 5;
        this.bC3 = new JButton("SET");
        this.bC3.setFont(font);
        this.bC3.addActionListener(this);
        panel.add(bC3, constraints);
        
        // ---------------------
        
        container.add(panel);
    }

}
