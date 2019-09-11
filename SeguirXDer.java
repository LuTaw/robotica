import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.addon.OpticalDistanceSensor;

import java.io.*; 

public class SeguirXDer implements Behavior 
{

	private ColorSensor colorAdelante  = new ColorSensor(SensorPort.S2);
	private UltrasonicSensor ojosDer = new UltrasonicSensor(SensorPort.S4);
	private static ControladorPilot cp;

	public SeguirXDer(ControladorPilot n) 
	{
		cp = n;
	}

	public boolean takeControl() 
	{
		System.out.println("condSXD");
		return (cp.getTieneTubo() && !cp.getFaltaTuboDerecha() && cp.getEncontrePlataforma());
	}

	public void suppress() 
	{
		cp.stop();
	}

	public void action() 
	{
		System.out.println("estamos en el action de seguir der");
		while (ojosDer.getDistance() == 10) {
			cp.avanzar();
		}
		if (ojosDer.getDistance() < 10) {
			cp.girar(true, 20);
		}
		else if (ojosDer.getDistance() > 10 && ojosDer.getDistance() < 20) {
			cp.girar(false, 20);
		}
		else if (ojosDer.getDistance() > 20 && ojosDer.getDistance() < 80) {
			cp.setEncontrePlataforma(false);
		}
		else if (ojosDer.getDistance() > 80) {
			cp.setFaltaTuboDerecha(true);
		}
	}
}
