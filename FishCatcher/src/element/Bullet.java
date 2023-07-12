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
    Net net=new Net();
    Pool pool=new Pool();
    public Bullet(Pool pool){
        this.panel = pool;
    }
    public void moving(){
        if(isLive){
            y -= 15;//子弹移动速度(本来想做成根据炮台等级改变速度的，试了几次搞不定，之后再说吧）
            panel.repaint();
            if(y<=-100){
                isLive = false;
            }
        }
    }
    public void drawBullet(Graphics g){
//        System.out.println("test");
//        System.out.printf(pool.finY +"\n");
        Graphics2D gp = (Graphics2D)g.create();
        gp.rotate(route,p.x,p.y);
        gp.drawImage(imageIcon.getImage(),x,y,panel);
    }
}

