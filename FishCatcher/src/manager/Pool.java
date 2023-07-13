package manager;

import controller.BulletThread;
import element.Bullet;
import element.Fish;
import element.Net;
import element.Pt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Pool extends JPanel {
    BufferedImage poolImg;

    Fish[] allFish = new Fish[29];

    public Net net = new Net();
    Pt pt=new Pt();

    private int score = 0;
    private int temp = 0;

    BufferedImage fstImg;
    BufferedImage ptImg;

    int mouseX = 0;
    int mouseY = 0;
    int ptX = 405;
    int ptY = 400;
    public int finY;
    double angle;
    public JButton leftButton;
    public JButton rightButton;
    private Timer timer= new Timer();;//得分动画显示计时器
    private boolean showImage = false;
    public int level=1;//炮台和渔网共用等级(暂时没有实现，在点击切换炮台的两个按钮的时候渔网大小不会变化)
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    public Pool(){
        setDoubleBuffered(true);
    }

    public void getImg() throws IOException {
        getPoolImg();
        getFstImg();
        pt.getPtImg();
        getFishImg();
        getNetImg();
    }

//    private void getPtImg() throws IOException {
//        File file = new File("source/image/pt/pt_1.png");
////        sub=new JButton(new ImageIcon("source/image/sub.png"));
////        add=new JButton(new ImageIcon("source/image/add.png"));
//        ptImg = ImageIO.read(file);
//    }

    private void getFstImg() throws IOException {
        File file = new File("source/image/fst.png");
        fstImg = ImageIO.read(file);
    }

    private void getNetImg() throws IOException {
        net.getImg();
    }

    private void getFishImg() throws IOException {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                Fish fish = new Fish();
                fish.getImg("fish0" + (j + 1));
                allFish[count++] = fish;
            }
        }
        Fish fish27 = new Fish();
        fish27.getImg("fish13");
        Fish fish28 = new Fish();
        fish28.getImg("fish14");
        allFish[count++] = fish27;
        allFish[count++] = fish28;
    }

    private void getPoolImg() throws IOException {
        File file = new File("source/image/bg.jpg");
        poolImg = ImageIO.read(file);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(poolImg, 0, 0, null);
        drawAllFish(g);
        drawNet(g);

        g.drawImage(fstImg,10,400,null);
        Image rightAdd = new ImageIcon("source/image/add.png").getImage();
        Image leftEduce  = new ImageIcon("source/image/sub.png").getImage();

        // 创建隐形的按钮
        rightButton = new JButton();
        leftButton = new JButton();
        rightButton.setBounds(447, 433, 80, 30);
        leftButton.setBounds(343, 433, 80, 30);
        rightButton.setOpaque(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setBorderPainted(false);
        leftButton.setOpaque(false);
        leftButton.setContentAreaFilled(false);
        leftButton.setBorderPainted(false);

        // 添加隐形按钮的点击事件
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                level++;
//                if(level>5){
//                    level=1;
//                }
//                net.level=level;
                pt.addPt();
            }
        });
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                level--;
//                if(level<1){
//                    level=5;
//                }
//                net.level=level;
                pt.subPt();
            }
        });

        // 将按钮添加到组件上
        this.add(rightButton);
        this.add(leftButton);

        // 绘制按钮
        g.drawImage(leftEduce, 343, 433, 80, 30, this);
        g.drawImage(rightAdd, 447, 433, 80, 30, this);

        drawBullet(g);
        drawMess(g);
        drawPt(g);

        if (showImage) {
            if(score-temp==10){
                File file = new File("source/image/score/score_10.png");
                try {
                    BufferedImage img=ImageIO.read(file);
                    g.drawImage(img,400,50,null);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                File file = new File("source/image/score/score_20.png");
                try {
                    BufferedImage img=ImageIO.read(file);
                    g.drawImage(img,400,50,null);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void drawBullet(Graphics g) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if(bullet.isLive){
                bullet.drawBullet(g);
            }else{
                bullets.remove(bullet);
            }

        }
    }

    private void drawMess(Graphics g) {
        g.setFont(new Font("微软雅黑",Font.BOLD,21));
        g.setColor(Color.red);
        g.drawString(String.valueOf(score%10),148,464);

        g.drawString(String.valueOf(score%100/10),123,464);

        g.drawString(String.valueOf(score%1000/100),100,464);

        g.drawString(String.valueOf(score%10000/1000),77,464);

        g.drawString(String.valueOf(score%100000/10000),54,464);

        g.drawString(String.valueOf(score%1000000/100000),31,464);

        g.drawString("子弹数量"+bullets.size(),30,30);

    }

    private void drawPt(Graphics g) {
        double x1 = mouseX-ptX;
        double y1 = mouseY-ptY;
        angle = -Math.atan(x1/y1);
        Graphics2D gp = (Graphics2D) g;
        gp.rotate(angle,ptX+pt.ptImg.getWidth()/2,ptY+pt.ptImg.getHeight());
        gp.drawImage(pt.ptImg,ptX,ptY,null);
    }

    private void drawNet(Graphics g) {
        if (net.flag) {
            g.drawImage(net.netImg, net.x, net.y, null);
        }
    }

    private void drawAllFish(Graphics g) {
        for (int i = 0; i < allFish.length; i++) {
            g.drawImage(allFish[i].fishImg, allFish[i].x, allFish[i].y, null);
        }
    }

    public void action() {
        allFishStart();
        mouseEvent();
        LongLongPaint();

    }

    private void mouseEvent() {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                net.x = e.getX() - net.netImg.getWidth() / 2;
                net.y = e.getY() - net.netImg.getHeight() / 2;

                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                finY=y;
                Point netPoint = new Point(x, y);

                Bullet bullet = new Bullet(Pool.this);
                bullet.imageIcon = new ImageIcon("source/image/bullet.png");

                bullet.x = ptX + 10;
                bullet.y = ptY + 20;
                bullet.route = angle;

                bullet.p = new Point(ptX +19,ptY +39);
                if (e.getButton() == MouseEvent.BUTTON1&&bullets.size()<5) {
                    bullets.add(bullet);
                    BulletThread thread = new BulletThread(bullet);
                    thread.start();
                    catchFish(netPoint);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
//                    System.out.println("鼠标右键点击");
                    pt.addPt();
                    net.changeNet();
                }
            }

            private void catchFish(Point netPoint) {
                for (int i = 0; i < allFish.length; i++) {
                    Fish fish = allFish[i];
                    Point fishPoint = new Point(fish.x+fish.fishImg.getWidth()/2, fish.y+fish.fishImg.getHeight()/2);
                    boolean flag = checkCatchFish(netPoint, fishPoint);
                    if (flag) {
                        yanChi_CXU(fish);
                        fish.isCatch = false;
                        fish.speed = 1;

                    }
                }
            }

            private void yanChi_CXU(Fish fish) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        fish.removing();
                        if (fish.fishImg.getWidth() < 200) {//捕捉鱼的得分
                            updateScore(10);
                            recordTime();
                        } else {
                            updateScore(20);
                            recordTime();
                        }
                        if(score>1000){
                            File file = new File("source/image/t1.jpg");//根据分数切换背景(这里可以补充下一关提示)
                            try {
                                poolImg = ImageIO.read(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        timer.cancel();
                    }
                }, 1000);
            }

            private boolean checkCatchFish(Point netPoint, Point fishPoint) {
                int distance = (int) netPoint.distance(fishPoint);
                if (distance <= net.netImg.getWidth() / 2) {
                    return true;
                }
                return false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                net.flag = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                net.flag = false;
            }
        };

        this.addMouseListener(adapter);
        this.addMouseMotionListener(adapter);
    }

    private void LongLongPaint() {
        while (true) {
            repaint();
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void allFishStart() {
        for (int i = 0; i < allFish.length; i++) {
            allFish[i].start();
        }
    }

    private void updateScore(int increment) {
        // 更新score的值
        score += increment;
    }
    public void recordTime() {
        if (score != temp) {
            showImage = true;
            repaint();
            temp = score;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    showImage = false;
                    repaint();
                }
            }, 500); // 0.5秒后执行任务
        }
    }
}
