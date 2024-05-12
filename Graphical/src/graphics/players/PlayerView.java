package graphics.players;

import javax.swing.JLabel;
import javax.swing.JPanel;

import game.interfaces.*;
import graphics.elements.*;

public abstract class PlayerView
{
	protected ElementView pos;
	//protected int posX;
	//protected int posY;
	
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	
	public PlayerView()
	{
		//TODO
	}
	
	public abstract JLabel LoadImage();
}
