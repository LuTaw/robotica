import lejos.nxt.*;
public class Outlet_Connect {
	private static BTConnectTest2 pigFactory;
 
    public static void main(String[] args) {
    	pigFactory = new BTConnectTest2();
        pigFactory.start();
    }
}