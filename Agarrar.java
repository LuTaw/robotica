import lejos.nxt.Motor;
import lejos.util.Delay;

public class Agarrar
{
    public Agarrar() {}

    public void agarrar()
    {
	Motor.B.setSpeed(100);
        Motor.B.backward();
        Delay.msDelay(200);
	Motor.B.stop();
    }
}
