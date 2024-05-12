package tests;

import game.*;
import game.elements.*;

public class TestSticky extends TestBase
{
	/**
	 * 8.2.4
	 * Egy játékos ragadóssá tesz egy csövet.
	 */
	public static void TestSetSticky()
	{
		System.out.println("Set a Pipe Sticky Test\n");

		saboteur.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(saboteur);
		System.out.println("Szabotőr a pipe1-n\nEllenőrizzük, hogy a pipe1 az egy sima cső-e?");
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe1.GetSticky() == 0 ? "Egyformák" : "Nem egyformák");
		System.out.println("Szabotőr Sticky-vé tesz egy csövet.\nEllenőrizzük, hogy a pipe1 az egy sima cső-e");
		saboteur.SetStickyPipe();
		System.out.println("Nem egyforma értéket várunk");
		System.out.println(pipe1.GetSticky() == 0 ? "Egyformák" : "Nem egyformák");
		System.out.println("Ellenőrizzük, hogy a pipe1 az egy sima cső-e");
		saboteur.SetStickyPipe();
		System.out.println("Nem egyforma értéket várunk");
		System.out.println(pipe1.GetSticky() == 0 ? "Egyformák" : "Nem egyformák");
		System.out.println("Szabotőr pipe2-re mozgatása majd, ellenőrzése, hogy a pipe2 sima cső-e");
		saboteur.SetCurrentPosition(pipe2);
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe2.GetSticky() == 0 ? "Egyformák" : "Nem egyformák");
		System.out.println("Szabotőr Sticky-vé teszi a pipe2-t. Ellenőrizzük.");
		saboteur.SetStickyPipe();
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe2.GetSticky() == Constants.LeakageTimerBound - 1 ? "Egyformák" : "Nem egyformák");
	}
	
	public static void TestStickyFunction()
	{
		System.out.println("Sticky Function Test\n");

		//mini init
		Pipe pipe3 = new Pipe();
		Cistern cistern1 = new Cistern();
		cistern.AddPipe(pipe3);
		pipe3.AddNeighbour(cistern1);
		
		saboteur.SetCurrentPosition(pipe3);
		pipe3.AcceptPlayer(saboteur);
		GameManager.SetCurrentSaboteur(saboteur);
		GameManager.SetCurrentMechanic(null);
		
		mechanic.SetCurrentPosition(cistern1);
		cistern1.AcceptPlayer(mechanic);
		
		saboteur.SetStickyPipe();
		saboteur.Move(0);
		
		GameManager.SetCurrentSaboteur(null);
		GameManager.SetCurrentMechanic(mechanic);
		
		mechanic.Move(0);
		
		//A GameManager osztály ezt kezeli, itt ez külön nem hívódik meg, igy kézi átállítás lesz.
		GameManager.SetCurrentSaboteur(saboteur);
		GameManager.SetCurrentMechanic(null);
		
		System.out.println("A következőkben megnézzük, hogy melyik játékos következik.");
		System.out.println("Szerelő játékos következik?");
		System.out.println("Nem egyforma értéket várunk");
		System.out.println(GameManager.GetCurrentMechanic() == mechanic ? "Igen" : "Nem");
		System.out.println("Szabotőr játékos következik?");
		System.out.println("Egyforma értéket várunk");
		System.out.println(GameManager.GetCurrentSaboteur() == saboteur ? "Igen" : "Nem");
	}
}
