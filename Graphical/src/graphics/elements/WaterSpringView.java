package graphics.elements;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.*;
import game.elements.*;

public class WaterSpringView extends ElementView
{
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	WaterSpring spring;
	
	JLabel springLabel;
	
	public WaterSpringView(int x, int y, int index)
	{
		posX = x;
		posY = y;
		spring = GameManager.GetWaterSprings().get(index);
		LoadImage();
	}
	
	public JLabel LoadImage()
	{
		try
		{
			springLabel = new JLabel();
			ImageIcon iSpring = new ImageIcon(this.getClass().getResource("/waterspring.png"));
			Image loadedImage = iSpring.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			iSpring = new ImageIcon(loadedImage);
			springLabel.setIcon(iSpring);
			return springLabel;
		}
		
		catch(Exception e)
		{
			springLabel = new JLabel("WaterSpring");
			springLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
			springLabel.setHorizontalAlignment(SwingConstants.LEFT);
			return springLabel;
		}
	}
}