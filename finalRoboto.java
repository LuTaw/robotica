import lejos.nxt.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.robotics.navigation.*;

public class finalRoboto {
	
	public static void main(String[] args) throws Exception {

		ControladorPilot cp = ControladorPilot.getInstance();
		Behavior moverse = new Moverse(cp);
		Behavior paraRoboto = new ParaRobot();
		Behavior[] bArray = { paraRoboto, moverse };
		Button.waitForAnyPress();
		(new Arbitrator(bArray)).start(); 

  	}
}
