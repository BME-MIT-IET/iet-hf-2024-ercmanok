package tests;

public class TestPickUpFreePipeEnd extends TestBase
{
    /**8.2.10
     * Játékos felveszi egy csőnek az egyik végét a ciszternából.
     */
    public static void TestPickUpFreePipe()
    {
        System.out.println("Pick Up Free Pipe Test\n");

        mechanic.SetCurrentPosition(pump);
        pump.AcceptPlayer(mechanic);
        System.out.println("Szerelő a pump.-n majd ellenőrzése, hogy rendelkezik-e pipe-al az inventory-ában.");
        System.out.println("Null értéket várunk");
		System.out.println(mechanic.GetPipeInInventory() == null ? "null" : "Hamis, van értéke");

        System.out.println("Pipe lecsatlakoztatása után ellenőrizzük, hogy még mindig null értékű a nála pipe (ha van nála).");
        mechanic.DisconnectNeighbourPipe(1);
        System.out.println("Nem egyező értéket várunk");
		System.out.println(mechanic.GetPipeInInventory() == null ? "Egyeznek" : "Nem egyeznek");
    }
}
