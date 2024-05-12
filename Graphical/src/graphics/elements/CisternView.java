package graphics.elements;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.*;
import game.elements.*;

public class CisternView extends ElementView
{
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	Cistern cistern;
	
	JLabel cisternLabel;
	
	public CisternView(int x, int y, int index)
	{
		//TODO
		posX = x;
		posY = y;
		cistern = GameManager.GetCisterns().get(index);
		LoadImage();
	}
	
	public JLabel LoadImage()
	{
		try
		{
			cisternLabel = new JLabel();
			ImageIcon iCistern = new ImageIcon(this.getClass().getResource("/cistern.png"));
			Image loadedImage = iCistern.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			iCistern = new ImageIcon(loadedImage);
			cisternLabel.setIcon(iCistern);
			return cisternLabel;
		}
		
		catch(Exception e)
		{
			cisternLabel = new JLabel("Cistern");
			cisternLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
			cisternLabel.setHorizontalAlignment(SwingConstants.LEFT);
			return cisternLabel;
		}
	}
}