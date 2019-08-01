import lejos.nxt.*;
import lejos.util.Delay;

public class Outlet_Connect {
	//private static BTConnectTest2 pigFactory;
	private static Agarrar aga;
	private static Depositar dep;

    public static void main(String[] args) {
    	//pigFactory = new BTConnectTest2();
        //pigFactory.start();
	aga = new Agarrar();
	aga.agarrar();
	System.out.println("fin de agarrar");
	//Delay.msDelay(5000);
	dep = new Depositar();
	dep.depositar();
	Button.waitForAnyPress();
    }
}
