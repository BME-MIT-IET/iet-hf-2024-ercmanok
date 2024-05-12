package game.elements;

import game.GameManager;

public class Desert
{
	/**A sivatagban elfolyt víz mennyiségét a szabotőr csapat pontszámához adja
	 * @param water a víz mennyisége
	 */
    public static void IncreaseWaterFromPipelineNetwork(int water)
    {
    	GameManager.SetSaboteursPoints(GameManager.GetSaboteurPoints() + water);
    }
}
