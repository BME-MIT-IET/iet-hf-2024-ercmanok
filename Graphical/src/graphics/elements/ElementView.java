package graphics.elements;

import javax.swing.JLabel;
import javax.swing.JPanel;

import game.interfaces.*;

public abstract class ElementView
{
	protected int posX;
	protected int posY;
	
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	
	public ElementView()
	{
		//TODO
	}
	
	public abstract JLabel LoadImage();
}
