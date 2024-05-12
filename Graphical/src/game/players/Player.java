package game.players;

import game.*;
import game.IO.DebugLog;
import game.IO.InfoLog;
import game.interfaces.*;

public abstract class Player
{
    private String name;							//A játékos neve.
    private IElement currentPosition;				//A játékos pozíciója.

    /**A játékos nevének átadása külső osztályoknak.
     * @return a játékos neve.
     */
    public String GetName()
    {
    	return name;
    }
    
    /**A játékos nevének beállítása.
     * @param name, amire a játékos neve beállításra kerül.
     */
    public void SetName(String name)
    {
    	this.name = name;
    }
    
    /**A játékos pozíciójának átadása külső osztályoknak.
     * @return a játékos pozíciója.
     */
    public IElement GetCurrentPosition()
    {
    	return currentPosition;
    }
    
    /**A játékos pozíciójának beállítása-
     * @param newPos a játékos új pozíciója.
     */
    public void SetCurrentPosition(IElement newPos)
    {
    	currentPosition = newPos;
    }
    
    /**A játékos mozgatását megoldó függvény.
     * @param neighbourIdx a szomszédos elem indexe, ahova lépni szeretne a játékos.
     * @return a lépés sikeressége.
     */
    public boolean Move(int neighbourIdx)
    {
        if (currentPosition.GetNeighbours().size() > neighbourIdx && neighbourIdx >= 0)
        {
            IElement toNeighbour = GetCurrentPosition().GetNeighbours().get(neighbourIdx);
            if (toNeighbour.AcceptPlayer(this))
            {
            	currentPosition.RemovePlayer(this);
                currentPosition = toNeighbour;
            	GameManager.ActionExecuted();
            	return true;
            }
        }
        return false;
    }

    /**A játékos megpróbálja beállítani a pumpát.
     * @param neighbourIdxFrom a honnan ág indexe.
     * @param neighbourIdxTo a hova ág indexe.
     * @return a beállítás sikeressége.
     */
    public boolean TrySetPump(int neighbourIdxFrom, int neighbourIdxTo)
    {
        if (currentPosition.TrySetInputOutput(neighbourIdxFrom, neighbourIdxTo))
        {
            GameManager.ActionExecuted();
            return true;
        }

        return false;
    }
    
    /**A játékos megpróbálja kilyukasztani az alatta lévő csövet.
     * @return a lyukasztás sikeressége.
     */
    public boolean Damage()
    {
        if (currentPosition.TryDamage())
        {
            GameManager.ActionExecuted();
            return true;
        }
        
        return false;
    }
    
    /**A játékos megpróbálja ragacsossá tenni a csövet.
     * @return a ragacsossátétel sikeressége.
     */
    public boolean SetStickyPipe()
    {
    	if(currentPosition.TrySetSticky())
    	{
    		GameManager.ActionExecuted();
    		return true;
    	}
    	
    	return false;
    }
    
    /**A ragacsos csőre lépő játékos kimarad a további lépéseiből a körben.
     */
    public void Stuck()
    {
    	//Itt csak az adott köréből zárja ki a játékost, ha kell még további körökből is kizárni, akkor azt valahogy le kellene tárolni kb
    	System.out.println("A " + this.GetName() + " játékos rálépett egy ragacsos csőre. Kimarad a köréből.");
    	GameManager.SetPlayerAction(Constants.ActionInRoundPerUser);
    }

    /**A játékos körből való kihagyása
     */
    public void Pass()
    {
    	System.out.println(this.GetName() + " játékos passzolt.");
    	for(int i = GameManager.GetPlayerAction(); i < Constants.ActionInRoundPerUser; i++)
    	{
    		GameManager.ActionExecuted();
    	}
    	GameManager.SetPlayerAction(Constants.ActionInRoundPerUser);
    }
    
    /**A játék bezárása
     */
    public void Exit()
    {
    	DebugLog.WriteOutDebugLog();
    	InfoLog.WriteOutInfoLog();
    	System.exit(0);
    }
    
    /**
     */    
    public abstract String GetType();
}
