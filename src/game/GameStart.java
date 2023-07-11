package game;

import show.Loading;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import show.GameWindow;

public class GameStart extends JFrame {
	BufferedImage welcomeImg;
	public GameStart() throws IOException {
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("捕鱼达人");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		File file = new File("source/image/bg_1.jpg");
		welcomeImg = ImageIO.read(file);
		JPanel jPanel = new JPanel(){
			@Override
			public void paint(Graphics g) {
				g.drawImage(welcomeImg,0,0,this.getWidth(),this.getHeight(),this);
			}
		};
		this.add(jPanel);
		Loading Label = new Loading(1000,30);
		this.add(Label,BorderLayout.SOUTH);
		Label.start();
		loding(this);
	}

	private void loding(GameStart welcomeWindow) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				welcomeWindow.setVisible(false);
				try {
					new GameWindow().show();
				} catch (IOException e) {
					e.printStackTrace();
				}
				timer.cancel();
			}
		},1000);
	}

	public static void main(String[] args) throws IOException {
		GameStart welcome = new GameStart();


		welcome.setVisible(true);

	}
}
