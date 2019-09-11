import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.util.Delay;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.PilotProps;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.DifferentialPilot;

import java.io.*; 

import java.util.Random;


public class ControladorPilot {

	private float AREA_WIDTH = 200F;
	private float AREA_LENGTH = 200F;

	private Waypoint wTarget;
	private Waypoint target;
	private static ControladorPilot cp;
	private DifferentialPilot pilot;
	private LegacyNavigator sn;
	private Navigator nav;
	private boolean tieneTubo = false;
	private boolean faltaTubo = false;
	private int colorActual = 6;
	private int colorAnterior = 6;
	private boolean encontrePlat = false;
	private boolean fatlaTuboDer = false;
	private boolean fatlaTuboIzq = false;

	public ControladorPilot() 
	{ 	
		pilot = new DifferentialPilot(1.8f, 5.2f, Motor.B, Motor.C, false);
		nav = new Navigator(pilot);
	}

	public static ControladorPilot getInstance() 
	{
		if (cp == null) 
		{
			cp = new ControladorPilot();
		}
		return cp;
	}

	public void getRandomPosition() 
	{
		if (cp == null) 
		{
			cp = getInstance();
		}
		wTarget = getTarget(Math.random() * AREA_WIDTH, Math.random() * AREA_LENGTH);
		nav.goTo(wTarget);
	}

	public boolean isPathCompleted() 
	{
		return nav.pathCompleted();
	}

	public void volverParaAtras() 
	{
		Motor.B.backward();
		Motor.C.backward();
	}

	public void stopPilot()
	{
		Motor.B.stop();
        	Motor.C.stop();
	}

	public void stop()
	{
		nav.stop();
	}

	public boolean getTieneTubo() 
	{
		return this.tieneTubo;
	}

	public void setTieneTubo(boolean value) 
	{
		 this.tieneTubo = value;
	}

	public int getColorActual() 
	{
		return this.colorActual;
	}

	public void setColorActual(int value) 
	{
		 this.colorActual = value;
	}

	public int getColorAnterior() 
	{
		return this.colorAnterior;
	}

	public void setColorAnterior(int value) 
	{
		 this.colorAnterior = value;
	}

	private Waypoint getTarget(double x, double y)
	{
    		target = new Waypoint(x, y);
		return target;
	}

	public boolean getFaltaTubo() {
		return faltaTubo;
	}

	public void setFaltaTubo(boolean ft) {
		faltaTubo = ft;
	}

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

	public boolean getEncontrePlataforma(){
		return this.encontrePlat;
	}

	public void setEncontrePlataforma(boolean value){
		this.encontrePlat = value;
	}

	public boolean getFaltaTuboDerecha(){
		return this.fatlaTuboDer;
	}

	public void setFaltaTuboDerecha(boolean value){
		this.fatlaTuboDer = value;
	}

	public boolean getFaltaTuboIzquierda(){
		return this.fatlaTuboIzq;
	} 

	public void setFaltaTuboIzquierda(boolean value){
		this.fatlaTuboIzq = value;
	}

	public void girar(Boolean lado, int tiempo){
		if (lado) {
			Motor.B.backward();
			Motor.C.forward();
			Delay.msDelay(tiempo);
			Motor.C.stop();
			Motor.B.stop();
		}
		else {
			Motor.C.backward();
			Motor.B.forward();
			Delay.msDelay(tiempo);
			Motor.C.stop();
			Motor.B.stop();
		}
	}

	public void polea(Boolean agarrarSoltar){
		if (agarrarSoltar) {
			Motor.A.forward();
			Delay.msDelay(100);
			Motor.A.stop();
		}
		else {
			Motor.A.backward();
			Delay.msDelay(100);
			Motor.A.stop();
		}
	}

}
