package tests;

import game.elements.*;

public class TestConnectPipe extends TestBase
{
	/**8.2.11
     * A szerelő összecsatlakoztatja a csövet egy pumpával.
     * A sikeres teszt esetén a
     */
    public static void TestConnect()
    {
        System.out.println("TestConnect Test\n");
        Pipe pipe3 = new Pipe();
        Pump testPump1 = new Pump();
        Pump testPump2 = new Pump();
        mechanic.SetPipeInInventory(pipe3);
        mechanic.SetCurrentPosition(testPump1);
        testPump1.AcceptPlayer(mechanic);
        
        System.out.println("A szerelő a teszt pump1-en állva csatlakoztatja ahhoz a csövet.");
        mechanic.ConnectPipe();        
        mechanic.SetPipeInInventory(pipe3);
        
        System.out.println("A szerelő a teszt pump2-n állva csatlakoztatja ahhoz a csövet.");
        mechanic.SetCurrentPosition(testPump2);
        testPump2.AcceptPlayer(mechanic);
        mechanic.ConnectPipe();

        System.out.println("Amennyiben sikeres volt a cső lehelyezése az új cső két szomszédja pump és pump 1");
        System.out.println(pipe3.GetNeighbours().get(0).GetId() + " " + pipe3.GetNeighbours().get(1).GetId());
        System.out.println("A két szomszéd étékének vizsgálata. Két egymás utáni igaz értéket várunk");
        System.out.println(pump.GetId().equals(pipe3.GetNeighbours().get(0).GetId()) ? "Igaz" : "Hamis");
        System.out.println(pump2.GetId().equals(pipe3.GetNeighbours().get(1).GetId()) ? "Igaz" : "Hamis");
    }
}
