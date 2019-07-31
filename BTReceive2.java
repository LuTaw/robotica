//package org.lejos.sample.btreceive;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.*;

import lejos.nxt.LCD;
import lejos.nxt.Button;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

import lejos.nxt.UltrasonicSensor;
import lejos.nxt.SensorPort;
/**
 * Receive data from another NXT, a PC, a phone,
 * or another bluetooth device.
 *
 * Waits for a connection, receives an int and returns
 * its negative as a reply, 100 times, and then closes
 * the connection, and waits for a new one.
 *
 * @author Lawrie Griffiths
 *
 */
public class BTReceive2 extends Thread {
 
 	public BTReceive2() {}

	@Override
    	public void run()
	{
		final UltrasonicSensor ultraSonic = new UltrasonicSensor(SensorPort.S4);

		String connected = "Connected";
        	String waiting = "Waiting...";
        	String closing = "Closing...";
		boolean isConnected = false;
        	BTConnection btc;

		LCD.drawString(waiting,0,0);
		LCD.refresh();
		btc = Bluetooth.waitForConnection();

		LCD.clear();
		//LCD.drawString(connected,0,0);
		LCD.refresh();
		while (true)
		{

			DataInputStream dis = btc.openDataInputStream();
			DataOutputStream dos = btc.openDataOutputStream();
			try 
			{
				int coso = (int) dis.readByte();
				if (coso == 7)
				{
					LCD.drawInt(ultraSonic.getDistance(),8,0,1);
					dos.writeByte(ultraSonic.getDistance());
					dos.flush();
				}
				//LCD.drawInt(coso,8,0,1);
				/*for(int i=0;i<100;i++) {
					int n = dis.readInt();
					LCD.drawInt(n,7,0,1);
					LCD.refresh();
					dos.writeInt(-n);
					dos.flush();
				}*/
				Button.waitForAnyPress();

				dis.close();
				dos.close();
				/*try {
	                		Thread.sleep(100);
            			} catch (InterruptedException e) {
	                		e.printStackTrace();
	            		}*/ 
				//Thread.sleep(100); // wait for data to drain
				LCD.clear();
				LCD.drawString(closing,0,0);
				LCD.refresh();
				btc.close();
				LCD.clear();
			} catch (IOException e) {
	                	e.printStackTrace();
			}
		}
	}
}
