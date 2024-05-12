package tests;

import java.util.ArrayList;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

/**
 *A tesztek alapját biztosító osztály.
 * A teszt osztályok ennek a leszármazótjai.
 * Létrehozza a tesztekhez szükséges objektumokat, és felállít egy alap WS- pipe1- pump- pipe2 - Cistern  pályát a tesztelés miatt.
 * A pump2 a többi teszteseten használatos mint új pumpa.
 */
public class TestBase
{
	static GameManager gamem = new GameManager();
	static Cistern cistern = new Cistern();
	static Pipe pipe1 = new Pipe();
	static Pipe pipe2 = new Pipe();
	static Pump pump = new Pump();
	static Pump pump2 = new Pump();
	static WaterSpring spring = new WaterSpring();
	static ArrayList<IElement> map = new ArrayList<IElement>();
	
	static Saboteur saboteur = new Saboteur();
	static Mechanic mechanic = new Mechanic();
	
	public static void Init()
	{	
		spring.AddPipe(pipe1);
		
		pipe1.AddNeighbour(spring);
		pipe1.AddNeighbour(pump);
		
		pump.AddPipe(pipe1);
		pump.AddPipe(pipe2);
		
		pipe2.AddNeighbour(pump);
		pipe2.AddNeighbour(cistern);
		
		cistern.AddPipe(pipe2);
		
		System.out.println("Szomszédságok beállítva.");
		
		map.add(cistern);
		map.add(pipe1);
		map.add(pipe2);
		map.add(pump);
		map.add(pump2);
		map.add(spring);
	}
}
