import lejos.nxt.*;
import lejos.robotics.Color;

import java.io.*; 

public class probandoSensor
{

	public static void main(String[] args) throws Exception {
		int i = 0;
		ColorSensor colorAdelante = new ColorSensor(SensorPort.S2);
		for (i = 0 ; i <= 10 ; i++)
		{			
			System.out.println(colorAdelante.getColorID());
		}
		Button.waitForAnyPress();
		if(Button.ESCAPE.isPressed())
		{
			System.exit(0);
		}

  	}
}
