package sushiqueen;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_3BYTE_BGR;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;

public class Viewer extends Canvas implements Runnable {

    private BufferedImage floor;
    private BufferedImage frame;
    private Mesa mesa;
    private ArrayList<Cliente> clientes;
    private ArrayList<Cocinero> cocineros;
    private Graphics frameGraphics;

    public Graphics getFrameGraphics() {
        return frame.getGraphics();
    }

    public Viewer(Mesa mesa, ArrayList cocineros, ArrayList clientes) {
        this.mesa = mesa;
        this.cocineros = cocineros;
        this.clientes = clientes;
        this.loadImgs();
        this.frameGraphics = this.frame.getGraphics();
        this.setSize(this.floor.getWidth(), this.floor.getHeight());
    }

    private void loadImgs() {
        try {
            this.floor = ImageIO.read(new File("floor2.jpg"));
            //this.frame = ImageIO.read(new File("floor2.jpg"));
            this.frame  = new BufferedImage(floor.getWidth(), floor.getHeight(), TYPE_3BYTE_BGR);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
    
    private void pintarFrame() {
            this.frameGraphics.drawImage(floor, 0, 0, this);
            this.mesa.pintar(frameGraphics);
            
            Iterator<Cocinero> it1 = cocineros.iterator();
            while (it1.hasNext()) {
                it1.next().pintar(frameGraphics);
            }

            Iterator<Cliente> it2 = clientes.iterator();
            while (it2.hasNext()) {
                it2.next().pintar(frameGraphics);
            }
            
            //this.paint(this.getGraphics());
            this.getGraphics().drawImage(frame, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        //g.drawImage(frame, 0, 0, this);
    }

    @Override
    public void run() {
        while (true) {
            this.pintarFrame();
        }
    }

}
