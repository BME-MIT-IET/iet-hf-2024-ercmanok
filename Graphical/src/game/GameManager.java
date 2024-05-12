package game;

import java.util.*;

import game.IO.DebugLog;
import game.IO.InfoLog;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class GameManager
{
	private static int round = 0;																	//A kör száma.
	private static int mechanicsPoints = 0;															//A szerelők pontszáma.
	private static int saboteursPoints = 0;															//A szabotőrök pontszáma.
	private static ArrayList<IElement> map = new ArrayList<IElement>();								//A térképen lévő összes elem.
	private static ArrayList<Pipe> pipes = new ArrayList<Pipe>();									//A térképen lévő csövek listája.
	private static ArrayList<Pump> pumps = new ArrayList<Pump>();									//A térképen lévő pumpák listája.
	private static ArrayList<Cistern> cisterns = new ArrayList<Cistern>();							//A térképen lévő pumpák listája. 
    private static ArrayList<ISteppable> steppables = new ArrayList<ISteppable>();					//A léptethetők listája(ciszternák, csövek, pumpák).
    private static ArrayList<WaterSpring> waterSprings = new ArrayList<WaterSpring>();				//A vízforrások listája.
    private static ArrayList<Saboteur> saboteurs = new ArrayList<Saboteur>();						//A szabotőrök listája.
    private static ArrayList<Mechanic> mechanics = new ArrayList<Mechanic>();						//A szerelők listája.
    private static Mechanic currentMechanicPlayer = null;											//Az aktuális játékos.
    private static Saboteur currentSaboteurPlayer = null;											//Az aktuális játékos.
    private static int playerActionCountInCurrentRound = 0;											//Az aktuális játékos körben tett lépéseinek száma.
    
    /**Az aktuális körszám visszaadása.
     * @return az aktuális körszám.
     */
    public static int GetRound()
    {
    	return round;
    }
    
    /**Az aktuális körszám beállítása adott értékre.
     * @param round az adott érték.
     */
    public static void SetRound(int round)
    {
    	GameManager.round = round;
    }
    
    /**A szerelők ponjainak visszaadása.
     * @return a szerelők pontjai.
     */
    public static int GetMechanincsPoints()
    {
    	return mechanicsPoints;
    }
    
    /**A szerelők ponjainak beállítása adott értékre.
     * @param points az adott érték.
     */
    public static void SetMechanicsPoints(int points)
    {
    	mechanicsPoints = points;
    }
    
    /**A szabotőrök ponjainak visszaadása.
     * @return a szabotőrök pontjai.
     */
    public static int GetSaboteurPoints()
    {
    	return saboteursPoints;
    }
    
    /**A szabotőrök pontjainak beállítása adott értékre.
     * @param points az adott érték.
     */
    public static void SetSaboteursPoints(int points)
    {
    	saboteursPoints = points;
    }
    
    /**Térkép átadása külső osztályoknak.
     * @return a térkép elemeinek listája.
     */
    public static ArrayList<IElement> GetMap()
    {
    	return map;
    }
    
    /**Beállítja a térképet egy a kapott paraméter szerintire.
     * @param map a kapott térkép.
     */
    public static void SetMap(ArrayList<IElement> map)
    {
    	GameManager.map = map;
    }
    
    /**A szerelők listájának visszaadása.
     * @return a szerelők listája.
     */
    public static ArrayList<Mechanic> GetMechanics()
    {
    	return mechanics;
    }
    
    /**Beállítja a külső osztálytól kapott értékre a szerelők listáját.
     * @param mechanics a kapott érték.
     */
    public static void SetMechanics(ArrayList<Mechanic> mechanics)
    {
    	GameManager.mechanics = mechanics;
    }
    
    /**Adott szerelő karakter hozzáfűzése a szerelők listához.
     * @param mechanic a hozzáfűzendő szerelő.
     * @return a hozzáfűzés sikeressége.
     */
    public static boolean AddMechanic(Mechanic mechanic)
    {
    	return mechanics.add(mechanic);
    }

    /**A szabotőrök listájának visszaadása.
     * @return a szabotőrök listája.
     */
    public static ArrayList<Saboteur> GetSaboteurs()
    {
    	return saboteurs;
    }

    /**Beállítja a külső osztálytól kapott értékre a szerelők listáját.
     * @param saboteurs a kapott érték.
     */
    public static void SetSaboteurs(ArrayList<Saboteur> saboteurs)
    {
    	GameManager.saboteurs = saboteurs;
    }
    
    /**Egy szabotőr felvétele a szabotőrök listájához.
     * @param saboteur az adott szabotőr.
     * @return a felvétel sikeressége.
     */
    public static boolean AddSaboteur(Saboteur saboteur)
    {
    	return saboteurs.add(saboteur);
    }
    
    /**A léptethetők listájának átadása más osztályok felé.
     * @return a léptethetők listája.
     */
    public static ArrayList<ISteppable> GetSteppables()
    {
    	return steppables;
    }
    
    /**A léptethetők (ciszterna, cső, pumpa) listájának beállítása egy másik listából.
     * @param steppables a másik lista.
     */
    public static void SetSteppables(ArrayList<ISteppable> steppables)
    {
    	GameManager.steppables = steppables;
    }

    /**Adott léptethető elem(ciszterna, cső, pumpa) felvétele a léptethetők listájába.
     * @param steppable az adott elem.
     * @return a felvétel sikeressége.
     */
    public static boolean AddSteppable(ISteppable steppable)
    {
    	return steppables.add(steppable);
    }
    
    /**Adott léptethető elem(ciszterna, cső, pumpa) levétele a léptethetők listájából.
     * @param steppable az adott elem.
     * @return a levétel sikeressége.
     */
    public static boolean RemoveSteppable(ISteppable steppable)
    {
    	return steppables.remove(steppable);
    }
    
    /**Adott cső felvétele a csövek listájába.
     * @param pipe steppable az adott elem.
     * @return a felvétel sikeressége.
     */
    public static boolean AddPipe(Pipe pipe)
    {
    	return pipes.add(pipe);
    }
    
    /**Adott cső felvétele a csövek listájába.
     * @param pump steppable az adott elem.
     * @return a felvétel sikeressége.
     */
    public static boolean AddPump(Pump pump)
    {
    	return pumps.add(pump);
    }
    
    /**Adott cső felvétele a csövek listájába.
     * @param cistern steppable az adott elem.
     * @return a felvétel sikeressége.
     */
    public static boolean AddCistern(Cistern cistern)
    {
    	return cisterns.add(cistern);
    }
    
    public static ArrayList<Cistern> GetCisterns()
    {
    	return cisterns;
    }
    
    public static ArrayList<Pipe> GetPipes()
    {
    	return pipes;
    }
    
    public static ArrayList<Pump> GetPumps()
    {
    	return pumps;
    }
    
    /**A térképen lévő vízforrások listájának átadása más osztályok felé.
     * @return a vízforrások listája.
     */
    public static ArrayList<WaterSpring> GetWaterSprings()
    {
    	return waterSprings;
    }
        
    /**A térképen lévő vízforrások listájának beállítása adott listából. 
     * @param waterSprings az adott lista.
     */
    public static void SetWaterSprings(ArrayList<WaterSpring> waterSprings)
    {
    	GameManager.waterSprings = waterSprings;
    }
    
    /**A térképre adott vízforrás felhelyezése.
     * @param waterspring az adott vízforrás.
     * @return a felhelyezés sikeressége.
     */
    public static boolean AddWaterSpring(WaterSpring waterspring)
    {
    	return waterSprings.add(waterspring);
    }
    
    /**A térképről adott vízforrás levétele.
     * @param waterspring az adott vízforrás.
     * @return levétel sikeressége.
     */
    public static boolean RemoveWaterSpring(WaterSpring waterspring)
    {
    	return waterSprings.remove(waterspring);
    }
    
    /**Átadja az éppen aktuális szerelő játékost külső osztályoknak.
     * @return az aktuális szerelő.
     */
    public static Mechanic GetCurrentMechanic()
    {
    	return currentMechanicPlayer;
    }
    
    /**Az aktuális szerelő játékos beállítása adott játékos.
     * @param current az adott játékos.
     */
    public static void SetCurrentMechanic(Mechanic current)
    {
    	currentMechanicPlayer = current;
    }
    
    /**Átadja az éppen aktuális szabotőr játékost külső osztályoknak.
     * @return az aktuális szabotőr.
     */
    public static Saboteur GetCurrentSaboteur()
    {
    	return currentSaboteurPlayer;
    }
    
    /**Az aktuális szabotőr játékos beállítása adott játékos.
     * @param current az adott játékos.
     */
    public static void SetCurrentSaboteur(Saboteur current)
    {
    	currentSaboteurPlayer = current;
    }
    
    /**Adott karakter körbeli lépésszámának átadása más osztályoknak.
     * @return adott karakter lépésszáma
     */
    public static int GetPlayerAction()
    {
    	return playerActionCountInCurrentRound;
    }
    
    /**Adott karakter körbeli lépésszámának beállítása paraméterként kapott értékre.
	*/
    public static void SetPlayerAction(int count)
    {
    	playerActionCountInCurrentRound = count;
    }
    
    /**Adot karakter körbeli lépésszámának növelése.
     */
    public static void IncreasePlayerAction()
    {
    	playerActionCountInCurrentRound++;
    }
    
    /**Megkersi a legkisebb, még nem használt id-t a csövekből.
     * @return az adott id szám értéke.
     */
    public static int TryPipeIdSet()
    {
    	int id = 1;
    	for(int i = 0; i < pipes.size(); i++)
    	{
    		String piid = "pipe" + id;
    		if(pipes.get(i).GetId().compareTo(piid) == 0)
    			id++;
    	}
    	return id;
    }
    
    /**Megkersi a legkisebb, még nem használt id-t a pumpákból.
     * @return az adott id szám értéke.
     */
    public static int TryPumpIdSet()
    {
    	int id = 1;
    	for(int i = 0; i < pumps.size(); i++)
    	{
    		String puid = "pump" + id;
    		if(pumps.get(i).GetId().compareTo(puid) == 0)
    			id++;
    	}
    	return id;
    }
    
    /**Megkersi a legkisebb, még nem használt id-t a ciszternákból.
     * @return az adott id szám értéke.
     */
    public static int TryCisternIdSet()
    {
    	int id = 1;
    	for(int i = 0; i < cisterns.size(); i++)
    	{
    		String cid = "cistern" + id;
    		if(cisterns.get(i).GetId().compareTo(cid) == 0)
    			id++;
    	}
    	return id;
    }
    
    /**Megkersi a legkisebb, még nem használt id-t a vízforrásokból.
     * @return az adott id szám értéke.
     */
    public static int TryWaterSpringIdSet()
    {
    	int id = 1;
    	for(int i = 0; i < waterSprings.size(); i++)
    	{
    		String spid = "spring" + id;
    		if(waterSprings.get(i).GetId().compareTo(spid) == 0)
    			id++;
    	}
    	return id;
    }
    
    /**A játékot menetéért felelős függvény.
     * A modell adott lejátszott körszámig játszatja a játékot.
     * Ha minden karakter meglépte adott körre vonatkozó lépéseit, akkor növelődik.
     */
    public static void StartGame()
    {
    	System.out.println("\n A menü használata: "
				+ "\n A kívánt menüpont kiválasztása a hozzátartozó parancs leírásával,"
				+ "\n ha van további feltétel(a menüleírásban X és Y jelzi),"
				+ "\n akkor a szóköz után, elemenként szóközzel elválasztva írandó.\n"); 
    	
    	while (round < Constants.RoundNumber)
        {
    		System.out.println("\nCSAPATOK");
        	System.out.println("Szerelők:");
        	for(int i = 0; i < mechanics.size(); i++)
        	{
        		System.out.println("  " + mechanics.get(i).GetName() + " " + mechanics.get(i).GetCurrentPosition().GetId());
        	}
        	System.out.println("Szabotőrök:");
        	for(int i = 0; i < saboteurs.size(); i++)
        	{
        		System.out.println("  " + saboteurs.get(i).GetName() + " " + saboteurs.get(i).GetCurrentPosition().GetId());
        	}
        	
        	System.out.println("\n" + (round + 1) + ". Kör");
			System.out.println("___________________________________________________________________");
			
			System.out.println("\t\t\t(Szerelő csapat)");
            MechanicActions();
            
            System.out.println("\t\t\t(Szabotőr csapat)");
            SaboteurActions();
            SetRound(GetRound() + 1);
        }
    	
    	if(round == Constants.RoundNumber)
    	{
            InfoLog.WriteOutInfoLog();
            DebugLog.WriteOutDebugLog();
    		System.out.print("Gratulálunk a ");
    		System.out.println(saboteursPoints < mechanicsPoints ? "Szerelők nyertek!" : "Szabotőrök nyertek!");
    		System.out.println("A nyertes csapat tagjai:");
    		if(saboteursPoints < mechanicsPoints)
    		{
    			for(int i = 0; i < mechanics.size(); i++)
    				System.out.println(mechanics.get(i).GetName());
    		}
    		else if(saboteursPoints > mechanicsPoints)
    		{
    			for(int i = 0; i < saboteurs.size(); i++)
    				System.out.println(saboteurs.get(i).GetName());
    		}
    	}
    }
    
    /**Ha sikesen végrehajt egy játékos egy elemi akciót, akkor ez a függvény hívódik meg.
     * Növeli az adott játékos lépésszámát, valamint lépteti a vizet a rendszerben.
     */
    public static void ActionExecuted()
    {
    	IncreasePlayerAction();
        StepSteppables();
        FireSourceActions();
        //TODO: Logoló függvény,
    }
    
    /**A forrásokból a szomszédos elemekbe folyatja a vizet.
     * A források minden szomszédos elemébe(rákapcsolt cső) juttat vizet.
     */
    public static void FireSourceActions()
    {
    	for(int i = 0; i < waterSprings.size(); i++)
    	{
    		waterSprings.get(i).FillNeighourPipes();
    	}
    }
    
    /**A térkép összes - kivéve vízforrás - elemében történő vízfolyatás.
     */
    public static void StepSteppables()
    {
    	for(int i = steppables.size() - 1; i >= 0; i--)
    	{
        	steppables.get(i).Step();
    	}
    }
    
    /**A szerelő játékos karakterek lépéseinek menüje.
     */
    public static void MechanicActions()
    {
    	for(int i = 0; i < mechanics.size(); i++)
    	{
    		playerActionCountInCurrentRound = 0;
			SetCurrentMechanic(mechanics.get(i));
    		SetCurrentSaboteur(null);
    		
    		while (playerActionCountInCurrentRound < Constants.ActionInRoundPerUser)
    		{

            	System.out.println("\tMechanics' points: " + mechanicsPoints + "\t\tSaboteurs' points: " + saboteursPoints);
            	
            	System.out.println("\n" + mechanics.get(i).GetName() + " játékos köre, " + (playerActionCountInCurrentRound + 1) + ". akció");
                System.out.println("Position: " + mechanics.get(i).GetCurrentPosition().GetId() + "\n");
                System.out.println("Neighbors: \n ** - index name: players standing on it **");
                
                for(int j = 0; j < mechanics.get(i).GetCurrentPosition().GetNeighbours().size(); j++)
                {
                	int onTop = mechanics.get(i).GetCurrentPosition().GetNeighbours().get(j).GetPlayers().size();
                	System.out.print("    - " + j + " " + mechanics.get(i).GetCurrentPosition().GetNeighbours().get(j).GetId() + ": ");
                	System.out.print(onTop == 0 ? "No one stands on this element.\n" : onTop + " player stand(s) on this element.\n");
                }
                
    			System.out.println("\nLehetőségek:");
    			System.out.println("\tmove X - Mozgás, X szomszéd indexe, ahova mozogni szeretnél");
    			System.out.println("\trepair - Javítás");
    			System.out.println("\tpickfreepipe - Szabad csővég felvétele");
               	System.out.println("\tpicknewpump - Pumpa felvétele");
               	System.out.println("\tdroppump - Pumpa beépítése a csőbe");
               	System.out.println("\tconnectpipe - Csővég csatlakoztatása");
               	System.out.println("\tpickneighbour X - Szomszédos csővég felvétele. Az X a szomszéd indexe.");
               	System.out.println("\tsetpump X Y - Pumpa beállítása. Az X a kívánt input szomszéd indexe, Y a kívánt output szomszéd indexe.");
               	System.out.println("\tleakpipe - Cső lyukasztás");
               	System.out.println("\tstickypipe - Cső ragacsossá tétele");
               	System.out.println("\tpass - A kör kihagyása");
               	
               	try
               	{
               		Scanner reader = new Scanner(System.in);
                   	String userinput = reader.nextLine();
                   	
                   	switch (userinput.split(" ")[0])
                   	{
                   		case "move":
                   			int neighbourIdx = Integer.parseInt(userinput.split(" ")[1]);
                   			currentMechanicPlayer.Move(neighbourIdx);
                            break;
                        case "repair":
                        	currentMechanicPlayer.Repair();
                            break;
                        case "pickfreepipe":
                        	currentMechanicPlayer.PickUpFreePipeEnd();
                            break;
                        case "picknewpump":
                        	currentMechanicPlayer.PickUpPump();
                            break;
                        case "droppump":
                        	currentMechanicPlayer.BuildPumpIntoPipe();
                            break;
                        case "connectpipe":
                        	currentMechanicPlayer.ConnectPipe();
                            break;
                        case "pickneighbour":
                            neighbourIdx = Integer.parseInt(userinput.split(" ")[1]);
                            currentMechanicPlayer.DisconnectNeighbourPipe(neighbourIdx);
                            break;
                        case "setpump":
                            int neighbourIdxFrom = Integer.parseInt(userinput.split(" ")[1]);
                            int neighbourIdxTo = Integer.parseInt(userinput.split(" ")[2]);
                            currentMechanicPlayer.TrySetPump(neighbourIdxFrom, neighbourIdxTo);
                            break;
                        case "leakpipe":
                        	currentMechanicPlayer.Damage();
                        	break;
                        case "stickypipe":
                        	currentMechanicPlayer.SetStickyPipe();
                        	break;
                        case "pass":
                        	currentMechanicPlayer.Pass();
                        	break;
                        case "exit":
                        	currentMechanicPlayer.Exit();
                        	break;
                        default:
                            break;
                   	}
               	}
               	catch(Exception e)
               	{
               		System.err.println("Hibás menü bemenet!\n" + e);
               	}
    		}
    	}
    }

    /**A szabotőr játékos karakter lépéseinek menüje.
     */
    public static void SaboteurActions()
    {
    	for(int i = 0; i < saboteurs.size(); i++)
    	{
    		playerActionCountInCurrentRound = 0;
    		SetCurrentMechanic(null);
    		SetCurrentSaboteur(saboteurs.get(i));
    		
            while (playerActionCountInCurrentRound < Constants.ActionInRoundPerUser)
            {
            	System.out.println("\tMechanics' points: " + mechanicsPoints + "\t\tSaboteurs' points: " + saboteursPoints);
            	
            	System.out.println("\n" + saboteurs.get(i).GetName() + " játékos köre, " + (playerActionCountInCurrentRound + 1) + ". akció");
                System.out.println("Position: " + saboteurs.get(i).GetCurrentPosition().GetId() + "\n");
                System.out.println("Neighbors: \n ** - index name: players standing on it **");
                
                for(int j = 0; j < saboteurs.get(i).GetCurrentPosition().GetNeighbours().size(); j++)
                {
                	int onTop = saboteurs.get(i).GetCurrentPosition().GetNeighbours().get(j).GetPlayers().size();
                	System.out.print("    - " + j + " " + saboteurs.get(i).GetCurrentPosition().GetNeighbours().get(j).GetId() + ": ");
                	System.out.print(onTop == 0 ? "No one stands on this element.\n" : onTop + " player stand(s) on this element.\n");
                }
                
                System.out.println("\nLehetőségek:");
                System.out.println("\tmove X - Mozgás, X szomszéd indexe, ahova mozogni szeretnél");
                System.out.println("\tleakpipe - Maga alatt lévő cső lyukasztása");
                System.out.println("\tsetpump X Y - Pumpa beállítása. Az X a kívánt input szomszéd indexe, Y a kívánt output szomszéd indexe.");
                System.out.println("\tstickypipe - A cső ragacsossá tétele maga alatt");
                System.out.println("\tslipperypipe - A cső csúszóssá tétele");
                System.out.println("\tpass - A kör kihagyása");
                
                try
               	{
                	Scanner reader = new Scanner(System.in);
                   	String userinput = reader.nextLine();
                   	
                   	switch (userinput.split(" ")[0])
                    {
                    	case "move":
                    		int neighbourIdx = Integer.parseInt(userinput.split(" ")[1]);
                   			saboteurs.get(i).Move(neighbourIdx);
                    		break;
                        case "leakpipe":
                        	saboteurs.get(i).Damage();
                            break;
                        case "setpump":
                        	int neighbourIdxFrom = Integer.parseInt(userinput.split(" ")[1]);
                            int neighbourIdxTo = Integer.parseInt(userinput.split(" ")[2]);
                            saboteurs.get(i).TrySetPump(neighbourIdxFrom, neighbourIdxTo);
                            break;
                        case "stickypipe":
                        	saboteurs.get(i).SetStickyPipe();
                        	break;
                        case "slipperypipe":
                        	saboteurs.get(i).SetSlipperyPipe();
                        	break;
                        case "pass":
                        	saboteurs.get(i).Pass();
                        	break;
                        case "exit":
                        	saboteurs.get(i).Exit();
                        	break;
                        default:
                        	break;
                    }
               	}
                catch(Exception e)
                {
                	System.err.println("Hibás menü bemenet!\n" + e);
                }
            }
        }
    }
}