//package org.lejos.sample.btreceive;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.*;

import lejos.nxt.Motor;

import lejos.nxt.LCD;
import lejos.nxt.Button;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

//import lejos.nxt.UltrasonicSensor;
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
		//final UltrasonicSensor ultraSonic = new UltrasonicSensor(SensorPort.S4);


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
		while (Button.ESCAPE.isUp())
		{


			//DataOutputStream dos = btc.openDataOutputStream();
			try
			{
        DataInputStream dis = btc.openDataInputStream();
		String distancias = dis.readUTF();
	/*String separador = "-";
        String[] dist = distancias.split(separador);
        int ojoDer = (int) dist[0];
        int ojoIzq = (int) dist[1];
        int ojoPla = (int) dist[2];
        int ojoTub = (int) dist[3];
        System.out.println(ojoTub+"-"+ojoPla+"-"+ojoIzq+"-"+ojoDer);*/
        //LCD.drawInt(coso.toInt(),8,0,1);

        //LCD.refresh();
        /*Motor.A.resetTachoCount();
				if (coso < 10)
				{
          Motor.A.rotateTo(180,true);
					/*LCD.drawInt(ultraSonic.getDistance(),8,0,1);
					dos.writeByte(ultraSonic.getDistance());
					dos.flush();
				}*/


				dis.close();
				//dos.close();
				/*try {
	                		Thread.sleep(100);
            			} catch (InterruptedException e) {
	                		e.printStackTrace();
	            		}*/
				//Thread.sleep(100); // wait for data to drain
				/*LCD.clear();
				LCD.drawString(closing,0,0);*/
				//LCD.refresh();
				//btc.close();
				//LCD.clear();
			} catch (IOException e) {
	                	e.printStackTrace();
			}
		}
	}
}
