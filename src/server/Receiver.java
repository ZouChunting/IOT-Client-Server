package server;

import java.awt.Frame;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import dao.TemHumDao;
import entity.TemHum;
import service.TemHumService;

public class Receiver {
	
	public static void Thread() {
		try {
			TemHumService temHumService=new TemHumService();
			Client client=new Client(18, 73);
			client.launchFrame();
			new Thread() {
				DatagramSocket ds = new DatagramSocket(3000);
				public void run() {
					boolean flg=false;
					while (!flg) {
						try {
							
							byte[] buf = new byte[1024];
							DatagramPacket dp = new DatagramPacket(buf, buf.length);
							ds.receive(dp);
							//System.out.println(dp.getData().length);  //1024
							//System.out.println(dp.getSocketAddress());  ///127.0.0.1:59197
							//System.out.println(ds.getInetAddress());  //null
							//String data = " form :" + dp.getAddress().getHostAddress() + new String(dp.getData(), 0, dp.getLength());
							//System.out.println("tag");
							//System.out.println(data);   //form :127.0.0.1www.csdn.net
							
							//调用DAO转存数据库
							String th=new String(dp.getData(), 0, dp.getLength());
							String[] arr=th.split(" ");
							//System.out.println(arr[0]);
							//System.out.println(arr[1]);
							TemHum temHum=new TemHum();
							temHum.setTemperature(Integer.parseInt(arr[0]));
							temHum.setHumidity(Integer.parseInt(arr[1].trim()));
							
							temHumService.add(temHum);
							
							client.setTem(Integer.parseInt(arr[0]));
							client.setHum(Integer.parseInt(arr[1].trim()));
							client.repeat();
							System.out.println("接收");
							System.out.println(new String(dp.getData(), 0, dp.getLength()));  //www.csdn.net
							Thread.sleep(1000);
						} catch (IOException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}.start();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		try {
			DatagramSocket ds = new DatagramSocket(3000);
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			ds.receive(dp);
			//System.out.println(dp.getData().length);  //1024
			//System.out.println(dp.getSocketAddress());  ///127.0.0.1:59197
			//System.out.println(ds.getInetAddress());  //null
			String data = " form :" + dp.getAddress().getHostAddress() + new String(dp.getData(), 0, dp.getLength());
			//System.out.println("tag");
			//System.out.println(data);   //form :127.0.0.1www.csdn.net
			System.out.println(new String(dp.getData(), 0, dp.getLength()));  //www.csdn.net
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		Thread();
	}

}
