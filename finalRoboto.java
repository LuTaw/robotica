import lejos.nxt.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class finalRoboto {

	public static void main(String[] args) throws Exception {

		Behavior moverse = new Moverse();
		Behavior paraRoboto = new ParaRobot();
		Behavior[] bArray = {  moverse, paraRoboto};
		Button.waitForAnyPress();
		(new Arbitrator(bArray)).start(); 

  	}
}
