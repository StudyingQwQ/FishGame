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
        File file = new File("source/image/net/net_" + (level%5+1) + ".png");
        try{
            netImg = ImageIO.read(file);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
