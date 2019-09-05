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

import java.util.Random;

public class Moverse implements Behavior 
{

	public static float AREA_WIDTH = 200F;
	public static float AREA_LENGTH = 200F;

	ColorSensor colorAdelante;

	PilotProps pp;
	RegulatedMotor leftMotor;
	RegulatedMotor rightMotor;
	RotateMoveController pilot;
	Navigator nav;

	public Moverse() 
	{
		float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "5.7"));
		float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "14.5"));
		boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE, "false"));
		colorAdelante = new ColorSensor(SensorPort.S2);
		pp = new PilotProps();
		leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "B"));
		rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "C"));
		pilot = new DifferentialPilot(wheelDiameter, trackWidth, leftMotor, rightMotor, reverse);
		pilot.setRotateSpeed(150);
		nav = new Navigator(pilot);

	}
	

	public boolean takeControl() 
	{
		return (colorAdelante.getColorID() != 7);
	}

	public void suppress() 
	{
		nav.stop();
		//suppress = true;
	}

	public void action() 
	{
		double x_targ = Math.random() * AREA_WIDTH;
		double y_targ = Math.random() * AREA_LENGTH;
		moverseRandom(nav, (float)x_targ, (float)y_targ);
		nav.stop();
	}

	private static void moverseRandom(Navigator nav, float x, float y) 
	{
    		nav.goTo(x, y);
	}
}
