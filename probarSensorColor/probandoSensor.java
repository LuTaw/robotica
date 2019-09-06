import lejos.nxt.*;
import lejos.robotics.Color;

import java.io.*; 

public class probandoSensor
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
