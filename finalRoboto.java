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
		Behavior paraRoboto = new ParaRobot();
		Behavior[] bArray = { paraRoboto, NoCaerseEnNegro, BuscarColor, BuscarBlanco, BuscarVerde };
		Button.waitForAnyPress();
		(new Arbitrator(bArray)).start(); 

  	}
}
