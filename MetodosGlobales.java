import lejos.nxt.*; 
import lejos.nxt.Motor;
import lejos.util.Delay;

public class MetodosGlobales {
	
	private static boolean tengoTubo;
	private static boolean faltaTubo;
	private static int colorAnterior; 
	//private static String estado;
	private static MetodosGlobales mg;

	private MetodosGlobales () {
		mg = new MetodosGlobales();
		tengoTubo = false;
		faltaTubo = false;
		colorAnterior = 6;
		//estado = "Buscar Colores";
	}

	public static MetodosGlobales getInstance() {
		if (mg == null) {
			mg = new MetodosGlobales();
		}
		return mg;
	}

	public boolean getTengoTubo() {
		return tengoTubo;
	}

	public boolean getFaltaTubo() {
		return faltaTubo;
	}

	public int getColorAnterior() {
		return colorAnterior;
	}
	
	/*public String getEstado() {
		return estado;
	}*/

	public void setTengoTubo(Boolean tt) {
		tengoTubo = tt;
	}

	public void setFaltaTubo(Boolean ft) {
		faltaTubo = ft;
	}

	public void setColorAnterior(int ca) {
		colorAnterior = ca;
	}
	
	/*public void setEstado(String est) {
		estado = est;
	}*/

	public void avanzar(){
		Motor.B.forward();
        Motor.C.forward();
	}

	public void detenerse(){
		Motor.B.stop();
        Motor.C.stop();
	}

	public void retroceder(){
		Motor.B.backward();
        Motor.C.backward();
	}

	public void girar(Boolean lado, int tiempo){
		if (lado) {
			Motor.B.backward();
			Motor.C.forward();
			Delay.msDelay(tiempo);
		}
		else {
			Motor.C.backward();
			Motor.B.forward();
			Delay.msDelay(tiempo);
		}
	}

	public void polea(Boolean agarrarSoltar){
		if (agarrarSoltar) {
			Motor.A.forward();
			Delay.msDelay(200);
		}
		else {
			Motor.A.backward();
			Delay.msDelay(200);
		}
	}
}