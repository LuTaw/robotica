import lejos.nxt.*;
import java.io.*;

import javax.bluetooth.RemoteDevice;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

import lejos.nxt.UltrasonicSensor;
import lejos.nxt.OpticalDistanceSensor;

//import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.nxt.SensorPort;

public class BTConnectTest2 extends Thread {

	public BTConnectTest2() {}

	public void run()
	{
		final UltrasonicSensor ojoDerecho = new UltrasonicSensor(SensorPort.S2);
		final UltrasonicSensor ojoIzquierdo = new UltrasonicSensor(SensorPort.S1);
		final UltrasonicSensor ojoPlataforma = new UltrasonicSensor(SensorPort.S3);
		final OpticalDistanceSensor ojoTubos = new OpticalDistanceSensor(SensorPort.S4);

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

			//DataInputStream dis = btc.openDataInputStream();


			while (Button.ESCAPE.isUp())
			{
				try
				{
					/*int distDer = ojoDerecho.getDistance();

					LCD.drawInt(distDer,8,0,1);
					LCD.refresh();*/
					DataOutputStream dos = btc.openDataOutputStream();
					String distDer = ojoDerecho.getDistance()+"-"+ojoIzquierdo.getDistance()+"-"+ojoPlataforma.getDistance+"-"+ojoTubos.getDistance;
					dos.writeUTF(distDer);
					dos.flush();

					dos.close();
					/*try
					{
						Thread.sleep(3000);
			    		} catch (InterruptedException e) {
						e.printStackTrace();
					}

					try {
					        Thread.sleep(2000);
				    	} catch (InterruptedException e)
					{
			        		e.printStackTrace();
			    		}*/

				} catch (IOException ioe) {
					LCD.drawString("Write Exception", 0, 0);
					LCD.refresh();
				}
			}

			LCD.drawString("Closing...    ", 0, 0);
			LCD.refresh();
			//dis.close();

			btc.close();
			LCD.clear();
			LCD.drawString("Finished",3, 4);
			LCD.refresh();

	}
}
