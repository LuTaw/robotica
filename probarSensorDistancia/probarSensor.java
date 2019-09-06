import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.addon.OpticalDistanceSensor;

import java.io.*; 

public class probarSensor
{
	public static void main(String[] args) throws Exception {
		OpticalDistanceSensor ojoTubos = new OpticalDistanceSensor(SensorPort.S3);
		while (!Button.ESCAPE.isPressed())
		{			
			System.out.println(ojoTubos.getDistance());
		}
		System.exit(0);

  	}
}
