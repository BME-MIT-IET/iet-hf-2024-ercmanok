package game.interfaces;

public interface ISteppable extends IElement
{
	/**A léptethető elemek léptetése.
	 * A megvalósító osztályok kezelik a metódus törzsét, itt az nincs kezelve.
	 * @return a léptetés sikeressége.
	 */
	boolean Step();
}
