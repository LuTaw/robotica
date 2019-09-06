import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.addon.OpticalDistanceSensor;

import java.io.*; 

public class Moverse implements Behavior 
{

	private ColorSensor colorAdelante  = new ColorSensor(SensorPort.S2);
	private static ControladorPilot cp;

	public Moverse(ControladorPilot n) 
	{
		//System.out.println("esta en el constructor de moverse");
		cp = n;
	}

	public boolean takeControl() 
	{
		//System.out.println("esta evaluando el takecontrol de moverse");
		return (colorAdelante.getColorID() != 7);
	}

	public void suppress() 
	{
		cp.stop();
	}

	public void action() 
	{
		cp.getRandomPosition();
	}
}
