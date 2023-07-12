package element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pt {
    Net net=new Net();
    public int level;
    public BufferedImage ptImg;
    public void getPtImg() throws IOException {
        File file = new File("source/image/pt/pt_1.png");
//        sub=new JButton(new ImageIcon("source/image/sub.png"));
//        add=new JButton(new ImageIcon("source/image/add.png"));
        ptImg = ImageIO.read(file);
    }
    public void addPt(){
        net.level++;
        level=net.level;
        if(level>5){
            level=1;
            net.level=1;
        }
        File file = new File("source/image/pt/pt_" + level+ ".png");
        try{
            ptImg = ImageIO.read(file);
        }catch (IOException e1){
            e1.printStackTrace();
        }
    }
    public void subPt(){
        net.level--;
        level=net.level;
        if(level<1){
            level=5;
            net.level=5;
        }
        File file = new File("source/image/pt/pt_" + level+ ".png");
        try{
            ptImg = ImageIO.read(file);
        }catch (IOException e1){
            e1.printStackTrace();
        }
    }
}
