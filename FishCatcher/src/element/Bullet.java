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
    private int[] speed = {5, 10, 15, 20, 25};

    public Bullet(Pool pool){
        this.panel = pool;
    }
    public void moving(){
        if(isLive){
            y -= speed[(panel.net.level-1)%5];//子弹移动速度
            panel.repaint();
            if(y<=panel.finY){//子弹消失位置
                isLive = false;
            }
        }
    }
    public void drawBullet(Graphics g){
//        System.out.printf(panel.finY +"\n");
        Graphics2D gp = (Graphics2D)g.create();
        gp.rotate(route,p.x,p.y);
        gp.drawImage(imageIcon.getImage(),x,y,panel);
    }
}

