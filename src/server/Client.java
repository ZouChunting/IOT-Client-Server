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
 * ������
 */
public class Client extends Frame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ���������
	 */
	public static final int WIDTH = 800;
	
	/**
	 * �������߶�
	 */
	public static final int HEIGHT = 620;
	
	/**
	 * ����������λ�ã������꣩
	 */
	public static final int LOC_X = 200;
	
	/**
	 * ����������λ�ã������꣩
	 */
	public static final int LOC_Y = 70;
	
	private Font font = new Font("΢���ź�", Font.BOLD, 25);
	
	private int tem;
	private int hum;

	Color color = Color.WHITE; 
	Image offScreen = null;	//����˫����
	
	//����window��icon���������Զ�����һ��Windows���ڵ�iconͼ�꣬��Ϊʵ�ھ����ĸ�С����ͼ�겻�ÿ� = =��
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
	 * ������
	 * @param args	//
	 */
	/*public static void main(String[] args) {
		new Client().launchFrame();	
	}*/
	
	/**
	 * ��ʾ������
	 */
	public void launchFrame() {
		this.setBounds(LOC_X, LOC_Y, WIDTH, HEIGHT);	//�趨������������ֵ�λ��
		this.setTitle("IOT");	//���ó������
		this.setIconImage(icon);
		this.setBackground(Color.white);	//���ñ���ɫ
		
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
			//��ӶԴ���״̬�ļ���
			public void windowClosing(WindowEvent arg0) {
				//�����ڹر�ʱ
				System.exit(0);	//�˳�����
			}
			
		});

		this.setResizable(false);	//���ڴ�С���ɸ���
		this.setVisible(true);	//��ʾ����
			
		//new Thread(new RepaintThread()).start();	//�����ػ��߳�
	}
	
	/**
	 * ���������������Ԫ��
	 */
	public void paint(Graphics g) {
		Color c = g.getColor();
		
		g.setFont(new Font("΢���ź�", Font.BOLD, 25));
		g.setColor(Color.black);
		g.drawString(" �¶ȣ� ", 45, 130);
		g.drawString(tem+"", 120, 130);
		
		g.setColor(Color.black);
		g.setFont(new Font("΢���ź�", Font.BOLD, 25));
		g.drawString(" ʪ�ȣ� ", 425, 130);	
		g.drawString(hum+"", 500, 130);	
		
	}
	
	/**
	 * ˫���巽ʽ�ػ������Ԫ�����
	 */
	public void update(Graphics g) {
		if (offScreen == null)	offScreen = this.createImage(WIDTH, HEIGHT);
		Graphics gOffScreen = offScreen.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.white);
		gOffScreen.fillRect(0, 0, WIDTH, HEIGHT);	//�ػ���������
		this.paint(gOffScreen);	//�ػ�����Ԫ��
		gOffScreen.setColor(c);
		g.drawImage(offScreen, 0, 0, null);	//���»��õĻ�����������ԭ������
	}
	
	
	/*
	 * �ػ��̣߳�ÿ��250�����ػ�һ�Σ�
	 */
	public void repeat() {
		repaint();
	}
	
}
