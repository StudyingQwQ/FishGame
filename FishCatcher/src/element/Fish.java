package element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Fish extends Thread {
    public BufferedImage fishImg;
    BufferedImage[][] allImg = new BufferedImage[4][10];
    BufferedImage[][] catchImg = new BufferedImage[4][4];
    public boolean isCatch = true;
    Random r = new Random();
    public int x;
    public int y;
    public int speed;
    int direction = 0;

    public void getImg(String name) throws IOException {
        getYouDongImg(name);
        setRandom();
        getCatchImg(name);

    }

    private void getCatchImg(String name) throws IOException {

        get_CatchImg(name, "E");
        get_CatchImg(name, "W");
        get_CatchImg(name, "N");
        get_CatchImg(name, "S");
    }

    private void get_CatchImg(String name, String fx) throws IOException {
        int index;
        switch (fx) {
            case "E":
                index = 0;
                break;
            case "W":
                index = 1;
                break;
            case "N":
                index = 2;
                break;
            case "S":
                index = 3;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fx);
        }
        for (int i = 0; i < catchImg[0].length; i++) {
            File file = new File("source/image/" + fx + "/" + name + "_catch_0" + (i + 1) + ".png");
            catchImg[index][i] = ImageIO.read(file);
        }
    }

    private void setRandom() {
        x = r.nextInt(800);
        y = r.nextInt(500);
        speed = r.nextInt(10) + 3;
        direction = r.nextInt(4);
    }

    private void getYouDongImg(String name) throws IOException {
        get_Img(name, "E");
        get_Img(name, "W");


        get_Img(name, "N");
        get_Img(name, "S");
    }

    private void get_Img(String name, String fx) throws IOException {
        int index;
        switch (fx) {
            case "E":
                index = 0;
                break;
            case "W":
                index = 1;
                break;
            case "N":
                index = 2;
                break;
            case "S":
                index = 3;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fx);
        }
        for (int i = 0; i < allImg[0].length; i++) {
            if (i < 9) {
                File file = new File("source/image/" + fx + "/" + name + "_0" + (i + 1) + ".png");
                allImg[index][i] = ImageIO.read(file);
            } else {
                File file = new File("source/image/" + fx + "/" + name + "_" + (i + 1) + ".png");
                allImg[index][i] = ImageIO.read(file);
            }

        }
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            changeImg(i++);
            moving();
        }
    }

    // private void changeImg(int i) {
    //     if (direction == 0) {
    //         if (isCatch) {
    //             fishImg = allImg[0][i % 10];
    //         } else {
    //             fishImg = catchImg[0][i % 4];
    //         }
    //     } else if (direction == 1) {
    //         if (isCatch) {
    //             fishImg = allImg[1][i % 10];
    //         } else {
    //             fishImg = catchImg[1][i % 4];
    //         }
    //     } else if (direction == 2) {
    //         if (isCatch) {
    //             fishImg = allImg[2][i % 10];
    //         } else {
    //             fishImg = catchImg[2][i % 4];
    //         }
    //     } else {
    //         if (isCatch) {
    //             fishImg = allImg[3][i % 10];
    //         } else {
    //             fishImg = catchImg[3][i % 4];
    //         }
    //     }
    
    //     try {
    //         Thread.sleep(60);
    //     } catch (InterruptedException e) {
    //         e.printStackTrace();
    //     }
    // }
private void changeImg(int i) {
    int row = direction;
    int column = i % 10;
    
    if (isCatch) {
        fishImg = allImg[row][column];
    } else {
        fishImg = catchImg[row][column % 4];
    }

    try {
        Thread.sleep(60);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

    private void moving() {
        if (direction == 0) {
            x = x - speed;
            if (x + fishImg.getWidth() <= 0) {
                removing();
            }
        } else if (direction == 1) {
            x = x + speed;
            if (x - fishImg.getWidth() >= 800) {
                removing();
            }
        } else if (direction == 2) {
            y = y - speed;
            if (y + fishImg.getHeight() <= 0) {
                removing();
            }
        } else {
            y = y + speed;
            if (y - fishImg.getHeight() >= 500) {
                removing();
            }
        }

    }

    // private void move() {
    //     if (direction == 0) {
    //         x -= speed;
    //         if (x + fishImg.getWidth() <= 0) {
    //             remove();
    //         }
    //     } else if (direction == 1) {
    //         x += speed;
    //         if (x - fishImg.getWidth() >= 800) {
    //             remove();
    //         }
    //     } else if (direction == 2) {
    //         y -= speed;
    //         if (y + fishImg.getHeight() <= 0) {
    //             remove();
    //         }
    //     } else {
    //         y += speed;
    //         if (y - fishImg.getHeight() >= 500) {
    //             remove();
    //         }
    //     }
    // }
    public void removing() {
        final int MAX_X = 800;
        final int MAX_Y = 500;
        final int MIN_SPEED = 3;
        final int MAX_SPEED = 12;
    
        switch (direction) {
            case 0:
                x = MAX_X;
                y = r.nextInt(MAX_Y);
                break;
            case 1:
                x = -fishImg.getWidth();
                y = r.nextInt(MAX_Y);
                break;
            case 2:
                y = MAX_Y;
                x = r.nextInt(MAX_X);
                break;
            default:
                y = -fishImg.getHeight();
                x = r.nextInt(MAX_X);
                break;
        }
    
        speed = r.nextInt(MAX_SPEED - MIN_SPEED + 1) + MIN_SPEED;
        isCatch = true;
    }
}
