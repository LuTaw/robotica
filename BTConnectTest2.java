import lejos.nxt.*;
import java.io.*;

import javax.bluetooth.RemoteDevice;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

import lejos.util.Delay;

public class BTConnectTest2
{
	private static ManejadorConexion mConexion;
	private static BTConnection btc;
	private static boolean isConnected = false;

	public BTConnectTest2() /*throws Exception*/ {
		/*if (!isConnected) {
		
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
		}*/
	}

	public int getDistanceOjosTubos()
	{
		System.out.println("antes de tomar las medidas del sensor ded distancia");
		return this.getDistance(4);
	}

	public int getDistanceOjosPlat()
	{
		return this.getDistance(3);
	}

	public int getDistanceOjosPlatDerecha()
	{
		return this.getDistance(2);
	}

	public int getDistanceOjosPlatIzquierda()
	{
		return this.getDistance(1);
	}

	private int getDistance(int sensorPort) 
	{
		int sensorValue = 0;
		try {
			DataOutputStream dos = btc.openDataOutputStream();
			DataInputStream dis = btc.openDataInputStream();
			System.out.println("antes de enviar puerto");
			dos.writeInt(sensorPort);
			System.out.println("despues de enviar puerto");
			Delay.msDelay(500);
			System.out.println("antes de leer distancia");
			sensorValue = (int) dis.readInt();
			dos.flush();
			dos.close();
			dis.close();
		} catch (Exception e) {
		System.out.println("exception");
			e.printStackTrace();
		}	
		return sensorValue;
	}


}
