import lejos.nxt.*;
import java.io.*;

import javax.bluetooth.RemoteDevice;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

public class ManejadorConexion
{
	private static boolean isConnected = false;
	private static BTConnection btc;

	public ManejadorConexion() { }
	
	public static BTConnection getConexion() {
		if (!isConnected) {
		
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

			return btc;
		}
		return btc;
	}
}
