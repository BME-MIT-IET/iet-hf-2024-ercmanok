package game.elements;

import java.util.*;

import game.*;
import game.interfaces.*;

public class Pump extends ActiveElement implements ISteppable
{
    private Pipe input = null;			//a pumpa bemeneti csöve
    private Pipe output = null;			//a pumpa kimeneti csöve
    private boolean broken = false;		//elromlott pumpa tulajdonság
    private int age = 0;
    
    /**Pump osztály konstruktora,
     * amely meghívja a GameManager osztály AddSteppables függvényét, ezzel hozzáadva magát a léptethető elemekhez.
     */
    public Pump()
    {
        GameManager.AddSteppable(this);
        GameManager.AddPump(this);
        this.SetId("pump" + GameManager.TryPumpIdSet());
    }
    
    public String GetType()
    {
    	return "pump";
    }
    
    public boolean GetBroken()
    {
    	return broken;
    }
    
    public Pipe GetInput()
    {
    	return input;
    }


    public Pipe GetOutput()
    {
    	return output;
    }

    /**Víz pumpálása adott irányba.
     * Ha a pumpában van elegendő víz, akkor kifele vagy befele pumpálja a vizet.
     * @return elromlott pumpa vagy sikeres pumpálás
     */
    public boolean Step()
    {
        var pumpWaterToOutputDone = false;
        var pumpWaterFromInputDone = false;
        var isBroken = false;
        
        if(!broken)
        {
            if (GetWaterInside() == Constants.PumpWaterCapacity )
            {
                pumpWaterToOutputDone = PumpWaterToOutput();
                pumpWaterFromInputDone = PumpWaterFromInput();
            }
        	if (GetWaterInside() < Constants.PumpWaterCapacity) pumpWaterFromInputDone = PumpWaterFromInput();
        	
            isBroken = GettingOlder();
        }

        return broken || isBroken || pumpWaterToOutputDone || pumpWaterFromInputDone;
    }

    /**Ha megfelelő indexeket kap a függvény,
     * akkor beállítja az indexeknek megfelelően a be-, és kimeneti értékeket.
     * @return beállítás sikeressége 
     */
    public boolean TrySetInputOutput(int neighbourIdxFrom, int neighbourIdxTo)
    {
        if (GetNeighbours().size() > neighbourIdxFrom && neighbourIdxFrom >= 0 
            && neighbourIdxTo < GetNeighbours().size() && neighbourIdxTo >= 0
            && neighbourIdxFrom != neighbourIdxTo)
        {
            input = this.neighbours.get(neighbourIdxFrom);
            output = this.neighbours.get(neighbourIdxTo);

            return true;
        }

        System.out.println("Pumpa átállítása sikertelen.");
        return false;
    }

    /**Ha van szabad kapacitása a tartálynak.
     * Ha a bejövő mennyiség nem nulla, akkor a bejövőn a vízmennyiséget csökkentjük annyival,
     * amennyivel amennyivel a puffertartály tartalmát növeltük. 
     * @return művelet sikeressége
     */
    public boolean PumpWaterFromInput()
    {
        if (input != null && input.GetWaterInside() > 0)
        {
            input.SetWaterInside(input.GetWaterInside() - 1);
            SetWaterInside(GetWaterInside() + 1);

            return true;
        }

        return false;
    }

    /**A bemeneti csőből a kimeneti csőbe juttatja a vizet.
     * @return művelet sikeressége
     */
    public boolean PumpWaterToOutput()
    {
        if (output != null)
        {
        	output.FillWaterTo();
        	SetWaterInside(GetWaterInside() - 1);
            return true;
        }

        return false;
    }

    /**A pumpa elromlásának a valószínűségét egy randommal dönti el.
     * Ha a random által elromlik a pumpa, akkor a vizet a sivatagba engedi.
     * @return pumpa állapota (hibás vagy sem)
     */
    private boolean GettingOlder()
    {
    	if((age >= Constants.PumpErrorProbability) && !broken)
    	{
    		GoWrong();
            return true;
    	}
    	
    	if(age < Constants.PumpErrorProbability)
    	{
    		for(int i = 0; i < GameManager.GetPumps().size(); i++)
    		{
    			if(this.GetId().compareTo("pump" + (i + 1)) == 0)
    				age += ((i % 5) + 1) + 4;
    		}
    	}
    	
        return false;
    }

    /**A vizet a sivatagba engedi és a víztartályt lenullázza.
     */
    private void GoWrong()
    {
    	System.out.println("A " + this.GetId() + " elromlott, javításra szorul!");
    	broken = true;
        Desert.IncreaseWaterFromPipelineNetwork(GetWaterInside());
        SetWaterInside(0);
    }
    
    /**A meghibásodott pumpa megjavítása.
     * @return true sikeres javítás esetén
     * @return false ha nem volt meghibásodva
     */
    public boolean TryRepair()
    {
        if (broken == true)
        {
            broken = false;
            age = 0;
            System.out.println("Pumpa javítása sikeres volt.\n");
            return true;
        }

        System.out.println("A pumpa nem romlott még el.\n");
        return false;
    }

    /**Az eltávolítandó elemre vonatkozó vizsgálatok után a szomszéd eltávolítása, 
     * valamint a szomszéd szomszédsági listájából a pumpa eltávolítása. 
     * @param neighbourIdx a szomszéd azonosítója
     * @return szomszéd cső azonosítója
     */
    public Pipe DisconnectNeighbourPipe(int neighbourIdx)
    {
    	if(GetNeighbours().get(neighbourIdx).GetPlayers().size() > 0) return null;
    	
        if (neighbourIdx < 0 || neighbourIdx >= GetNeighbours().size()) return null;
        
        Pipe neighbourtoDisconnect = this.neighbours.get(neighbourIdx);

        if (input == neighbourtoDisconnect || output == neighbourtoDisconnect) return null;

        RemovePipe(neighbourtoDisconnect);
        neighbourtoDisconnect.WaterToDesert();
        neighbourtoDisconnect.RemoveNeighbour(this);

        return neighbourtoDisconnect;
    }

    /**Új cső létrehozása.
     * @param pipe a létrehozandó cső
     * @return a létrehozás sikeressége
     */
    public boolean GetBuildedInto(Pipe pipe)
    {
        // Beépítésnél input/output beállítása nélkül kerül a pályára a pumpa, ezt állítani külön elemi művelet, itt nincs rá lehetőség.
    	String pipeId = "pipe" + GameManager.TryPipeIdSet();
    	
        Pipe newPipe = new Pipe(pipe.GetWaterInside(), pipe.GetLeaking(), pipe.GetTimer(), pipe.GetSlippery(), pipe.GetSticky(), new ArrayList<ActiveElement>(), pipeId);
        //egyik oladli szomszéd elem letárolási
        ActiveElement neighbour = pipe.GetNeighbours().get(0);
        //az új cső szomszédjainak beállítása
        newPipe.AddNeighbour(neighbour);
        newPipe.AddNeighbour(this);
        //a szomszéd elem értesítése a szomszéd változásról
        neighbour.AddPipe(newPipe);
        neighbour.RemovePipe(pipe);
        //az eredeti cső szomszédjainak kezelése
        pipe.RemoveNeighbour(neighbour);
        pipe.AddNeighbour(this);
        //az új pumpa szomszédjainak beállítása
        this.AddPipe(newPipe);
        this.AddPipe(pipe);

        return true;
    }
}