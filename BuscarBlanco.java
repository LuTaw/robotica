import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.addon.OpticalDistanceSensor;

import java.io.*; 

// estamos en verde y no tenemos tubo y color anterior es blanco o azul
public class BuscarBlanco implements Behavior
{
	private int colorAnterior;
	private int colorActual;
	private ColorSensor colorAdelante  = new ColorSensor(SensorPort.S2);
	private static ControladorPilot cp;

	public BuscarBlanco(ControladorPilot n) 
	{
		cp = n;
		colorAnterior = cp.getColorAnterior();
		colorActual = cp.getColorActual();	
	}
	
	public boolean takeControl() 
	{
		return (colorAdelante.getColorID() == 1 && (colorAnterior== 2 || colorAnterior == 6) && !cp.getTieneTubo());
	}

	public void suppress() 
	{
		cp.stop();
	}

	public void action() 
	{
		cp.setColorAnterior(colorActual);
		cp.setColorActual(colorAdelante.getColorID());
		if (colorAnterior == 6) 
		{
			while (colorAdelante.getColorID() == 1)
			{
				cp.volverParaAtras();
			}
			cp.stopPilot();
		} else 
		{
			cp.getRandomPosition();
		}
	}
}
