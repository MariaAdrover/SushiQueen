package sushiqueen;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class SushiQueen extends JFrame {

    private ArrayList<Cliente> clientes;
    private ArrayList<Cocinero> cocineros;
    private Mesa mesa;
    private Viewer viewer;
    private Controles controles;

    public SushiQueen() {
        this.setTitle("SushiQueen Chachi Restaurant");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(500, 50);       
        
        int xMesa1 = 50;
        int yMesa1 = 300;
        this.mesa = new Mesa(xMesa1, yMesa1);
        
        this.cocineros = new ArrayList();
        this.cocineros.add(new Cocinero(mesa, 100, 50, 10, 3000));
        this.cocineros.add(new Cocinero(mesa, 200, 50, 30, 2000));
        
        this.clientes = new ArrayList();
        this.clientes.add(new Cliente(mesa, 60, 650, 8, "hipster1.png", "hipster2.png", "hipster3.png", "hipster4.png"));
        this.clientes.add(new Cliente(mesa, 155, 650, 20, "pig.png", "pig2.png", "pig3.png", "pig4.png"));
        this.clientes.add(new Cliente(mesa, 250, 650, 10, "architect1.png", "architect2.png", "architect3.png", "architect4.png"));
        
        //this.controles = new Controles(mesa, cocinero, clientes);

        this.viewer = new Viewer(this.mesa, this.cocineros, this.clientes);
        Container c = this.getContentPane();
        c.add(viewer);
        this.pack();
    }

    public void startAnimation() {
        (new Thread(this.viewer)).start();
        
        Iterator<Cocinero> it1 = cocineros.iterator();
        while (it1.hasNext()) {
            (new Thread(it1.next())).start();
            
        }
        
        Iterator<Cliente> it2 = clientes.iterator();
        while (it2.hasNext()) {
            (new Thread(it2.next())).start();
            
        }
    }

    public static void main(String[] args) {
        SushiQueen restaurante = new SushiQueen();
        restaurante.startAnimation();
        restaurante.setVisible(true);
    }

}
