import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.addon.OpticalDistanceSensor;

import java.io.*; 

public class BuscarPlataforma implements Behavior 
{
	private ColorSensor colorAdelante  = new ColorSensor(SensorPort.S2);
	private UltrasonicSensor ojosIzq = new UltrasonicSensor(SensorPort.)
	private UltrasonicSensor ojosDer = new UltrasonicSensor(SensorPort.) 
	private OpticalDistanceSensor ojosPlat = new OpticalDistanceSensor(SensorPort.)
	private static ControladorPilot cp;

	public BuscarPlataforma(ControladorPilot n) 
	{
		cp = n;
	}

	public boolean takeControl() 
	{
		return (colorAdelante.getColorID() == 2 && cp.getColorAnterior() == 1 && !cp.getEncontrePlataforma && cp.get && cp.getTengoTubo());
	}

	public void suppress() 
	{
		cp.stop();
	}

	public void action() 
	{
		while (ojosDer.getDistance() > 80 && ojosIzq.getDistance() > 80 && ojosPlat.getDistance() == 100){
			cp.getRandomPosition();
		}
		if (ojosDer.getDistance() < 80) {
			cp.girar();
			while (ojosPlat.getDistance() == 100) {
				cp.avanzar
			}
		}
		else if (ojosIzq.getDistance() < 80) {
			cp.girar();
			while (ojosPlat.getDistance() == 100) {
				cp.avanzar
			}
		}
		else if (ojosPlat.getDistance() < 100) {
			cp.girar();
			cp.setEncontrePlataforma(true);
		}
	}
}