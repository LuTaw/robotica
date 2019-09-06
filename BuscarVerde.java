import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.addon.OpticalDistanceSensor;

import java.io.*; 

// estamos en azul, no tenemos tubo y color anterior es verde
public class BuscarVerde implements Behavior
{
	private int colorAnterior;
	private int colorActual;
	private ColorSensor colorAdelante  = new ColorSensor(SensorPort.S2);
	private static ControladorPilot cp;

	public BuscarVerde(ControladorPilot n) 
	{
		cp = n;
		colorAnterior = cp.getColorAnterior();
		colorActual = cp.getColorActual();	
	}
	
	public boolean takeControl() 
	{
		return (colorAdelante.getColorID() == 2 && colorAnterior == 1 && !cp.getTieneTubo());
	}

	public void suppress() 
	{
		cp.stop();
	}

	public void action() 
	{
		cp.setColorAnterior(colorActual);
		cp.setColorActual(colorAdelante.getColorID());
		while (colorAdelante.getColorID() == 2) 
		{
			cp.getRandomPosition();
		}
	}
}
