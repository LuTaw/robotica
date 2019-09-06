import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.addon.OpticalDistanceSensor;

import java.io.*; 

// estamos en color, tenemos tubo y color anterior no es verde
public class BuscarBlancoConTubo implements Behavior
{
	private int colorAnterior;
	private int colorActual;
	private ColorSensor colorAdelante  = new ColorSensor(SensorPort.S2);
	private static ControladorPilot cp;

	public BuscarBlancoConTubo(ControladorPilot n) 
	{
		cp = n;
		colorAnterior = cp.getColorAnterior();
		colorActual = cp.getColorActual();	
	}
	
	public boolean takeControl() 
	{
		return ((colorAdelante.getColorID() == 0 || colorAdelante.getColorID() == 3 || colorAdelante.getColorID() == 2) 
			&& (colorAnterior == 0 ||colorAnterior == 3 || colorAnterior == 2 || colorAnterior == 1) 
			&& cp.getTieneTubo());
	}

	public void suppress() 
	{
		cp.stop();
	}

	public void action() 
	{
		while (colorAdelante.getColorID() == 0 || colorAdelante.getColorID() == 3 || colorAdelante.getColorID() == 2) 
		{
			cp.getRandomPosition();
		}
		if (colorAdelante.getColorID() != 0 || colorAdelante.getColorID() != 3 || colorAdelante.getColorID() != 2) 
		{
			cp.setColorAnterior(colorActual);
			cp.setColorActual(colorAdelante.getColorID());
		}
	}
}
