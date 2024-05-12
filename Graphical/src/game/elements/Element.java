package game.elements;

import java.util.*;

import game.*;
import game.interfaces.*;
import game.players.*;

public abstract class Element implements IElement
{
    private int waterInside = 0;									//az elemben levő víz mennyisége, kezdetben ez nulla
    private String id = "";											//az elem azonosítója, kezdetben ez üres
    private ArrayList<Player> players = new ArrayList<Player>();	//az elemen álló játékosok listája

    /**Visszaadja az adott elemben levő víz mennyiségét.
     * @return víz mennyisége
     */
    public int GetWaterInside()
    {
    	return waterInside;
    }

    
    /**Beállítja az adott elemben levő víz mennyiségét.
     */
    public void SetWaterInside(int waterInside)
    {
    	this.waterInside = waterInside;
    }

    /**Visszaadja az eleme azonosítóját.
     * @return elem azonosító
     */
    public String GetId()
    {
    	return id;
    }
    
    /**Beállítja az elem azonosítóját a paraméterként kapottra.
     * @param newid az új azonosító
     */
    public void SetId(String newid)
    {
    	id = newid;
    }
    
    /**Visszaadja a players listát.
     * @return a players lista
     */
    public ArrayList<Player> GetPlayers()
    {
    	return players;
    }
    
    /**A players listához adja a playert.
     * @return felvétel sikeressége
     */
    public boolean AddPlayer(Player player)
    {
    	return players.add(player);
    }
    
    /**Töröl egy elemet a player listából.
     * @param player a törölni kívánt játékos
     * @return a törlés sikeressége
     */
    public boolean RemovePlayer(Player player)
    { 	
    	return players.remove(player);
    }
    
    /**A vizmennyiség növelése.
     * @return true ha sikerül vizet fogadnia
     * @return false ha nem sikerül vizet fogadnia
     */
    public boolean FillWaterTo()
    {
        if (waterInside < Constants.PipeCapacity)
        {
            waterInside++;

            return true;
        }

        return false;
    }

    /**Az adott elemben levő víz mennyiségét a sivatagba folyatja.
     * Majd az adott elemben levő víz mennyiségét nullára állítja.
     */
    public void WaterToDesert()
    {
        Desert.IncreaseWaterFromPipelineNetwork(waterInside);
        waterInside = 0;
    }

    public abstract boolean AcceptPlayer(Player player);
    public abstract Pipe DisconnectNeighbourPipe(int neighbourIdx);
    public abstract Pipe PickUpFreePipeEnd();
    public abstract Pump PickUpPump();
    public abstract boolean TryBuildPumpInto(Pump pump);
    public abstract boolean TryConnectPipe(Pipe pipeInInventory);
    public abstract boolean TryDamage();
    public abstract boolean TryRepair();
    public abstract boolean TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx);
}
