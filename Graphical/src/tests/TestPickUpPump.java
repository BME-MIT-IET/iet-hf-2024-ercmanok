package tests;

public class TestPickUpPump extends TestBase
{
	/**8.2.13
     * Egy szerelő felvesz egy pumpát (a ciszternánál).
     */
	public static void TestPickPump()
    {
        System.out.println("Pick Up Pump Test\n");

        mechanic.SetCurrentPosition(cistern);
        cistern.AcceptPlayer(mechanic);
        System.out.println("Szerelő a cisternán.\nEllenőrizzük, hogy rendelkezik-e pumpával.");
        System.out.println("Null értéket várunk");
		System.out.println(mechanic.GetPumpInInventory() == null ? "null" : "Hamis, van értéke");
        
        mechanic.PickUpPump();
        System.out.println("A szerelő a cisternán állva felvesz egy pumpát.\nEllenőrizzük, hogy még mindig null értekű-e a nála lévő pumpa.");
        System.out.println("Nem egyező értéket várunk");
		System.out.println(mechanic.GetPumpInInventory() == null ? "Egyeznek" : "Nem egyeznek az értékek");
    }
}
