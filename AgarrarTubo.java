import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.addon.OpticalDistanceSensor;

import java.io.*; 

public class AgarrarTubo implements Behavior
{
	private int colorAnterior;
	private int colorActual;
	private ColorSensor colorAdelante  = new ColorSensor(SensorPort.S2);
	private static ControladorPilot cp;
	private static OpticalDistanceSensor ojoTubos = new OpticalDistanceSensor(SensorPort.S3);
	private int medidaTuboAgarrado = 0;

	public AgarrarTubo(ControladorPilot n) 
	{
		cp = n;
		colorAnterior = cp.getColorAnterior();
		colorActual = cp.getColorActual();	
	}
	public boolean takeControl() 
	{
		return ((155 <= ojoTubos.getDistance() && ojoTubos.getDistance() <= 165) 
			&& (!cp.getTieneTubo()) 
			&& (colorAdelante.getColorID() == 3 || colorAdelante.getColorID() == 2 || colorAdelante.getColorID() == 0)); 
	}

	public void suppress() 
	{
		//suppress = true;
	}
		
	public void action() 
	{
		cp.stop();
		cp.setTieneTubo(true);
		cp.setColorAnterior(colorActual);
		cp.setColorActual(colorAdelante.getColorID());
		cp.polea(false);
		//pilot.rotate(-60);
		//pilot.stop();
		if (colorAdelante.getColorID() == 2)
			medidaTuboAgarrado = 20;
		else if (colorAdelante.getColorID() == 0)
			medidaTuboAgarrado = 15;
		else if (colorAdelante.getColorID() == 3)	
			medidaTuboAgarrado = 10;
	}
}
