package graphics.elements;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.*;
import game.elements.Pipe;

public class PipeView extends ElementView
{
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	private Pipe pipe;
	private JLabel pipeLabel;
	private ElementView[] neighbours = new ElementView[2];
	
	public PipeView(int x, int y, int index)
	{
		//TODO
		posX = x;
		posY = y;
		pipe = GameManager.GetPipes().get(index);
		LoadImage();
	}
	
	public JLabel LoadImage()
	{
		//talán itt még a méretet be kell állítani
		try
		{
			pipeLabel = new JLabel();
			if(pipe.GetWaterInside() == 0)
			{
				if(!pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_empty.png"));
						pipeLabel.setIcon(iPipe);
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_empty_slippery.png"));
						pipeLabel.setIcon(iPipe);
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_empty_sticky.png"));
						pipeLabel.setIcon(iPipe);
					}
				}
				
				else if(pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_empty.png"));
						pipeLabel.setIcon(iPipe);
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_empty_slippery.png"));
						pipeLabel.setIcon(iPipe);
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_empty_sticky.png"));
						pipeLabel.setIcon(iPipe);
					}
				}
			}
			
			else if(pipe.GetWaterInside() == 1)
			{
				if(!pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_full.png"));
						pipeLabel.setIcon(iPipe);
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_full_slippery.png"));
						pipeLabel.setIcon(iPipe);
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_full_sticky.png"));
						pipeLabel.setIcon(iPipe);
					}
				}
				
				else if(pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_full.png"));
						pipeLabel.setIcon(iPipe);
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_full_slippery.png"));
						pipeLabel.setIcon(iPipe);
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_full_sticky.png"));
						pipeLabel.setIcon(iPipe);
					}
				}
			}
			return pipeLabel;
		}
		
		catch(Exception e)
		{
			if(pipe.GetWaterInside() == 0)
			{
				if(!pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						pipeLabel = new JLabel("Pipe empty");
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						pipeLabel = new JLabel("Pipe empty slippery");
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						pipeLabel = new JLabel("Pipe empty sticky");
					}
				}
				
				else if(pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						pipeLabel = new JLabel("Pipe empty leaking");
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						pipeLabel = new JLabel("Pipe empty leaking slippery");
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						pipeLabel = new JLabel("Pipe empty leaking sticky");
					}
				}
			}
			
			else if(pipe.GetWaterInside() == 1)
			{
				if(!pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						pipeLabel = new JLabel("Pipe full");
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						pipeLabel = new JLabel("Pipe full slippery");
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						pipeLabel = new JLabel("Pipe full sticky");
					}
				}
				
				else if(pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						pipeLabel = new JLabel("Pipe full leaking");
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						pipeLabel = new JLabel("Pipe full leaking slippery");
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						pipeLabel = new JLabel("Pipe full leaking sticky");
					}
				}
			}
			pipeLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
			System.err.println(e);
			return pipeLabel;
		}
	}
}