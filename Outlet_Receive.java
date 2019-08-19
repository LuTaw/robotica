import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.*;
import lejos.robotics.navigation.LegacyNavigator;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.PilotProps;
import lejos.nxt.TouchSensor;

import java.util.Random;

public class Outlet_Receive {
    private static BTReceive2 duckFactory;
 
    public static void main(String[] args) {
    	duckFactory = new BTReceive2();
        duckFactory.start();

Behavior DriveForward = new Behavior() {
				public boolean takeControl() {return false;}
				public void suppress() {}
				public void action() {}
		/*		public boolean takeControl() {
					if (light.readValue() <= 40) {
						System.out.println("Estamos en el driveForward al principio");
						System.out.println("=========");
					}
					return light.readValue() <= 40;
				}
	
				public void suppress() {
					pilot.stop();
				}
	
				public void action() {
					sn.stop();
					pilot.forward();
					while (light.readValue() <= 40)
						Thread.yield(); // // cuando hay linea
				}
		*/	};
	
			Behavior OffLine = new Behavior() {
				public boolean takeControl() {return false;}
				public void suppress() {}
				public void action() {}
		/*		private boolean suppress = false;
	
				public boolean takeControl() {
					if (light.readValue() > 40) {
						System.out.println("Estamos en el offline al principio");
						System.out.println("=========");
					}
					return light.readValue() > 40;
				}
	
				public void suppress() {
					suppress = true;
				}
	
				public void action() {
					sn.stop();
					int sweep = 10;
					while (!suppress) {
						pilot.rotate(sweep, true);
						while (!suppress && pilot.isMoving())
							Thread.yield();
						sweep *= -2;
					}
					pilot.stop();
					suppress = false;
				}
		*/	};


			Behavior AvoidObstacle = new Behavior() {
				public boolean takeControl() {return false;}
				public void suppress() {}
				public void action() {}
				
		/*		public boolean takeControl() {
					if (ultraSonic.getDistance() <= 15) {
						System.out.println("Estamos en el avoid obstacle take control");
						System.out.println("=========");
					}
					return ultraSonic.getDistance() <= 15;
				}
				
				public void suppress() {
					sn.stop();
				}
	
				public void action() {
	    				pilot.rotate(-60);
					pilot.stop();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ie) {
						Thread.currentThread().interrupt();
					}						
					
					isAvoidingObstacle = true;
					sn.arc(20,90);

				}
		*/	};

			Behavior DriveForward2 = new Behavior() {
				public boolean takeControl() {return false;}
				public void suppress() {}
				public void action() {}
		/*		public boolean takeControl() {
					if ((light.readValue() <= 40 && isAvoidingObstacle)) {
						System.out.println("Estamos en el take control driveForward2");
						System.out.println("=========");
					}
					return (light.readValue() <= 40 && isAvoidingObstacle);
				}
	
				public void suppress() {
					sn.stop();
				}
	
				public void action() {
					isAvoidingObstacle = false;
	    				pilot.rotate(-60);
					pilot.stop();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ie) {
						Thread.currentThread().interrupt();
					}						
					
					sn.arc(20,90);
				}
		*/	};
			
			


			Behavior stopRobot = new Behavior() {
				public boolean takeControl() {return false;}
				public void suppress() {}
				public void action() {}

               		/*	public boolean takeControl() {
					return touchSensor.isPressed();
				}
				
				public void suppress() {
					System.exit(0);
				}
	
				public void action() {
					pilot.stop();
					System.exit(0);
				}
		*/	};

		Behavior[] bArray = { OffLine, DriveForward, AvoidObstacle, DriveForward2, stopRobot };
		Button.waitForAnyPress();
		(new Arbitrator(bArray)).start();
    }
}
