package tests;

public class TestDisconnectNeighbourPipe extends TestBase
{
    /**.
     * 8.2.9
     * A szerelő lecsatlakoztat egy már csatlakoztatott csövet a pumpáról.
     * Sikeres teszt esetén előszőr 2 szomszédja van, majd 1.
     */
	public static void TestDisconnectNeighbourPipes()
	{
        System.out.println("DisconnectNeighbourPipes Test\n");

        mechanic.SetCurrentPosition(pump);
        System.out.println("A szerelő a pumpán.\n Jelenleg 2 szomszédjának ellenőrzése");
        System.out.println("Egyforma értéket várunk");
		System.out.println(pump.GetNeighbours().size() == 2 ? "Egyformák" : "Nem egyformák");
        
        mechanic.DisconnectNeighbourPipe(0);
        System.out.println("Lecsatlakoztatás után az egyetlen szomszéd ellenőrzése.");
        System.out.println("Egyforma értéket várunk");
		System.out.println(pump.GetNeighbours().size() == 1 ? "Egyformák" : "Nem egyformák");
    }
}
