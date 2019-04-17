package server;

public class ViewTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new DataView().dataFrame();
		Client client=new Client(0, 0);
		client.launchFrame();	
		client.setTem(18);
		client.setHum(80);
		client.repeat();
	}

}
