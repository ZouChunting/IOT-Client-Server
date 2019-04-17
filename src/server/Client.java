package server;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

/**
 * 主程序
 */
public class Client extends Frame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 程序界面宽度
	 */
	public static final int WIDTH = 800;
	
	/**
	 * 程序界面高度
	 */
	public static final int HEIGHT = 620;
	
	/**
	 * 程序界面出现位置（横坐标）
	 */
	public static final int LOC_X = 200;
	
	/**
	 * 程序界面出现位置（纵坐标）
	 */
	public static final int LOC_Y = 70;
	
	private Font font = new Font("微软雅黑", Font.BOLD, 25);
	
	private int tem;
	private int hum;

	Color color = Color.WHITE; 
	Image offScreen = null;	//用于双缓冲
	
	//设置window的icon（这里我自定义了一下Windows窗口的icon图标，因为实在觉得哪个小咖啡图标不好看 = =）
	Toolkit toolKit = getToolkit();
	Image icon = toolKit.getImage(Client.class.getResource("computer.png"));
	
	
	public Client(int tem,int hum) {
		this.tem=tem;
		this.hum=hum;
	}
	
	public int getTem() {
		return tem;
	}



	public void setTem(int tem) {
		this.tem = tem;
	}



	public int getHum() {
		return hum;
	}



	public void setHum(int hum) {
		this.hum = hum;
	}
	
	/**
	 * 主方法
	 * @param args	//
	 */
	/*public static void main(String[] args) {
		new Client().launchFrame();	
	}*/
	
	/**
	 * 显示主界面
	 */
	public void launchFrame() {
		this.setBounds(LOC_X, LOC_Y, WIDTH, HEIGHT);	//设定程序在桌面出现的位置
		this.setTitle("IOT");	//设置程序标题
		this.setIconImage(icon);
		this.setBackground(Color.white);	//设置背景色
		
		/*tem.setBounds(140, 103, 225, 50);
		tem.setBackground(Color.black);
		tem.setFont(font);
		tem.setForeground(Color.white);
		add(tem);
		
		hum.setBounds(520, 103, 225, 50);
		hum.setBackground(Color.black);
		hum.setFont(font);
		hum.setForeground(Color.white);
		add(hum);*/
		
		this.addWindowListener(new WindowAdapter() {
			//添加对窗口状态的监听
			public void windowClosing(WindowEvent arg0) {
				//当窗口关闭时
				System.exit(0);	//退出程序
			}
			
		});

		this.setResizable(false);	//窗口大小不可更改
		this.setVisible(true);	//显示窗口
			
		//new Thread(new RepaintThread()).start();	//开启重画线程
	}
	
	/**
	 * 画出程序界面各组件元素
	 */
	public void paint(Graphics g) {
		Color c = g.getColor();
		
		g.setFont(new Font("微软雅黑", Font.BOLD, 25));
		g.setColor(Color.black);
		g.drawString(" 温度： ", 45, 130);
		g.drawString(tem+"", 120, 130);
		
		g.setColor(Color.black);
		g.setFont(new Font("微软雅黑", Font.BOLD, 25));
		g.drawString(" 湿度： ", 425, 130);	
		g.drawString(hum+"", 500, 130);	
		
	}
	
	/**
	 * 双缓冲方式重画界面各元素组件
	 */
	public void update(Graphics g) {
		if (offScreen == null)	offScreen = this.createImage(WIDTH, HEIGHT);
		Graphics gOffScreen = offScreen.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.white);
		gOffScreen.fillRect(0, 0, WIDTH, HEIGHT);	//重画背景画布
		this.paint(gOffScreen);	//重画界面元素
		gOffScreen.setColor(c);
		g.drawImage(offScreen, 0, 0, null);	//将新画好的画布“贴”在原画布上
	}
	
	
	/*
	 * 重画线程（每隔250毫秒重画一次）
	 */
	public void repeat() {
		repaint();
	}
	
}
