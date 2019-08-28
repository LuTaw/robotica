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

	public BTConnectTest2() {
		mConexion = new ManejadorConexion();
		btc = mConexion.getConexion();
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
			dos.writeByte(sensorPort);
			System.out.println("despues de enviar puerto");
			Delay.msDelay(500);
			System.out.println("antes de leer distancia");
			sensorValue = (int) dis.readByte();
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
