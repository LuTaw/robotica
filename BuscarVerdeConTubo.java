import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.addon.OpticalDistanceSensor;

import java.io.*; 

// Estamos en blanco y tenemos tubo
// probablemente sea el mismo buscarverde que el anterior pero queda renombrado por las dudas
public class BuscarVerdeConTubo implements Behavior
{
	private int colorAnterior;
	private int colorActual;
	private ColorSensor colorAdelante  = new ColorSensor(SensorPort.S2);
	private static ControladorPilot cp;

	public BuscarVerdeConTubo(ControladorPilot n) 
	{
		cp = n;
		colorAnterior = cp.getColorAnterior();
		colorActual = cp.getColorActual();	
	}
	
	public boolean takeControl() 
	{
		return (colorAdelante.getColorID() == 6 
			&& (colorAnterior == 0 || colorAnterior == 3 || colorAnterior == 2)
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
