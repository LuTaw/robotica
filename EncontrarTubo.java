import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.addon.OpticalDistanceSensor;

import java.io.*; 

public class EncontrarTubo implements Behavior 
{

	private ColorSensor colorAdelante = new ColorSensor(SensorPort.S2);
	private UltrasonicSensor ojosIzq = new UltrasonicSensor(SensorPort.S1);
	private UltrasonicSensor ojosDer = new UltrasonicSensor(SensorPort.S4); 
	private OpticalDistanceSensor ojosPlat = new OpticalDistanceSensor(SensorPort.S3);
	private static ControladorPilot cp;

	public EncontrarTubo(ControladorPilot n) 
	{
		cp = n;
	}

	public boolean takeControl() 
	{
		return ((ojosDer.getDistance() <= 15) || (ojosIzq.getDistance() <= 15));
	}

	public void suppress() 
	{
		cp.stop();
	}

	public void action() 
	{
		if (ojosDer.getDistance() <= 15) 
		{
			cp.girar(false, 100);
		} else 
		{
			cp.girar(true, 100);
		}	
	}
}
