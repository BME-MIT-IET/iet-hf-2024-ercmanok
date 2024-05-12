package game.players;

import game.*;

public class Saboteur extends Player
{
	/**A szabotőr osztály konstruktora.
	 * Hozzáadja a létrehozott szabotőrt, a GameManager osztály szabotőrök listájához.
	 */
    public Saboteur()
    {
        GameManager.AddSaboteur(this);
    }
    
    public String GetType()
    {
    	return "saboteur";
    }
    
    /**A szabotőr megpróbálja csúszóssá tenni az alatta lévő csövet
     * @return a csúszóssátétel sikeressége.
     */
    public boolean SetSlipperyPipe()
    {
    	if(GetCurrentPosition().TrySetSlippery())
    	{
    		GameManager.ActionExecuted();
    		return true;
    	}
    	return false;
    }
}