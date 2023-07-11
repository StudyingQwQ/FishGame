package element;

import manager.Pool;

import javax.swing.*;
import java.awt.*;

public class Bullet {
    public int x;
    public int y;
    public Point p;
    public ImageIcon imageIcon;
    public double route;
    Pool panel;
    public boolean isLive = true;
    public Bullet(Pool pool){
        this.panel = pool;
    }
    public void moving(){
        if(isLive){
            y -= 10;
            panel.repaint();
            if(y<=-100){
                isLive = false;
            }
        }
    }
    public void drawBullet(Graphics g){
        Graphics2D gp = (Graphics2D)g.create();
        gp.rotate(route,p.x,p.y);
        gp.drawImage(imageIcon.getImage(),x,y,panel);
    }
}

