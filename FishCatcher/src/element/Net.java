package element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Net {
    public BufferedImage netImg;
    public int x;
    public int y;
    public boolean flag = false;
    public void getImg() throws IOException {
        File file = new File("source/image/net09.png");
        netImg = ImageIO.read(file);
    }
}
