package game.elements;

import java.util.*;
import java.util.stream.Collectors;

import game.*;
import game.IO.*;
import game.interfaces.*;
import game.players.*;

public class Pipe extends Element implements ISteppable
{
    private boolean leaking = false;		//megadja, hogy a cső jelenleg ki van-e lyukasztva
    private int noLeakageTimer = 0;			//megadja, hogy a cső mennyi ideig nem lyukasztható
    private int slipperyTimer = 0;			//megadja, hogy a cső jelenleg csúszós-e
    private int stickyTimer = 0;			//megadja, hogy a cső jelenleg ragadós-e
    
    private ArrayList<ActiveElement> neighbours = new ArrayList<ActiveElement>();

    /**A Pipe osztály konstruktora.
     */
    public Pipe()
    {
    	GameManager.AddSteppable(this);
    	GameManager.AddPipe(this);
    	this.SetId("pipe" + GameManager.TryPipeIdSet());
    }
    
    /**Az osztály paraméteres konstruktora.
     * @param leaks lyukas tulajdonsága
     * @param timer nem lyukaszthatóság ideje
     * @param slippery csúszossága
     * @param sticky ragadós tulajdonsága
     * @param neighbours szomszédai
     */
    public Pipe(int water, boolean leaks, int timer, int slippery, int sticky, ArrayList<ActiveElement> neighbours, String id)
    {
    	SetWaterInside(water);
    	leaking = leaks;
    	noLeakageTimer = timer;
    	slipperyTimer = slippery;
    	stickyTimer = sticky;
    	if(neighbours != null)
    		this.neighbours = neighbours;
    	this.SetId(id);
    	GameManager.AddSteppable(this);
    	GameManager.AddPipe(this);
    }

    /**Visszaadja a neighbours.
     * @return aktív elem szomszédok.
     */
    public ArrayList<ActiveElement> GetNeighbours()
    {
    	return neighbours;
    }
    
    /**Visszaadja a leaking értékét.
     * @return lyukas vagy sem.
     */
    public boolean GetLeaking()
    {
    	return leaking;
    }
    
    /**Visszaadja a noLeakageTimer értékét.
     * @return meddig nem lyukasztható.
     */
    public int GetTimer()
    {
    	return noLeakageTimer;
    }
    
    /**Visszaadja a slipperyTime értékét.
     * @return csúszós vagy sem.
     */
    public int GetSlippery()
    {
    	return slipperyTimer;
    }    
    
    /**Beállít egy random, két érték közötti értéket a slipperyTimer-nek.
     */
    public void SetSlippery()
    {
    	slipperyTimer = new Random().nextInt(Constants.LeakageTimerBound, Constants.LeakageTimerBound + 1);
    }

    /**Visszaadja a stickyTimer értékét.
     * @return ragad vagy sem a cső.
     */
    public int GetSticky()
    {
    	return stickyTimer;
    }
    
    /**Beállítja a stickyTimer-t egy két érték közötti random értékre.
     */
    public void SetSticky()
    {
    	stickyTimer = new Random().nextInt(Constants.LeakageTimerBound, Constants.LeakageTimerBound + 1);
    }
    
    /**Visszaadja, hogy sikerült-e lerakni a pumpát a csőre.
     * @param pump lerakni kívánt pumpa
     * @return pumpa lerakásának sikeressége.
     */
    public boolean TryBuildPumpInto(Pump pump)
    {
    	return pump.GetBuildedInto(this);
    }

    /**Lyukas cső esetén befoltozza a csövet, majd beállítja a noLeakageTimer értékét.
     * @return cső javításának sikeressége.
     */
    public boolean TryRepair()
    {
        if (leaking)
        {
        	//itt állítódik be, hogy mennyi ideig nem lehet lyukasztani foltozás után
        	noLeakageTimer = new Random().nextInt(Constants.LeakageTimerBound, Constants.LeakageTimerBound + 1);
        	leaking = false;
        	System.out.println("Cső javítása sikeres volt." + noLeakageTimer + " ennyi ideig nem lyukasztható újra.\n");
            return true;
        }

        System.out.println("Cső javítása nem sikerül. Nincs elromolva ez az elem.\n");
        return false;
    }

    /**Ha nem lyukas a cső és a noLeakageTimer nulla, akkor a leaking értékét igazra állítja.
     * @return cső rongálásának sikeressége.
     */
    public boolean TryDamage()
    {
        if (!leaking && noLeakageTimer == 0)
        {
        	leaking = true;
        	DebugLog.WriteDebugLog("Cső rongálása sikeres volt. Lyukas lett.\n");
        	InfoLog.WriteInfoLog("Cső rongálása sikeres volt. Lyukas lett.\n");
        	System.out.println(this.GetId() + " rongálása sikeres volt. Lyukas lett.\n");
            return true;
        }
        DebugLog.WriteDebugLog("Cső rongálása nem sikerül. Már lyukas ez az elem.\n");
        InfoLog.WriteInfoLog("Cső rongálása nem sikerül. Már lyukas ez az elem.\n");
        System.out.println(this.GetId() + " rongálása nem sikerül. Már lyukas ez az elem.\n");
        return false;
    }

    /**Léptetéssel kapcsolatos értékek csökkentése egyel.
     * Lyukas vagy vég nélküli nem üres csőből a vizet a sivatagba engedi.
     * @return true, ha sikerült a vizet a sivatgba engedni.
     * @return false minden más esetben.
     */
    public boolean Step()
    {
    	if(noLeakageTimer > 0)
         	noLeakageTimer--;
    	
    	if(slipperyTimer > 0)
    		slipperyTimer--;
    	
    	if(stickyTimer > 0)
    		stickyTimer--;
    	
        if ((leaking || neighbours.size() < 2) && GetWaterInside() > 0)
        {
            WaterToDesert();

            return true;
        }
        
        return false;
    }

    /**Cső csőhöz csatlakoztatása.
     * @return nem lehetséges.
     */
    public boolean TryConnectPipe(Pipe pipeInInventory)
    {
        System.out.println("Jelenleg nem lehet csövet csőhöz csatlakoztatni.");
        return false;
    }

    /**Hozzáadja a paraméterben kapott aktív elemet a szomszédsági listájához. 
     * @param newNeighbour az új aktív elem (szomszéd) azonosítója.
     */
    public void AddNeighbour(ActiveElement newNeighbour)
    {
    	neighbours.add(newNeighbour);
    }

    /**Szabad csővég felvétele.
     * @return null nem lehet szabad csővég a csövön
     */
    public Pipe PickUpFreePipeEnd()
    {
        System.out.println("Jelenleg nem lehet szabad csővég a csövön.");
        return null;
    }

    /**Pumpa felvétele csövön állva.
     * @return null
     */
    public Pump PickUpPump()
    {
        System.out.println("Pumpát kizárólag valamelyik ciszternánál lehet felvenni..");
        return null;
    }

    /**Játékos adott csőre helyezése.
     * Ha nem áll más játéko a csövön, akkor ráléphet.
     * Vizsgálja a csúszósságot és a ragacsosságot, ezeknek megfelően jár el.
     * @return játékos csőre helyezésének sikeressége.
     */
    public boolean AcceptPlayer(Player player)
    {
        if (GetPlayers().size() < Constants.AcceptedPlayersInPipe)
        {
        	if(SlipperyPipe(player))
        	{
        		return false;
        	}
        	
            StickyPipe(player);
            	
            AddPlayer(player);
                    
           	return true; 
        }

        System.out.println("Cső nem tud fogadni, mert tele van. Válassz más műveletet.");
        return false;
    }

    /**Kimenet és bemenet vizsgálata.
     * @return false ugyanis a csőnek nincs ilyen tulajdonsága.
     */
    public boolean TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx)
    {
        System.out.println("Nem csinálunk semmit, a cső input/output nem állítható.");
        return false;
    }

    /**Szomszédos cső lecsatlakoztatáasa.
     * @return null nem lehet a cső szomszédját lecsatlakoztatni
     */
    public Pipe DisconnectNeighbourPipe(int neighbourIdx)
    {
        System.out.println("Nem csinálunk semmit, cső szomszédja nem lecsatlakoztatható.");
        return null;
    }

    /**A neighbours listából kiveszi a paraméterül kapott szomszédot.
     * @param neighbour a szomszéd
     */
    public void RemoveNeighbour(ActiveElement neighbour)
    {
    	neighbours.remove(neighbour);
    }
    
    /**A slipperyTimer értékének beállítása,
     * @return true ha az érték nulla, és sikerült beállítani.
     */
    public boolean TrySetSlippery()
    {
    	if(slipperyTimer == 0)
    	{
    		SetSlippery();
    		System.out.println("A cső csúszós lett.");
    		return true;
    	}    		
    	System.out.println("Nem sikerült csúszóssá tenni a csövet.");
    	return false;
    }
    
    /**A stickyTimer értékének beállítása,
     *@return true ha az értéke nulla, és sikerült beállítani.
     */
    public boolean TrySetSticky()
    {
    	if(stickyTimer == 0)
    	{
    		SetSticky();
    		System.out.println("A cső ragacsos lett.");
    		return true;
    	}
    		
    	System.out.println("Nem sikerült ragadóssá tenni a csövet.");
    	return false;
    }
    
    /**Csúszós cső általi műveletek.
     * Ha a cső csúszós, tehát a slipperyTimer nagyobb, mint nulla,
     * akkor a csőre csatlakoztatott elemek közül random segítségével a játékos elléptetése adott szomszédra
     * @param player a játékos.
     * @return a slipperyTimer nullához képesti vizsgálata.
     */
    public boolean SlipperyPipe(Player player)
    {
    	if(slipperyTimer > 0)
    	{
    		player.GetCurrentPosition().RemovePlayer(player);
    		this.GetNeighbours().get(new Random().nextInt(this.neighbours.size())).AcceptPlayer(player);
    		GameManager.ActionExecuted();
    		
    		System.out.println(player.GetCurrentPosition().GetId() + "-re került a " + player.GetName() + " játékos.");
    		System.out.println("Még ennyi ideig csúszós a cső: " + this.GetSlippery());
    	}
    	return slipperyTimer > 0;
    }
    
    /**Ragacsos cső általi műveletek.
     * Ha ragacsos a cső, akkor a rálépő játékos kizárása a játékkörből,
     * majd a ragacsosság megszüntetése a csövön.
     * @param player a játékos.
     * @return ragacsos a cső vagy sem.
     */
    public boolean StickyPipe(Player player)
    {
    	if(stickyTimer > 0)
    	{
    		player.Stuck();
        	stickyTimer = 0;
        	return true;
    	}

    	return false;
    }
    
    /**A vizmennyiség növelése.
     * @return true ha sikerül vizet fogadnia
     * @return false ha nem sikerül vizet fogadnia
     */
    public boolean FillWaterTo()
    {
        if (this.GetWaterInside() < Constants.PipeCapacity)
        {
            this.SetWaterInside(this.GetWaterInside() + 1);;
            
            if (leaking || neighbours.size() < 2)
            	WaterToDesert();            	
            
            return true;
        }

        return false;
    }
}