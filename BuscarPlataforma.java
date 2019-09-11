import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.addon.OpticalDistanceSensor;

import java.io.*; 

public class BuscarPlataforma implements Behavior 
{
	private ColorSensor colorAdelante  = new ColorSensor(SensorPort.S2);
	private UltrasonicSensor ojosIzq = new UltrasonicSensor(SensorPort.S1);
	private UltrasonicSensor ojosDer = new UltrasonicSensor(SensorPort.S4); 
	private OpticalDistanceSensor ojosPlat = new OpticalDistanceSensor(SensorPort.S3);
	private static ControladorPilot cp;

	public BuscarPlataforma(ControladorPilot n) 
	{
		cp = n;
	}

	public boolean takeControl() 
	{
		System.out.println("condBP");
		return ((colorAdelante.getColorID() == 2) && (cp.getColorAnterior() == 1) && (!cp.getEncontrePlataforma()) && (cp.getTieneTubo()));
	}

	public void suppress() 
	{
		cp.stop();
	}

	public void action() 
	{
		System.out.println("estamos en el action de buscar plataforma");
		while (ojosDer.getDistance() > 80 && ojosIzq.getDistance() > 80 && ojosPlat.getDistance() >= 190){
			cp.getRandomPosition();
		}
		if (ojosDer.getDistance() < 80) {
			cp.girar(false, 20);
			while (ojosPlat.getDistance() >= 190) {
				cp.avanzar();
			}
		}
		else if (ojosIzq.getDistance() < 80) {
			cp.girar(true, 20);
			while (ojosPlat.getDistance() >= 190) {
				cp.avanzar();
			}
		}
		else if (ojosPlat.getDistance() < 190) {
			cp.girar(false, 20);
			cp.setEncontrePlataforma(true);
		}
	}
}
