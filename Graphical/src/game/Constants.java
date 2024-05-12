package game;

public class Constants
{
	public static int AcceptedPlayersInPipe = 1;				//a csövön tartózkodó játékosok száma
	public static int ActionInRoundPerUser = 2;					//egy adott kör alatt a játékos végrehajtott feladatainak a száma
	public static int LeakageTimerBound = 10;					//lyukas cső időzítője
	public static int MaxNeighboursOfActiveElements = 4;		//az aktív elemekhez csatlakoztatott csövek maximum száma
	public static int PipeCapacity = 1;							//a cső térfogata
	public static int PumpErrorProbability = 150;				//a pumpa elromlásának valószínűsége
	public static int PumpWaterCapacity = 1;					//a pumpa kapacitása, mennyi vizet képes továbbítani
	public static int RoundNumber = 20;							//a játék köreinek a száma.
}