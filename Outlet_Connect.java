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

	/*private static OpticalDistanceSensor ojoTubos = new OpticalDistanceSensor(SensorPort.S3);
	private static UltrasonicSensor ojoPlat = new UltrasonicSensor(SensorPort.S4);*/
	private static final ColorSensor colorAdelante = new ColorSensor(SensorPort.S2);

	private boolean tieneTubo = false;
	private int colorAnterior = 0;
	private int colorActual = 0;
	private int medidaTuboAgarrado = 0;


	private final PilotProps pp = new PilotProps();
	private final float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "5.7"));
	private final float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "14.5"));
	private final RegulatedMotor leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "C"));
	private final RegulatedMotor rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "B"));
	private final boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE, "false"));
	private final RotateMoveController pilot = new DifferentialPilot(wheelDiameter, trackWidth, leftMotor, rightMotor, reverse);
	private final LegacyNavigator sn = new LegacyNavigator(wheelDiameter, trackWidth, leftMotor, rightMotor);


	public final void main(String[] args) throws Exception {

		pigFactory = new BTConnectTest2();

		pp.loadPersistentValues();
		pilot.setRotateSpeed(180);
		
/*		System.out.println("antes de tomar las medidas del sensor ded distancia");
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
*/

		// empieza agarrar tubo
		Behavior AgarrarTubo = new Behavior() {
			
			public boolean takeControl() 
			{
				return (pigFactory.getDistanceOjosTubos() / 10) < 4 && !tieneTubo; 
			}

			public void suppress() 
			{
				suppress = true;
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

		// estamos en verde y no tenemos tubo
		Behavior BuscarBlanco = new Behavior() {
			public boolean takeControl() 
			{
				return (colorAdelante.getColorID() == 6 && colorAnterior==2 && !tieneTubo);
			}

			public void suppress() 
			{
				System.exit(0);
			}

			public void action() 
			{
				while (colorAdelante.getColorID() == 3) 
				{
					this.moverseRandom();
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
				suppress = true; 
			}

			public void action() 
			{
				pilot.stop();
				this.mediaVuelta();
				while (colorAdelante.getColorID() == 2)
				{
					this.moverseRandom();
				}
				colorAnterior = colorAdelante.getColorID();
			}
			
		};

		// estamos en negro, no tenemos tubo y tenemos guardado el color anterior
		Behavior NoCaerseEnNegro = new Behavior() {
			public boolean takeControl() 
			{
				return (colorAdelante.getColorID() == 1);
			}

			public void suppress() 
			{
				suppress = true;
			}

			public void action() 
			{
				pilot.stop();
				this.mediaVuelta();
				while (colorAdelante.getColorID() == 1)
				{
					this.moverseRandom();
				}
			}
			
		};

		// estamos en color, no tenemos tubo
		Behavior EncontrarTubo = new Behavior() {
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

		// termina el agarrar tubo
	
		// empeiza depositar tubo

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
				return false;
			}

			public void suppress() 
			{

			}

			public void action() 
			{

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
				return pigFactory.getDistanceOjosPlat() > 12;
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

		Behavior[] bArray = { NoCaerseEnNegro, AgarrarTubo, PararRobot, BuscarAzul, BuscarVerdeConTubo, BuscarVerde, BuscarBlancoConTubo, BuscarBlanco, BuscarTuberiaPrincipal, EncontrarCanieria, MedirTamañoTuboFaltante, EncontramosEspacioLibre, DejarTubo, EncontrarTubo, BuscarColor, DoblarSiguiendoTuberia};
		Button.waitForAnyPress();
		(new Arbitrator(bArray)).start();

  	}

	private void moverse() 
	{
		pilot.forward();
	}

	private void moverseRandom() 
	{
		pilot.forward();
	}

	private void mediaVuelta() 
	{
		pilot.stop();
		pilot.rotate(180, true);
	}

	private void girarDerecha() 
	{
		pilot.stop();
		pilot.rotate(70);
	}

	private void girarIzquierda() 
	{
		pilot.stop();
		pilot.rotate(70);
	}
}
