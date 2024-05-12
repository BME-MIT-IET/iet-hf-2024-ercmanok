package tests;

import game.*;

public class TestSlippery extends TestBase
{
	/**
	 * 8.2.3
	 * Egy játékos csúszóssá tesz egy csövet.
	 */
	public static void TestSetSlippery()
	{
		System.out.println("Set a Pipe Slippery Test\n");

		saboteur.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(saboteur);
		System.out.println("Szabotőr a pipe1-n\nEllenőrizzük, hogy a pipe1 az egy sima cső-e?");
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe1.GetSlippery() == 0 ? "Egyformák" : "Nem egyformák");
		System.out.println("Szabotőr Slippery-vé tesz egy csövet.\nEllenőrizzük, hogy a pipe1 az egy sima cső-e");
		saboteur.SetSlipperyPipe();
		System.out.println("Nem egyforma értéket várunk");
		System.out.println(pipe1.GetSlippery() == 0 ? "Egyformák" : "Nem egyformák");
		System.out.println("Ellenőrizzük, hogy a LeakageTimerBound megegyezik-e a cső slippery-ével.");
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe1.GetSlippery() == Constants.LeakageTimerBound - 1 ? "Egyformák" : "Nem egyformák");
		System.out.println("Ellenőrizzük, hogy a pipe1 cső Slippery-e 0 -e ? ");
		saboteur.SetSlipperyPipe();
		System.out.println("Nem egyforma értéket várunk");
		System.out.println(pipe1.GetSlippery() == 0 ? "Egyformák" : "Nem egyformák");
		
		saboteur.SetCurrentPosition(pipe2);
		pipe2.AcceptPlayer(saboteur);
		System.out.println("Szabotőr pipe2 re mozgatjuk\nSlippery?");
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe2.GetSlippery() == 0 ? "Egyformák" : "Nem egyformák");

		saboteur.SetSlipperyPipe();
		System.out.println("Slippery lett. Tényleg?");
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe2.GetSlippery() == Constants.LeakageTimerBound - 1 ? "Egyformák" : "Nem egyformák");
	}
	
	public static void TestSlipperyFunction()
	{
		System.out.println("Slippery Function Test\n");

		TestBase.Init();
		
		saboteur.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(saboteur);
		
		mechanic.SetCurrentPosition(spring);
		spring.AcceptPlayer(mechanic);
		
		saboteur.SetSlipperyPipe();
		saboteur.Move(0);
		
		mechanic.Move(0);
		System.out.println("Vajon hova került a mechanic játékos?");
		System.out.println(mechanic.GetCurrentPosition().GetId() + " erre az elemere került a mechanic játkos!");
		System.out.println("Ha a mechanic jelenlegi helye valóban szomszédos az eredeti helyével, akkor igaz választ várunk.");
		System.out.println(mechanic.GetCurrentPosition().GetId() == spring.GetNeighbours().get(0).GetId() ? "Igaz" : "Hamis");
	}
}
