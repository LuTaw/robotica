import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.addon.OpticalDistanceSensor;

import java.io.*; 

public class SeguirXDer implements Behavior 
{

	private ColorSensor colorAdelante  = new ColorSensor(SensorPort.S2);
	private UltrasonicSensor ojosDer = new UltrasonicSensor(SensorPort.)
	private static ControladorPilot cp;

	public SeguirXDer(ControladorPilot n) 
	{
		cp = n;
	}

	public boolean takeControl() 
	{
		return (cp.getTengoTubo() && !cp.getFaltaTuboDerecha() && cp.getEncontrePlataforma());
	}

	public void suppress() 
	{
		cp.stop();
	}

	public void action() 
	{
		while (ojosDer.getDistance() == 10) {
			cp.avanzar();
		}
		if (ojosDer.getDistance() < 10) {
			cp.girar();
		}
		else if (ojosDer.getDistance() > 10 && ojosDer.getDistance() < 20) {
			cp.girar();
		}
		else if (ojosDer.getDistance > 20 && ojosDer.getDistance < 80) {
			cp.setEncontrePlataforma(false);
		}
		else if (ojosDer.getDistance() > 80) {
			cp.setFaltaTuboDerecha(true);
		}
	}
}