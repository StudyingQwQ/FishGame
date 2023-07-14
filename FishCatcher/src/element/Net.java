package element;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Net {
    public BufferedImage netImg;
    public int x;
    public int y;
    public int level =1;//网和炮台的等级
    public boolean flag = false;
    public void getImg() {
        File file = new File("source/image/net/net_1.png");
        try{
            netImg = ImageIO.read(file);
        }catch(IOException e){
            e.printStackTrace();
        }
//        netImg = ImageIO.read(file);
    }
    public void changeNet() {
        level++;
        if(level>5){
            level=1;
        }
        File file = new File("source/image/net/net_" + level + ".png");
        try{
            netImg = ImageIO.read(file);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void repaintNet(){
        level--;
        if(level<1){
            level=5;
        }
        File file = new File("source/image/net/net_" + level + ".png");
        try{
            netImg = ImageIO.read(file);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
