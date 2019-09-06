import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.addon.OpticalDistanceSensor;

import java.io.*; 

public class Moverse implements Behavior 
{

	private ColorSensor colorAdelante = new ColorSensor(SensorPort.S2);
	private static ControladorPilot cp;

	public Moverse(ControladorPilot n) 
	{
		cp = n;
	}

	public boolean takeControl() 
	{
		return (colorAdelante.getColorID() != 7);
	}

	public void suppress() 
	{
		cp.stop();
	}

	public void action() 
	{System.out.println("Estamos en el action de moverse");
		if (cp.isPathCompleted()) 
		{
			cp.getRandomPosition();
		}	
	}
}
