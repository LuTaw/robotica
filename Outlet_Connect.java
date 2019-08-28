import lejos.nxt.*;
import lejos.util.Delay;
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
import lejos.nxt.addon.OpticalDistanceSensor;

import java.util.Random;

public class Outlet_Connect {
	private static BTConnectTest2 pigFactory;
	/*private static Agarrar aga;
	private static Depositar dep;
	private static OpticalDistanceSensor ojoTubos = new OpticalDistanceSensor(SensorPort.S3);
	private static UltrasonicSensor ojoPlat = new UltrasonicSensor(SensorPort.S4);*/
	//private static LightSensor luzIzq = new LightSensor(SensorPort.S1);

	private static OpticalDistanceSensor ojoTubos = new OpticalDistanceSensor(SensorPort.S3);
	private static UltrasonicSensor ojoPlat = new UltrasonicSensor(SensorPort.S4);

	public static void main(String[] args) throws Exception {

		pigFactory = new BTConnectTest2();
		
		System.out.println("antes de tomar las medidas del sensor ded distancia");
		int ot = pigFactory.getDistanceOjosTubos();
		System.out.println("ot value is: ");
		System.out.println(ot);
		
		int op = pigFactory.getDistanceOjosPlat();
		System.out.println("op value is: ");
		System.out.println(op);
		int opd = pigFactory.getDistanceOjosPlatDerecha();
		System.out.println("opd value is: ");
		System.out.println(opd);
		int opi = pigFactory.getDistanceOjosPlatIzquierda();
		System.out.println("opi value is: ");
		System.out.println(opi);

		Button.ENTER.waitForPressAndRelease();
 
/*
		PilotProps pp = new PilotProps();
		pp.loadPersistentValues();
		float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "5.7"));
		float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "14.5"));
		RegulatedMotor leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "C"));
		RegulatedMotor rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "B"));
		boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE, "false"));
		final RotateMoveController pilot = new DifferentialPilot(wheelDiameter, trackWidth, leftMotor, rightMotor, reverse);
		final LegacyNavigator sn = new LegacyNavigator(wheelDiameter, trackWidth, leftMotor, rightMotor);
		pilot.setRotateSpeed(180);

		Behavior AgarrarTubo = new Behavior() {
			
			public boolean takeControl() 
			{
				return (pigFactory.getDistanceOjosTubos() / 10) < 4; 
			}

			public void suppress() 
			{
				// ToDo: guardaar color en el que estamos para saber la medida del tubo
			}
				
			public void action() 
			{
				pilot.stop();
				Motor.A.rotateTo(700);	
			}
		};
	
		Behavior DejarTubo = new Behavior() {
			
			public boolean takeControl() 
			{
				return pigFactory.getDistanceOjosPlat() < 12;
			}
		
			public void suppress() {}

			public void action() 
			{
				// sleep por x tiempo que tenemos que ver despues para que avance hasta llegar a la plataforma para depositar  el tubo
				pilot.stop();
				Motor.A.rotateTo(-700, true);
				//Motor.A.resetTachoCount();
			}
		};


		Behavior Moverse = new Behavior() {
			public boolean takeControl() 
			{
				return false;
			}

			public void suppress() 
			{

			}

			public void action() 
			{

			}
			
		};

		Behavior DetectarTubo = new Behavior() {
			public boolean takeControl() 
			{
				return false;
			}

			public void suppress() 
			{

			}

			public void action() 
			{

			}
			
		};

		Behavior DectetarFaltaTubo = new Behavior() {
			public boolean takeControl() 
			{
				return false;
			}

			public void suppress() 
			{

			}

			public void action() 
			{

			}
			
		};

		Behavior MedirTamañoTuboFaltante = new Behavior() {
			public boolean takeControl() 
			{
				return false;
			}

			public void suppress() 
			{

			}

			public void action() 
			{

			}
			
		};

		Behavior EncontrarCanieria = new Behavior() {
			public boolean takeControl() 
			{
				return false;
			}

			public void suppress() 
			{

			}

			public void action() 
			{

			}
			
		};

		Behavior DetectarColor = new Behavior() {
			public boolean takeControl() 
			{
				return false;
			}

			public void suppress() 
			{

			}

			public void action() 
			{

			}
			
		};

		Behavior NoCaerseEnNegro = new Behavior() {
			public boolean takeControl() 
			{
				return false;
			}

			public void suppress() 
			{

			}

			public void action() 
			{

			}
			
		};

		Behavior VolverARumbo = new Behavior() {
			public boolean takeControl() 
			{
				return false;
			}

			public void suppress() 
			{

			}

			public void action() 
			{

			}
			
		};


		Behavior PararRobot = new Behavior() {
			public boolean takeControl() 
			{
				return false;
			}

			public void suppress() 
			{
				System.exit(0);
			}

			public void action() 
			{
				pilot.stop();
				System.exit(0);
			}
			
		};

		Behavior[] bArray = { NoCaerseEnNegro, AgarrarTubo, PararRobot, VolverARumbo, DetectarColor, EncontrarCanieria, MedirTamañoTuboFaltante, DectetarFaltaTubo, Moverse, DejarTubo };
		Button.waitForAnyPress();
		(new Arbitrator(bArray)).start();
*/
  	}
}
