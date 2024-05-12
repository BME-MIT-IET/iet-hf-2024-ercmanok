package graphics.players;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.*;
import game.players.*;
import graphics.elements.*;

public class SaboteurView extends PlayerView
{
	Saboteur saboteur;
	JLabel saboteurLabel;
	
	public SaboteurView(ElementView pos, int index)
	{
		this.pos = pos;
		saboteur = GameManager.GetSaboteurs().get(index);
		LoadImage();
	}
	
	public JLabel LoadImage()
	{
		try
		{
			saboteurLabel = new JLabel();
			ImageIcon  iSaboteur = new ImageIcon(this.getClass().getResource("/saboteur.png"));
			Image loadedImage = iSaboteur.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
			iSaboteur = new ImageIcon(loadedImage);
			saboteurLabel.setIcon(iSaboteur);
			return saboteurLabel;
		}
		catch (Exception e)
		{
			saboteurLabel = new JLabel(saboteur.GetName());
			saboteurLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
			return saboteurLabel;
		}
	}
}
