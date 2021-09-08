import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import processing.core.PApplet;

public class Client extends PApplet
{

	public static void main(String[] args) 
	{
		PApplet.main("Client");
	}
	
	@Override
	public void settings() //void Awake
	{
		size(500, 500);
	}
	
	InetAddress iAd;
	
	@Override
	public void setup() //void Start
	{
		background(0, 0, 255);
		
		try {
			iAd = InetAddress.getLocalHost();
			String localIp = iAd.getHostAddress();
			System.out.println(localIp);
			
			boolean connected = iAd.isReachable(500);
			
			if(connected)
			{
				System.out.println(localIp + " CONNECTED");
			}
			
			System.out.println("Connecting to Server...");
			
			Socket socket = new Socket("192.168.1.9", 5000);
			System.out.println("Connected Succesfully");
			
			//receiving data
			
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			String receivedData = reader.readLine();
			
			System.out.println("Received Message: " + receivedData);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not connect to server (TIMED OUT)");
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw() //void Update
	{		

	}

}
