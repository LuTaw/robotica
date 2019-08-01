import lejos.nxt.Motor;
import lejos.util.Delay;

public class Depositar
{
    public Depositar() {}

    public void depositar()
    {
      System.out.println("Esto baja");
      //Motor.B.setSpeed(100);
      Motor.B.forward();
      Delay.msDelay(200);
	    Motor.B.stop();
    }
}
