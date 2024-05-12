package tests;

import game.GameManager;

public class TestPumpWater extends TestBase
{
    /**
     * 8.2.7
     * A pumpák minden elemi akció után pumpálnak a beállításuk alapján.
     */
	public static void TestWaterPump()
    {
        System.out.println("Water Pump Test\n");

        mechanic.SetCurrentPosition(pump);
        pump.AcceptPlayer(mechanic);
        System.out.println("Szerelő a pumpán.");
        spring.FillNeighourPipes();
        pump.TrySetInputOutput(0, 1);
        System.out.println("pumpa input és output beállítása.");
        pipe1.Step();
        pump.Step();
        spring.FillNeighourPipes();
        pump.Step();
        pipe2.Step();
        cistern.Step();
        System.out.println("Egy kör után.A következő elemekben megtalálható víz mennyiség.");
        System.out.println("Pipe1: "+pipe1.GetWaterInside());
        System.out.println("Pumpa: "+pump.GetWaterInside());
        System.out.println("Pipe2: "+pipe2.GetWaterInside());
        System.out.println("Cistern: "+cistern.GetWaterInside());
        System.out.println("Mechanic points: "+GameManager.GetMechanincsPoints());
        System.out.println("Ellenőrizzük, hogy a cisternáig eljutott-e a víz.");
        System.out.println("Egyező értéket várunk");
		System.out.println(cistern.GetWaterInside() == 1 ? "Egyeznek" : "Nem egyeznek az értékek");
    }
}
