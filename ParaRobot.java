import lejos.nxt.*;
import lejos.robotics.subsumption.*;

public class ParaRobot implements Behavior 
{
	public boolean takeControl() 
	{
		return Button.ESCAPE.isPressed();
	}

	public void suppress() 
	{
		System.exit(0);
	}

	public void action() 
	{
		System.exit(0);
	}

}
