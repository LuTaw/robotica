import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.addon.OpticalDistanceSensor;

import java.io.*; 

public class NoCaerseEnNegro implements Behavior 
{
	private ColorSensor colorAdelante  = new ColorSensor(SensorPort.S2);
	private static ControladorPilot cp;

	public NoCaerseEnNegro(ControladorPilot n) 
	{
		//System.out.println("esta en el constructor de moverse");
		cp = n;
	}

	public boolean takeControl() 
	{
		return (colorAdelante.getColorID() == 7);
	}

	public void suppress() 
	{
		cp.stopPilot();
	}

	public void action() 
	{
		while (colorAdelante.getColorID() == 7)
		{
			cp.volverParaAtras();
		}
		cp.stopPilot();
	}
}
