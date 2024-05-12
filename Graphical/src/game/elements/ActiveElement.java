package game.elements;

import java.util.ArrayList;

import game.*;
import game.players.*;

public abstract class ActiveElement extends Element
{
	protected ArrayList<Pipe> neighbours = new ArrayList<Pipe>();		//az aktív elem szomszédos csöveinek listája
	
	/**Visszaadja a neighbours értékét 
	 * @return az aktív elem szomszédsági listája
	 */
	public ArrayList<Pipe> GetNeighbours()
	{
		return neighbours;
	}
	
	/**Beállítja a neighbours listát a paraméterben kapottra.
	 * @param neighbours a szomszéd lista
	 */
	public void setNeighbours(ArrayList<Pipe> neighbours)
	{
		this.neighbours = neighbours;
	}
	
	/**Hozzáad egy új csövet a szomszéd listához.
	 * @param p az új cső, amit hozzáad.
	 * @return a felvétel sikeressége.
	 */
	public boolean AddPipe(Pipe p)
	{
		return neighbours.add(p);
	}

	/**Törli a szomszéd listából az adott csövet.
	 * @param p a törlni kívánt cső.
	 * @return a törlés sikeressége.
	 */
	public boolean RemovePipe(Pipe p)
	{
		return neighbours.remove(p);
	}

	/**Az AddPlayer értékét adja vissza.
	 * @return a cső felvételének sikeressége.
	 */
	public boolean AcceptPlayer(Player player)
	{
		return AddPlayer(player);
	}
	
	/**Lecsatlakoztatja a szomszédos csövet az aktív elemről.
	 * @return a lecsatlakoztatott cső.
	 */
	public Pipe DisconnectNeighbourPipe(int neighbourIdx)
    {
    	if(GetNeighbours().get(neighbourIdx).GetPlayers().size() > 0) return null;
    	
        if (neighbourIdx < 0 || neighbourIdx >= GetNeighbours().size()) return null;
        	
        Pipe neighbourtoDisconnect = this.neighbours.get(neighbourIdx);

        RemovePipe(neighbourtoDisconnect);
        neighbourtoDisconnect.WaterToDesert();
        neighbourtoDisconnect.RemoveNeighbour(this);

        return neighbourtoDisconnect;
    }

	/**Aktív elemen állva nem lehet szabad csővéget felvenni. (csak csövön)
	 * @return null és szöveg.
	 */
	public Pipe PickUpFreePipeEnd()
	{
		// override-olni leszármazottakban, ott megvalósítani ha felvehető az adott
		// típuson szabad csővég.
		System.out.println("Nem csinálunk semmit, szabad csővég nem felvehető.");
		return null;
	}

	/**Aktív elemen állva nem lehet pumpát felvenni.
	 * @return null és szöveg.
	 */
	public Pump PickUpPump()
	{
		// override-olni leszármazottakban, ott megvalósítani ha felvehető az adott
		// típuson pumpa.
		System.out.println("Nem csinálunk semmit, szabad csővég nem felvehető.");
		return null;
	}

	/**Aktív elemen állva nem lehet a csőbe pumpát illeszteni. 
	 * @return false és szöveg.
	 */
	public boolean TryBuildPumpInto(Pump pump)
	{
		// override-olni leszármazottakban, ott megvalósítani ha be lehet építeni
		// pumpát.
		System.out.println("Nem csinálunk semmit, a pumpa nem beépíthető.");
		return false;
	}

	/**Aktív elemhez paraméterben kapott cső csatlakoztatása.
	 * @param pipe a csatlakoztatott cső.
	 * @return csatlakoztatás sikeressége.
	 */
	public boolean TryConnectPipe(Pipe pipe)
	{
		if (neighbours.size() < Constants.MaxNeighboursOfActiveElements)
		{
			pipe.AddNeighbour(this);
			this.AddPipe(pipe);
			
			return true;
		}

		return false;
	}

	/**Aktív elemet nem lehet rongálni.
	 * @return false és szöveg.
	 */
	public boolean TryDamage()
	{
		// override-olni leszármazottakban, ott megvalósítani ha tönkretehető.
		System.out.println("Nem csinálunk semmit, a pályaelem nem tönkretehető.");
		return false;
	}

	/**Aktív elemet nem lehet javítani.
	 * @return false és szöveg.
	 */
	public boolean TryRepair()
	{
		// override-olni leszármazottakban, ott megvalósítani ha javatható.
		System.out.println("Nem csinálunk semmit, a pályaelem nem javítható.");
		return false;
	}

	/**Aktív elemen állva nem lehet a pumpa ki- és bemenetét állítani.
	 * @return false és szöveg.
	 */
	public boolean TrySetInputOutput(int inputIdx, int outputIdx)
	{
		// override-olni leszármazottakban, ott megvalósítani ha beállítható.
		System.out.println("Nem csinálunk semmit, a pályaelem nem állítható.");
		return false;
	}
	
	/**Aktív elemen állva nem lehet a csövet csúszóssá tenni. 
	 *  @return false és szöveg.
	 */
	public boolean TrySetSlippery()
    {
		// override-olni leszármazottakban, ott megvalósítani ha beállítható.
    	System.out.println("Nem sikerült csúszóssá tenni a csövet.");
    	return false;
    }
    
	/**Aktív elemen állva nem lehet a csövet ragadóssá tenni. 
	 *  @return false és szöveg.
	 */
    public boolean TrySetSticky()
    {
    	// override-olni leszármazottakban, ott megvalósítani ha beállítható.
    	System.out.println("Nem sikerült ragadóssá tenni a csövet.");
    	return false;
    }
}
