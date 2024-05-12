package game.elements;

import game.*;

public class WaterSpring extends ActiveElement
{
	/**A WaterSpring oztály konstruktora.
	 * Meghívja a GameManager osztály AddWaterSpring függvényét,
	 * ezzel hozzáadva magát a waterSprings listához.
	 */
    public WaterSpring() 
    {
        GameManager.AddWaterSpring(this);
        this.SetId("spring" + GameManager.TryWaterSpringIdSet());
    }
    
    public String GetType()
    {
    	return "spring";
    }    
    
    /**Megtölti a vízforrás szomszédos csöveit vízzel.
     */
    public void FillNeighourPipes()
    {    	
    	for(int i = 0; i < this.neighbours.size(); i++)
        {
    		this.neighbours.get(i).FillWaterTo();
        }
    }
}