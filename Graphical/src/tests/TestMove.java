package tests;

public class TestMove extends TestBase
{
	/**
	 * 8.2.5
	 * Mozgások ellenőrzése,
	 * 1. az alapmozgás ellenőrzése.
	 */
	public static void Test_defaultMove()
	{
		System.out.println("DefaultMove Test\n");

		mechanic.SetCurrentPosition(spring);
		spring.AcceptPlayer(mechanic);
		System.out.println("A szerelő Spring-re állítása. Majd ellenőrzése, hogy ott van-e");
		System.out.println("Igaz értéket várunk");
		System.out.println(spring.GetId().equals(mechanic.GetCurrentPosition().GetId()) ? "Igaz" : "Hamis");
		
		mechanic.Move(0);
		System.out.println("A szerelő a Spring 0. indexű szomszédjára mozgatása . Majd ellenőrzése, hogy a Spring-en van-e?");
		System.out.println("Hamis értéket várunk");
		System.out.println(spring.GetId().equals(mechanic.GetCurrentPosition().GetId()) ? "Igaz" : "Hamis");

		System.out.println("A szerelő a Spring 0. indexű szomszédjára mozgatása . Majd ellenőrzése, hogy a Pipe1-n van-e?");
		System.out.println("Igaz értéket várunk");
		System.out.println(pipe1.GetId().equals(mechanic.GetCurrentPosition().GetId()) ? "Igaz" : "Hamis");

		System.out.println("A szerelő pumpára mozgatjuk, majd ellenőrizzük, hogy ott van-e.");
		mechanic.Move(mechanic.GetCurrentPosition().GetNeighbours().indexOf(pump));
		System.out.println("Igaz értéket várunk");
		System.out.println(pump.GetId().equals(mechanic.GetCurrentPosition().GetId()) ? "Igaz" : "Hamis");
	}

	/**
	 * Rossz indexre mozgatás
	 */
	public static void Test_wrongIndex()
	{
		System.out.println("WrongIndex Test\n");

		mechanic.SetCurrentPosition(pump);
		pump.AcceptPlayer(mechanic);
		System.out.println("A szerelőt a pumpára állítjuk majd ellenőrizzük, hogy ott van-e");
		System.out.println("Igaz értéket várunk");
		System.out.println(pump.GetId().equals(mechanic.GetCurrentPosition().GetId()) ? "Igaz" : "Hamis");
		
		mechanic.Move(1);
		System.out.println("A szerelőt a pipe2-re mozgatjuk  majd ellenőrizzük, hogy ott van-e");
		System.out.println("Igaz értéket várunk");
		System.out.println(pipe2.GetId().equals(mechanic.GetCurrentPosition().GetId()) ? "Igaz" : "Hamis");

		System.out.println("A szerelőt a pipe2 -1. indexére mozgatjuk  majd ellenőrizzük, hogy ott van-e");
		System.out.println("Hamis értéket várunk");
		System.out.println(mechanic.Move(-1) ? "Igaz" : "Hamis");

		System.out.println("A szerelőt a pipe2-ről próbáljuk visszamozgatni a spring-re  majd ellenőrizzük, hogy ott van-e");
		System.out.println("Hamis értéket várunk");
		System.out.println(mechanic.Move(mechanic.GetCurrentPosition().GetNeighbours().indexOf(spring)) ? "Igaz" : "Hamis");
	}

	/**
	 * Több player ugyanazon aktív elemre mozgatásának tesztelése
	 */
	public static void Test_morePlayersOnActiveElement()
	{
		System.out.println("MOre Player On Active Element Test\n");

		saboteur.SetCurrentPosition(pump);
		pump.AcceptPlayer(saboteur);
		System.out.println("Szabotőr pumpára mozgatása sikeres.");
		mechanic.SetCurrentPosition(pipe2);
		pipe2.AcceptPlayer(mechanic);
		System.out.println("Szerelő pipe2-re mozgatása sikeres");
		mechanic.Move(0);
		System.out.println("A szerelőt a 0. indexére mozgatjuk ahol a szabotőr is áll. Ellenőrizzük, hogy ugyan azon a helyen vannak-e?");
		System.out.println("Igaz értéket várunk");
		System.out.println(saboteur.GetCurrentPosition().GetId().equals(mechanic.GetCurrentPosition().GetId()) ? "Igaz" : "Hamis");
	}

	/**
	 * Több player egy pipe-n való tesztelése.
	 */
	public static void Test_morePlayersOnPipe()
	{
		System.out.println("More Player On Pipe Test\n");

		saboteur.SetCurrentPosition(spring);
		spring.AcceptPlayer(saboteur);
		System.out.println("Szabotőr Springre állítása");
		saboteur.SetName("sab");
		mechanic.SetName("mec");
		mechanic.SetCurrentPosition(pump);
		System.out.println("Szerelő pumpára állítása");
		pump.AcceptPlayer(mechanic);
		saboteur.Move(0);
		mechanic.Move(0);
		System.out.println("Mind a kettő player pipe1 re mozgatásának probálkozása\nA szabotőr pipe-n van? ");
		System.out.println("Igaz értéket várunk");
		System.out.println(pipe1.GetId().equals(saboteur.GetCurrentPosition().GetId()) ? "Igaz" : "Hamis");
		System.out.println("A szerelő nem tud a pipe1-re mozogni mert foglalt. \n A szerelő a pumpán maradt?");
		System.out.println("Igaz értéket várunk");
		System.out.println(pump.GetId().equals(mechanic.GetCurrentPosition().GetId()) ? "Igaz" : "Hamis");
	}

	/**
	 *
	 */
	public static void Test_movePlayersToSamePipe()
	{
		System.out.println("MOve Players To Same Pipe Test\n");

		saboteur.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(saboteur);
		System.out.println("Szabotőr a pipe1-n.");
		mechanic.SetCurrentPosition(pump);
		pump.AcceptPlayer(mechanic);
		System.out.println("Szerelő pump-n.\nMajd mozgatási probálkozás a már foglalt pipe1-re.");
		mechanic.Move(0);
		System.out.println("Hamis értéket várunk");
		System.out.println(saboteur.GetCurrentPosition().GetId().equals(mechanic.GetCurrentPosition().GetId()) ? "Igaz" : "Hamis");
	}
}
