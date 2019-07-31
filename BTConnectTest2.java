import lejos.nxt.*;
import java.io.*;

import javax.bluetooth.RemoteDevice;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

public class BTConnectTest2 extends Thread {
	
	public BTConnectTest2() {}

	public void run() 
	{
		boolean isConnected = false;
		BTConnection btc;

		String name = "NXT";

		LCD.drawString("Connecting...", 0, 0);
		LCD.refresh();

		RemoteDevice btrd = Bluetooth.getKnownDevice(name);
		
			if (btrd == null) 
			{
				LCD.clear();
				LCD.drawString("No such device", 0, 0);
				LCD.refresh();
				try {
	                		Thread.sleep(2000);
	            		} catch (InterruptedException e) {
	                		e.printStackTrace();
	            		}
				System.exit(1);
		
			}


			final byte[] pin = {(byte) '1', (byte) '2', (byte) '3', (byte) '4'};
			btc = Bluetooth.connect(btrd.getDeviceAddr(),  NXTConnection.LCP, pin);

			if (btc == null) 
			{
				LCD.clear();
				LCD.drawString("Connect fail", 0, 0);
				LCD.refresh();
				try {
		                Thread.sleep(2000);
			    	} catch (InterruptedException e) {
			        	e.printStackTrace();
			    	}
				System.exit(1);
			}

			LCD.clear();
			LCD.drawString("Connected", 0, 0);
			isConnected = true;
			LCD.refresh();

			while (true) 
			{
				try 
				{
					DataInputStream dis = btc.openDataInputStream();
					DataOutputStream dos = btc.openDataOutputStream();
					LCD.drawString("Master",1,1);
					LCD.refresh();
					dos.writeByte(7);
					dos.flush();

					try 
					{
						Thread.sleep(3000);
			    		} catch (InterruptedException e) {
						e.printStackTrace();
				    	}

					int coso = (int) dis.readByte();
					LCD.drawInt(coso,8,0,1);

					LCD.drawInt(dis.readInt(),8, 0,3);
					LCD.refresh();

					LCD.drawString("Closing...    ", 0, 0);
					LCD.refresh();
					dis.close();
					dos.close();
					btc.close();
					LCD.clear();
					LCD.drawString("Finished",3, 4);
					LCD.refresh();
					try {
					        Thread.sleep(2000);
				    	} catch (InterruptedException e) 
					{
			        		e.printStackTrace();
			    		}	

				} catch (IOException ioe) {
					LCD.drawString("Write Exception", 0, 0);
					LCD.refresh();
				}
			}
		
	}
}
