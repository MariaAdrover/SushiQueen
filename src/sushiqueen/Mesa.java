package sushiqueen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Mesa implements Drawable {

    private int sushis;
    private BufferedImage sushiImg;
    private int x;
    private int y;
    private ArrayList<Thread> colaClientes;
    private ArrayList<Thread> colaCocineros;
    private boolean actualizandoArray;

    public Mesa(int x, int y) {
        this.x = x;
        this.y = y;
        this.sushis = 0;
        this.loadSushiImg();
        actualizandoArray = false;
        this.colaClientes = new ArrayList();
        this.colaCocineros = new ArrayList();
    }

    public synchronized void ponerseEnColaCocinar(Thread hilo) {
        while (this.actualizandoArray) {
            try {
                wait();
            } catch (InterruptedException e) {
                
            }
        }
        
        this.colaCocineros.add(hilo);
        
        this.actualizandoArray = false;
        notifyAll();
    }
    
    public void quitarseDeLaColaCocinar(Thread hilo) {
        this.colaCocineros.remove(0);
    }

    public synchronized void ponerseEnColaComer(Thread hilo) {
        while (this.actualizandoArray) {
            try {
                wait();
            } catch (InterruptedException e) {
                
            }
        }
        
        this.colaClientes.add(hilo);
        
        this.actualizandoArray = false;
        notifyAll();
    }
    
    public void quitarseDeLaCola(Thread hilo) {
        this.colaClientes.remove(0);
    }

    private void loadSushiImg() {
        try {
            this.sushiImg = ImageIO.read(new File("littleSushi_60.png"));
        } catch (IOException e) {
            System.out.println("File not found");
        }

    }

    public synchronized void cogerSushi(Thread hilo) {
        while (sushis < 1 || (this.colaClientes.indexOf(hilo)) > 0) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        sushis--;
        notifyAll();
    }

    public synchronized void dejarSushi(Thread hilo) {
        while (sushis > 2 || (this.colaCocineros.indexOf(hilo)) > 0) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        sushis++;
        notifyAll();
    }

    @Override
    public void pintar(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(this.x, this.y, 300, 100);

        int sushiX = this.x + 20;
        int sushiY = this.y + 20;

        for (int i = 0; i < this.sushis; i++) {
            g.drawImage(sushiImg, sushiX, sushiY, null);
            sushiX += 100;
        }
    }

}
