import lejos.nxt.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.robotics.navigation.*;

public class finalRoboto {
	
	public static void main(String[] args) throws Exception {

		ControladorPilot cp = ControladorPilot.getInstance();
		Behavior NoCaerseEnNegro = new NoCaerseEnNegro(cp);
		Behavior BuscarColor = new BuscarColor(cp);
		Behavior BuscarBlanco = new BuscarBlanco(cp);
		Behavior BuscarVerde = new BuscarVerde(cp);
		Behavior SeguirXIzq = new SeguirXIzq(cp);
		Behavior SeguirXDer = new SeguirXDer(cp);
		Behavior AgarrarTubo = new AgarrarTubo(cp);
		Behavior EncontrarTubo = new EncontrarTubo(cp);
		Behavior BuscarPlataforma = new BuscarPlataforma(cp);
		Behavior paraRoboto = new ParaRobot();
		Behavior[] bArray = { paraRoboto, NoCaerseEnNegro , BuscarColor, BuscarBlanco, BuscarVerde, EncontrarTubo, AgarrarTubo, /*, BuscarPlataforma/*, SeguirXIzq, SeguirXDer*/ };
		Button.waitForAnyPress();
		(new Arbitrator(bArray)).start(); 

  	}
}
