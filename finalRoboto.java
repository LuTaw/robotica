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

public class finalRoboto {

	/*private static Agarrar aga;
	private static Depositar dep;
	private static OpticalDistanceSensor ojoTubos = new OpticalDistanceSensor(SensorPort.S3);
	private static UltrasonicSensor ojoPlat = new UltrasonicSensor(SensorPort.S4);*/
	//private static LightSensor luzIzq = new LightSensor(SensorPort.S1);

	/*private static OpticalDistanceSensor ojoTubos = new OpticalDistanceSensor(SensorPort.S3);
	private static UltrasonicSensor ojoPlat = new UltrasonicSensor(SensorPort.S4);*/

	private static boolean tieneTubo = false;
	private static int colorAnterior = 0;
	private static int colorActual = 0;
	private static int medidaTuboAgarrado = 0;
	public static int AREA_WIDTH = 200;
	public static int AREA_LENGTH = 200;
	public static Waypoint target;


	public static void main(String[] args) throws Exception {

		ColorSensor colorAdelante = new ColorSensor(SensorPort.S2);
		OpticalDistanceSensor ojoTubos = new OpticalDistanceSensor(SensorPort.S4);
		UltrasonicSensor OjosDerechos = new UltrasonicSensor(SensorPort.S3);
		UltrasonicSensor OjosIzquierdos = new UltrasonicSensor(SensorPort.S1);
	

		PilotProps pp = new PilotProps();
		float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "5.7"));
		float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "14.5"));
		RegulatedMotor leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "B"));
		RegulatedMotor rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "C"));
		boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE, "false"));
		RotateMoveController pilot = new DifferentialPilot(wheelDiameter, trackWidth, leftMotor, rightMotor, reverse);
		Navigator nav = new Navigator(pilot);
		LegacyNavigator sn = new LegacyNavigator(wheelDiameter, trackWidth, leftMotor, rightMotor);
		pilot.setRotateSpeed(150);


		// empieza agarrar tubo
		Behavior AgarrarTubo = new Behavior() {
			
			public boolean takeControl() 
			{
				return ((ojoTubos.getDistance() / 10) == 12); 
			}

			public void suppress() 
			{
				//suppress = true;
			}
				
			public void action() 
			{
				// ToDo: Controlar la distancia minima de ojosTubo 
				// capaz que tenemos que detenernos antes y despues del pilot.Stop() hacer un pilot.rotate(x) para llegar a agarrar el tubo
				pilot.stop();
				tieneTubo = true;
				colorActual = colorAdelante.getColorID();
				Motor.A.rotateTo(700, true);
				while (colorActual == 6) 
				{
					pilot.rotate(-60);
					pilot.stop();
					colorActual = colorAdelante.getColorID();
				}
				if (colorActual == 2)
					medidaTuboAgarrado = 20;
				else if (colorActual == 5)
					medidaTuboAgarrado = 15;
				else if (colorActual == 4)	
					medidaTuboAgarrado = 10;
			}
		};

		// estamos en blanco y no tenemos tubo
		Behavior BuscarColor = new Behavior() {
			public boolean takeControl() 
			{
				return colorAdelante.getColorID() == 6 && !tieneTubo; 
			}

			public void suppress() {}

			public void action() 
			{
				colorAnterior = colorActual;
				colorActual = colorAdelante.getColorID();
				if (colorActual == 6 && colorAnterior != 0){
					double x_targ = Math.random() * AREA_WIDTH;
					double y_targ = Math.random() * AREA_LENGTH;
					moverseRandom(nav, x_targ, y_targ);
				} else {
					moverse(pilot);
				}
			}
			
		};


		// estamos en verde y no tenemos tubo
		Behavior BuscarBlanco = new Behavior() {
			public boolean takeControl() 
			{
				return (colorAdelante.getColorID() == 6 && colorAnterior==2 && !tieneTubo);
			}

			public void suppress() 
			{
				//System.exit(0);
			}

			public void action() 
			{
				while (colorAdelante.getColorID() == 3) 
				{
					double x_targ = Math.random() * AREA_WIDTH;
					double y_targ = Math.random() * AREA_LENGTH;
					moverseRandom(nav, x_targ, y_targ);
				}
				colorAnterior = colorAdelante.getColorID();
			}
			
		};

		// estamos en azul, no tenemos tubo y color anterior es verde
		Behavior BuscarVerde = new Behavior() {
			public boolean takeControl() 
			{
				return (!tieneTubo && colorAnterior == 3 && colorAdelante.getColorID() == 2);
			}

			public void suppress() 
			{
				//suppress = true; 
			}

			public void action() 
			{
				pilot.stop();
				mediaVuelta(pilot);
				while (colorAdelante.getColorID() == 2)
				{
					double x_targ = Math.random() * AREA_WIDTH;
					double y_targ = Math.random() * AREA_LENGTH;
					moverseRandom(nav, x_targ, y_targ);
				}
				colorAnterior = colorActual;
				colorActual = colorAdelante.getColorID();
			}
			
		};

		// estamos en negro, no tenemos tubo y tenemos guardado el color anterior 
		Behavior NoCaerseEnNegro = new Behavior() {
			private boolean suppress = false;
			public boolean takeControl() 
			{
				return (colorAdelante.getColorID() == 7);
			}

			public void suppress() 
			{
				//suppress = true;
			}

			public void action() 
			{
				/*nav.stop();
				mediaVuelta(pilot);
				double x_targ = Math.random() * AREA_WIDTH;
				double y_targ = Math.random() * AREA_LENGTH;
				moverseRandom(nav, x_targ, y_targ);*/
				
				nav.stop();
				int sweep = 10;
				while (!suppress && colorAdelante.getColorID() == 7) {
					System.out.println(colorAdelante.getColorID());
					pilot.rotate(sweep, true);
					while (!suppress && pilot.isMoving() && colorAdelante.getColorID() == 7)
						Thread.yield();
					sweep *= -2;
				}
				pilot.stop();
				suppress = false;
			}
			
		};
		Behavior Moverse = new Behavior() {
			public boolean takeControl() 
			{
				return (colorAdelante.getColorID() != 7);
			}

			public void suppress() 
			{
				//suppress = true;
			}

			public void action() 
			{
				System.out.println(colorAdelante.getColorID());
				double x_targ = Math.random() * AREA_WIDTH;
				double y_targ = Math.random() * AREA_LENGTH;
				moverseRandom(nav, x_targ, y_targ);
			}
			
		};

		// estamos en color, no tenemos tubo, pensarlo despues
		Behavior EncontrarTubo = new Behavior() {
			public boolean takeControl() 
			{
				return false;
			}

			public void suppress() 
			{
				//System.exit(0);
			}

			public void action() 
			{
				pilot.stop();
				System.exit(0);
			}
			
		};

		// termina el agarrar tubo
	
		// empeiza depositar tubo

		Behavior DejarTubo = new Behavior() {
			
			public boolean takeControl() 
			{
				return false;
			}
		
			public void suppress() 
			{
				//suppress = true;
			}

			public void action() 
			{
				pilot.forward();
				pilot.stop();
				Motor.A.rotateTo(-700, true);
				tieneTubo = false;
				medidaTuboAgarrado = 0;
			}
		};

		// Estamos en azul tenemos tubo y anterior es verde
		Behavior BuscarTuberiaPrincipal = new Behavior() {
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

		// estamos en color, tenemos tubo y color anterior no es verde
		// Probablemente sea el mismo BuscarBlanco que el anterior queda renombrado por las dudas
		Behavior BuscarBlancoConTubo = new Behavior() {
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

		// Estamos en blanco y tenemos tubo
		// probablemente sea el mismo buscarverde que el anterior pero queda renombrado por las dudas
		Behavior BuscarVerdeConTubo = new Behavior() {
			public boolean takeControl() 
			{
				return (colorAdelante.getColorID() == 6 && tieneTubo && (colorAnterior == 3 || colorAnterior == 5 || colorAnterior == 4));
			}

			public void suppress() 
			{
				// no es necesario hacer nada
			}

			public void action() 
			{
				while (colorAdelante.getColorID() == 6)
				{
					double x_targ = Math.random() * AREA_WIDTH;
					double y_targ = Math.random() * AREA_LENGTH;
					moverseRandom(nav, x_targ, y_targ);
				}
				colorAnterior = colorActual;
				colorActual = colorAdelante.getColorID();
			}
			
		};

		// Estamos en verde y tenemos tubo
		Behavior BuscarAzul = new Behavior() {
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

		// Estamos en azul, tenemos tubo y vimos espacio libre
		Behavior EncontramosEspacioLibre = new Behavior() {
			public boolean takeControl() 
			{
				return false;
			}

			public void suppress() {}

			public void action() 
			{

			}
			
		};

		// termina depositar tubo

		// Va incluido en detectar falta de tubo
		Behavior MedirTamañoTuboFaltante = new Behavior() {
			public boolean takeControl() 
			{
				return false;				
				//return (espacioLibre && tieneTubo);
			}

			public void suppress() 
			{

			}

			public void action() 
			{

			}
			
		};

		// Va incluido en detectar falta de tubo
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

		// se detiene completamente y apaga el roboto
		Behavior PararRobot = new Behavior() {
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
				pilot.stop();
				System.exit(0);
			}
			
		};

		//VER SI ES Behavier o Metodo
		//Tenemos tubo, estamos en azul, anterior verde, detectamos plataforma y no podemos avanzar
		Behavior DoblarSiguiendoTuberia = new Behavior() {
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
				
			}
			
		};

		Behavior[] bArray = { NoCaerseEnNegro, Moverse,/* BuscarColor,*/ PararRobot/*, AgarrarTubo, PararRobot, BuscarAzul, BuscarVerdeConTubo, BuscarVerde, BuscarBlancoConTubo, BuscarBlanco, BuscarTuberiaPrincipal, EncontrarCanieria, MedirTamañoTuboFaltante, EncontramosEspacioLibre, DejarTubo, EncontrarTubo, DoblarSiguiendoTuberia*/};
		//Button.waitForAnyPress();
		(new Arbitrator(bArray)).start(); 

  	}

	private static void moverse(RotateMoveController pilot) 
	{
		pilot.forward();
	}

	private static void moverseRandom(Navigator nav, double x, double y) 
	{
		
    		target = new Waypoint(x, y);
    		nav.goTo(target);
	}

	private static void mediaVuelta(RotateMoveController pilot) 
	{
		pilot.backward();
	}

	private static void girarDerecha(RotateMoveController pilot) 
	{
		pilot.stop();
		pilot.rotate(70);
	}

	private static void girarIzquierda(RotateMoveController pilot) 
	{
		pilot.stop();
		pilot.rotate(70);
	} 
}
