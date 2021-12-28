package sushiqueen;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Cocinero implements Runnable, Drawable {

    private Mesa mesa;
    private BufferedImage chefServing;
    private BufferedImage chefWaiting;
    private BufferedImage chefCooking;
    private boolean cooking;
    private boolean llevando;
    private int x;
    private int y;
    private int v;
    private int motivacion;

    public Cocinero(Mesa mesa, int x, int y, int v, int motivacion) {
        this.mesa = mesa;
        this.x = x;
        this.y = y;
        this.v = v;
        this.motivacion = motivacion;
        this.loadImgs();
        this.cooking = false;
        this.llevando = false;
    }

    private void loadImgs() {
        try {
            this.chefServing = ImageIO.read(new File("chefServing.png"));
            this.chefWaiting = ImageIO.read(new File("chefWaiting.png"));
            this.chefCooking = ImageIO.read(new File("chefCooking.png"));
        } catch (IOException e) {
            System.out.println("File not found");
        }

    }

    public void trabajar() {
        Thread hilo = Thread.currentThread();
        this.prepararSushi();
        this.llevarSushi();
        this.mesa.ponerseEnColaCocinar(hilo);
        this.mesa.dejarSushi(hilo);
        this.mesa.quitarseDeLaColaCocinar(hilo);
        this.volver();
    }

    public void setV(int v) {
        this.v = v;
    }

    private void prepararSushi() {
        this.cooking = true;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
    }

    private void llevarSushi() {
        this.cooking = false;
        this.llevando = true;
        while (y < 180) {
            try {
                Thread.sleep(300);
                this.cooking = false;
            } catch (InterruptedException e) {
            }

            y += v;
        }
    }

    private void volver() {
        this.llevando = false;
        while (y > 50) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }

            y -= v;
        }
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(motivacion);
            } catch (InterruptedException e) {
            }
            trabajar();
        }
    }

    @Override
    public void pintar(Graphics g) {
        BufferedImage img;
        if (cooking) {
            img = this.chefCooking;
        } else if (llevando) {
            img = this.chefServing;
        } else {
            img = this.chefWaiting;
        }

        g.drawImage(img, x, y, null);
    }

}
