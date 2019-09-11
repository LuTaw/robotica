import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.addon.OpticalDistanceSensor;

import java.io.*; 

public class SeguirXIzq implements Behavior 
{

	private ColorSensor colorAdelante  = new ColorSensor(SensorPort.S2);
	private UltrasonicSensor ojosIzq = new UltrasonicSensor(SensorPort.S1);
	private static ControladorPilot cp;

	public SeguirXIzq(ControladorPilot n) 
	{
		cp = n;
	}

	public boolean takeControl() 
	{
		System.out.println("condSXZ");
		return (cp.getTieneTubo() && !cp.getFaltaTuboIzquierda() && cp.getEncontrePlataforma());
	}

	public void suppress() 
	{
		cp.stop();
	}

	public void action() 
	{
		System.out.println("estamos en el action de seguir izq");
		while (ojosIzq.getDistance() == 10) {
			cp.avanzar();
		}
		if (ojosIzq.getDistance() < 10) {
			cp.girar(true, 20);
		}
		else if (ojosIzq.getDistance() > 10 && ojosIzq.getDistance() < 20) {
			cp.girar(false, 20);
		}
		else if (ojosIzq.getDistance() > 20 && ojosIzq.getDistance() < 80) {
			cp.setEncontrePlataforma(false);
		}
		else if (ojosIzq.getDistance() > 80) {
			cp.setFaltaTuboIzquierda(true);
		}
	}
}
