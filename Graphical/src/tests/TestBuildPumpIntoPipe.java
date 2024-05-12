package tests;

import java.util.*;

import game.*;

public class TestBuildPumpIntoPipe extends TestBase
{
	static Scanner scan = new Scanner(System.in);
	static String input = "";

    /** 8.2.12
     * Egy szerelő beépít egy pumpát a pipe1 cső közepébe.
     * A sikeres tesztet a Sikeres teszt felirat jezli,
     */
	public static void TestTryBuildPump()
	{
		System.out.println("TestBuildPumpIntoPipe");
		TestBase.Init();
		
		GameManager.SetCurrentMechanic(mechanic);
		mechanic.SetCurrentPosition(cistern);
		cistern.AcceptPlayer(mechanic);
		
		System.out.println(GameManager.GetCurrentMechanic().GetName() + " a " + GameManager.GetCurrentMechanic().GetCurrentPosition().GetId() + "-n");
		mechanic.SetPumpInInventory(pump2);
		
		mechanic.SetCurrentPosition(pipe2);
		pipe2.AcceptPlayer(mechanic);
		System.out.println(GameManager.GetCurrentMechanic().GetName() + " a " + GameManager.GetCurrentMechanic().GetCurrentPosition().GetId() + "-n");
		
		if(mechanic.BuildPumpIntoPipe())
			System.out.println("Sikeres teszt");
	}
		
    /**8.2.8
     * Egy játékos pumpát állít fel, majd ellenőrzi, hogy sikerült-e
     */
    public static void TestTryBuildPumpWithWaterFlow()
    {
        System.out.println("TryTestBuildPumpWithWaterFlow Test\n");
    	TestBase.Init();
        System.out.println("A szerelő megkapja a pump2-t");
        mechanic.SetCurrentPosition(pipe1);
        System.out.println("A szerelő a pipe1-n áll.");
        pipe1.AcceptPlayer(mechanic);
        mechanic.SetPumpInInventory(pump2);

        mechanic.BuildPumpIntoPipe();
        System.out.println("A szerelő beépítette a pumpát.");
        pump2.TrySetInputOutput(0, 1);
        pump.TrySetInputOutput(0, 1);
        System.out.println("A szerelő a beállította az újonnan beépített pumpát.");
        spring.FillNeighourPipes();
        pump2.GetNeighbours().get(0).Step();
        pump2.Step();
        pump2.Step();
        pipe1.Step();
        pump.Step();
        pump.Step();
        pipe2.Step();
        cistern.Step();
        System.out.println("A léptetés után az aktív elemekben a következő a vízmennyiség: ");
        System.out.println("Spring: " + spring.GetWaterInside());
        System.out.println("Pump2 új szom: " + pump2.GetNeighbours().get(0).GetWaterInside());
        System.out.println("Pumpa2: " + pump2.GetWaterInside());
        System.out.println("Pipe1: " + pipe1.GetWaterInside());
        System.out.println("Pump: " + pump.GetWaterInside());
        System.out.println("Pipe2: " + pipe2.GetWaterInside());        
        System.out.println("Cistern: " + cistern.GetWaterInside());
        System.out.println("Mechanic points: " + GameManager.GetMechanincsPoints());
        
        System.out.println("A ciszternába eljutott-e a víz, ha igen egyforma lesz az érték.");
        System.out.println("Egyforma értéket várunk");
		System.out.println(cistern.GetWaterInside() == 1 ? "Egyformák" : "Nem egyformák");
    }
}