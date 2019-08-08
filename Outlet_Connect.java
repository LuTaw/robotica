import lejos.nxt.*;
import lejos.util.Delay;

public class Outlet_Connect {
	private static BTConnectTest2 pigFactory;
	/*private static Agarrar aga;
	private static Depositar dep;
	private static OpticalDistanceSensor ojoTubos = new OpticalDistanceSensor(SensorPort.S3);
	private static UltrasonicSensor ojoPlat = new UltrasonicSensor(SensorPort.S4);*/
	//private static LightSensor luzIzq = new LightSensor(SensorPort.S1);

	public static void main(String[] args) {

	//LightSensor luzIzq = new LightSensor(SensorPort.S1);

  pigFactory = new BTConnectTest2();
  pigFactory.start();
	/*aga = new Agarrar();
	aga.agarrar();
	System.out.println("fin de agarrar");
	//Delay.msDelay(5000);
	dep = new Depositar();
	dep.depositar();*/
		//ojoTubos.powerOn();
	/*	for (int i=0;i < 5; i++){
			System.out.println(luzIzq.readValue());
			Button.waitForAnyPress();
		}*/
  }
}
