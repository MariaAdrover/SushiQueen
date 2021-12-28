package sushiqueen;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Cliente implements Runnable, Drawable {

    private Mesa mesa;
    private BufferedImage cliente;
    private BufferedImage clienteConComida;
    private BufferedImage clienteEnfadado;
    private BufferedImage clienteFumando;

    private int x;
    private int y;
    private int v;
    private boolean tieneComida;
    private boolean waiting;
    private boolean angry;
    private boolean fumando;

    private long llegada;
    private long esperando;

    public Cliente(Mesa mesa, int x, int y, int v, String path, String path2, String path3, String path4) {
        this.mesa = mesa;
        this.x = x;
        this.y = y;
        this.v = v;
        this.loadImgs(path, path2, path3, path4);
        this.tieneComida = false;
        this.waiting = false;
        this.angry = false;
        this.fumando = false;
    }

    public void setV(int v) {
        this.v = v;
    }

    private void loadImgs(String path, String path2, String path3, String path4) {
        try {
            this.cliente = ImageIO.read(new File(path));
            this.clienteConComida = ImageIO.read(new File(path2));
            this.clienteEnfadado = ImageIO.read(new File(path3));
            this.clienteFumando = ImageIO.read(new File(path4));
        } catch (IOException e) {
            System.out.println("File not found");
        }

    }

    private void buscarComida() {
        while (y > 340) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }

            y -= v;
        }
    }

    private void volver() {
        while (y < 650) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }

            y += v;
        }
    }

    public void cogerComida() {
        Thread hilo = Thread.currentThread();
        this.llegada = System.currentTimeMillis();
        this.waiting = true;
        this.mesa.ponerseEnColaComer(hilo);
        this.mesa.cogerSushi(hilo);
        this.mesa.quitarseDeLaCola(hilo);
        this.tieneComida = true;
        this.waiting = false;
        this.angry = false;
    }

    public void zampar() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        tieneComida = false;
    }

    public void reposarComida() {
        this.fumando = true;
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
        }
        this.fumando = false;
    }

    public void run() {
        while (true) {
            this.buscarComida();
            this.cogerComida();
            this.volver();
            this.zampar();
            this.reposarComida();
        }
    }

    @Override
    public void pintar(Graphics g) {
        BufferedImage img;

        if (this.waiting) {
            this.esperando = System.currentTimeMillis() - this.llegada;
            if (this.esperando > 2000) {
                this.angry = true;
            }
        }

        if (tieneComida) {
            img = clienteConComida;
        } else if (angry) {
            img = clienteEnfadado;
        } else if (fumando) {
            img = clienteFumando;
        } else {
            img = cliente;
        }

        g.drawImage(img, x, y, null);
    }

}
