package graphics.players;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.*;
import game.players.*;
import graphics.elements.*;

public class MechanicView extends PlayerView 
{
	private Mechanic mechanic;
	private JLabel mechanicLabel;
	
	public MechanicView(ElementView pos, int index)
	{
		this.pos = pos;
		mechanic = GameManager.GetMechanics().get(index);
		LoadImage();
	}
	
	public JLabel LoadImage()
	{
		try
		{
			mechanicLabel = new JLabel();
			ImageIcon  iMechanic= new ImageIcon(this.getClass().getResource("/mechanic.png"));
			Image loadedImage = iMechanic.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
			iMechanic = new ImageIcon(loadedImage);
			mechanicLabel.setIcon(iMechanic);
			return mechanicLabel;
		}
		
		catch (Exception e)
		{
			mechanicLabel = new JLabel(mechanic.GetName());
			mechanicLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
			return mechanicLabel;
		}
	}
}
