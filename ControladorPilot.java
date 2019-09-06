import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.util.Delay;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.*;
import lejos.robotics.navigation.LegacyNavigator;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.PilotProps;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.OpticalDistanceSensor;

import java.io.*; 

import java.util.Random;


public class ControladorPilot {

	private float AREA_WIDTH = 200F;
	private float AREA_LENGTH = 200F;

	private Waypoint wTarget;
	private Waypoint target;
	private static ControladorPilot cp;
	private PilotProps  pp = new PilotProps();
	private DifferentialPilot pilot = new DifferentialPilot(2.1f, 4.4f, Motor.B, Motor.C, false);
	private Navigator nav = new Navigator(pilot);

	public ControladorPilot() 
	{

	}

	public static ControladorPilot getInstance() 
	{
		//System.out.println("Acaba de entrar al getINstance");
		if (cp == null) 
		{
		//System.out.println("Entro al if");
			cp = new ControladorPilot();
		}
		//System.out.println("Salio del if");
		return cp;
	}

	public void getRandomPosition() 
	{
		//System.out.println("Acaba de entrar al getRandomPosition");
		if (cp == null) 
		{
		//System.out.println("el cp era null");
			cp = getInstance();
		}
		//System.out.println("antes de obtener el wpoint");
		wTarget = getTarget(Math.random() * AREA_WIDTH, Math.random() * AREA_LENGTH);
		System.out.println("antes de hacer el goTo");
		nav.goTo(wTarget);
	}

	public void stop()
	{
		nav.stop();
	}

	private Waypoint getTarget(double x, double y)
	{
    		target = new Waypoint(x, y);
		return target;
	}
}
