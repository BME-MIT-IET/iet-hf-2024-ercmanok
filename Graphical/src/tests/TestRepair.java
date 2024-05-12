package tests;

public class TestRepair extends TestBase
{
	/**
	 * 8.2.2
	 * Egy szerelő megjavít egy kilyukasztott csövet.
	 */
	public static void TestRepairs()
	{
		System.out.println("Repairs Test\n");

		mechanic.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(mechanic);
		System.out.println("Szerelő pipe1-n.\nEllenőrizzük, hogy lyukas-e a cső.");
		System.out.println("Hamis értéket várunk");
		System.out.println(pipe1.GetLeaking() ? "Igaz" : "Hamis");
		System.out.println("Megrongáljuk a csövet.\n Ellenőrizzük, hogy lyukas-e");
		mechanic.Damage();
		System.out.println("Igaz értéket várunk");
		System.out.println(pipe1.GetLeaking() ? "Igaz" : "Hamis");
		System.out.println("pipe2 lyukas?");
		System.out.println("Hamis értéket várunk");
		System.out.println(pipe2.GetLeaking() ? "Igaz" : "Hamis");
		System.out.println("Javítási kisérlet.");
		mechanic.Repair();
		System.out.println("Igaz értéket várunk");
		System.out.println(pipe1.GetLeaking() ? "Igaz" : "Hamis");
		System.out.println("Szerelő pipe2-n és javítási kisérlet. Pipe2 nem lyukas.");
		mechanic.SetCurrentPosition(pipe2);
		pipe2.AcceptPlayer(mechanic);
		mechanic.Repair();
		System.out.println("Hamis értéket várunk");
		System.out.println(pipe2.GetLeaking() ? "Igaz" : "Hamis");
	}
}
