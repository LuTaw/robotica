import lejos.nxt.*;
import java.io.*;

import javax.bluetooth.RemoteDevice;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.OpticalDistanceSensor;

//import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.nxt.SensorPort;

public class BTConnectTest2 extends Thread {

	private static ManejadorConexion mConexion;

	public BTConnectTest2() {}

	public void run()
	{
		final UltrasonicSensor ojoDerecho = new UltrasonicSensor(SensorPort.S2);
		final UltrasonicSensor ojoIzquierdo = new UltrasonicSensor(SensorPort.S1);
		final UltrasonicSensor ojoPlataforma = new UltrasonicSensor(SensorPort.S3);
		final OpticalDistanceSensor ojoTubos = new OpticalDistanceSensor(SensorPort.S4);

		mConexion = new ManejadorConexion();
		BTConnection btc = mConexion.getConexion();

	}
}
