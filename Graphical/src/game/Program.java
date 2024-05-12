package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.elements.*;
import game.IO.*;
import game.interfaces.*;
import game.players.*;
import graphics.*;
import tests.TestBuildPumpIntoPipe;

public class Program
{
    public static void main(String[] args)
    {
        MenuFrame mFrame = new MenuFrame();
    	//CreateMap();
    	//GameManager.StartGame();
    }
    
    /**Egy statikus térképet létrehozó függvény.
     */
    public static void CreateMap()
    {
    	String[] counts = {"2", "3", "4", "5"};
    	Color color = Color.decode("#c9a77d");
    	
    	ArrayList<IElement> map = new ArrayList<IElement>();
    	
        Cistern cistern1 = new Cistern();
        Cistern cistern2 = new Cistern();
        Cistern cistern3 = new Cistern();
        
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        Pipe pipe4 = new Pipe();
        Pipe pipe5 = new Pipe();
        Pipe pipe6 = new Pipe();
        Pipe pipe7 = new Pipe();
        Pipe pipe8 = new Pipe();
        Pipe pipe9 = new Pipe();
        Pipe pipe10 = new Pipe();
        Pipe pipe11 = new Pipe();
        Pipe pipe12 = new Pipe();
        Pipe pipe13 = new Pipe();
        Pipe pipe14 = new Pipe();
        Pipe pipe15 = new Pipe();
        Pipe pipe16 = new Pipe();
        Pipe pipe17 = new Pipe();
        Pipe pipe18 = new Pipe();
        Pipe pipe19 = new Pipe();
        
        Pump pump1 = new Pump();
        Pump pump2 = new Pump();
        Pump pump3 = new Pump();
        Pump pump4 = new Pump();
        Pump pump5 = new Pump();
        Pump pump6 = new Pump();
        Pump pump7 = new Pump();
        Pump pump8 = new Pump();
        Pump pump9 = new Pump();
        Pump pump10 = new Pump();
        Pump pump11 = new Pump();
        
        WaterSpring spring1 = new WaterSpring();
        WaterSpring spring2 = new WaterSpring();
        
        cistern1.SetId("cistern1");
        cistern1.AddPipe(pipe19);
        map.add(cistern1);

        cistern2.SetId("cistern2");
        cistern2.AddPipe(pipe18);
        map.add(cistern2);
        
        cistern3.SetId("cistern3");
        cistern3.AddPipe(pipe15);
        map.add(cistern3);
        
        pipe1.SetId("pipe1");
        pipe1.AddNeighbour(spring1);
        pipe1.AddNeighbour(pump1);
        map.add(pipe1);

        pipe2.SetId("pipe2");
        pipe2.AddNeighbour(spring2);
        pipe2.AddNeighbour(pump2);
        map.add(pipe2);

        pipe3.SetId("pipe3");
        pipe3.AddNeighbour(spring1);
        pipe3.AddNeighbour(pump2);
        map.add(pipe3);

        pipe4.SetId("pipe4");
        pipe4.AddNeighbour(pump1);
        pipe4.AddNeighbour(pump3);
        map.add(pipe4);

        pipe5.SetId("pipe5");
        pipe5.AddNeighbour(pump2);
        pipe5.AddNeighbour(pump3);
        map.add(pipe5);
        
        pipe6.SetId("pipe6");
        pipe6.AddNeighbour(pump1);
        pipe6.AddNeighbour(pump4);
        map.add(pipe6);
        
        pipe7.SetId("pipe7");
        pipe7.AddNeighbour(pump4);
        pipe7.AddNeighbour(pump5);
        map.add(pipe7);

        pipe8.SetId("pipe8");
        pipe8.AddNeighbour(pump4);
        pipe8.AddNeighbour(pump6);
        map.add(pipe8);
        
        pipe9.SetId("pipe9");
        pipe9.AddNeighbour(pump3);
        pipe9.AddNeighbour(pump6);
        map.add(pipe9);
        
        pipe10.SetId("pipe10");
        pipe10.AddNeighbour(pump3);
        pipe10.AddNeighbour(pump7);
        map.add(pipe10);
        
        pipe11.SetId("pipe11");
        pipe11.AddNeighbour(pump5);
        pipe11.AddNeighbour(pump8);
        map.add(pipe11);
        
        pipe12.SetId("pipe12");
        pipe12.AddNeighbour(pump6);
        pipe12.AddNeighbour(pump8);
        map.add(pipe12);
        
        pipe13.SetId("pipe13");
        pipe13.AddNeighbour(pump6);
        pipe13.AddNeighbour(pump9);
        map.add(pipe13);
        
        pipe14.SetId("pipe14");
        pipe14.AddNeighbour(pump7);
        pipe14.AddNeighbour(pump9);
        map.add(pipe14);
        
        pipe15.SetId("pipe15");
        pipe15.AddNeighbour(pump8);
        pipe15.AddNeighbour(cistern3);
        map.add(pipe15);
        
        pipe16.SetId("pipe16");
        pipe16.AddNeighbour(pump9);
        pipe16.AddNeighbour(pump10);
        map.add(pipe16);
        
        pipe17.SetId("pipe17");
        pipe17.AddNeighbour(pump9);
        pipe17.AddNeighbour(pump11);
        map.add(pipe17);
        
        pipe18.SetId("pipe18");
        pipe18.AddNeighbour(pump10);
        pipe18.AddNeighbour(cistern2);
        map.add(pipe18);
        
        pipe19.SetId("pipe19");
        pipe19.AddNeighbour(pump11);
        pipe19.AddNeighbour(cistern1);
        map.add(pipe19);
        
        pump1.SetId("pump1");
        pump1.AddPipe(pipe1);
        pump1.AddPipe(pipe4);
        pump1.AddPipe(pipe6);
        pump1.TrySetInputOutput(0, 2);
        map.add(pump1);

        pump2.SetId("pump2");
        pump2.AddPipe(pipe2);
        pump2.AddPipe(pipe3);
        pump2.AddPipe(pipe5);        
        pump2.TrySetInputOutput(0, 2);
        map.add(pump2);

        pump3.SetId("pump3");
        pump3.AddPipe(pipe4);
        pump3.AddPipe(pipe5);
        pump3.AddPipe(pipe9);
        pump3.AddPipe(pipe10);
        pump3.TrySetInputOutput(1, 3);
        map.add(pump3);

        pump4.SetId("pump4");
        pump4.AddPipe(pipe6);
        pump4.AddPipe(pipe7);
        pump4.AddPipe(pipe8);
        pump4.TrySetInputOutput(0, 1);
        map.add(pump4);
        
        pump5.SetId("pump5");
        pump5.AddPipe(pipe7);
        pump5.AddPipe(pipe11);
        pump5.TrySetInputOutput(0, 1);
        map.add(pump5);
        
        pump6.SetId("pump6");
        pump6.AddPipe(pipe8);
        pump6.AddPipe(pipe9);
        pump6.AddPipe(pipe12);
        pump6.AddPipe(pipe13);
        pump6.TrySetInputOutput(1, 3);
        map.add(pump6);
        
        pump7.SetId("pump7");
        pump7.AddPipe(pipe10);
        pump7.AddPipe(pipe14);
        pump7.TrySetInputOutput(0, 1);
        map.add(pump7);
        
        
        pump8.SetId("pump8");
        pump8.AddPipe(pipe11);
        pump8.AddPipe(pipe12);
        pump8.AddPipe(pipe15);
        pump8.TrySetInputOutput(0, 2);
        map.add(pump8);
        
        pump9.SetId("pump9");
        pump9.AddPipe(pipe13);
        pump9.AddPipe(pipe14);
        pump9.AddPipe(pipe16);
        pump9.AddPipe(pipe17);
        pump9.TrySetInputOutput(1, 3);
        map.add(pump9);
        
        pump10.SetId("pump10");
        pump10.AddPipe(pipe16);
        pump10.AddPipe(pipe18);
        pump10.TrySetInputOutput(0, 1);
        map.add(pump10);
        
        pump11.SetId("pump11");
        pump11.AddPipe(pipe17);
        pump11.AddPipe(pipe19);
        pump11.TrySetInputOutput(0, 1);
        map.add(pump11);
        
        spring1.SetId("spring1");
        spring1.AddPipe(pipe1);
        spring1.AddPipe(pipe3);
        map.add(spring1);
        
        spring2.SetId("spring2");
        spring2.AddPipe(pipe2);
        map.add(spring2);

        GameManager.SetMap(map);
        /*
        Mechanic testM = new Mechanic();
        spring1.AcceptPlayer(testM);
        testM.SetCurrentPosition(spring1);
        testM.SetName("testM");
        pipe1.TrySetSlippery();
        */
        JPanel westPanel = MenuFrame.GetWestPanel();
        westPanel.setBackground(color);
        JLabel mechanicLabel = new JLabel("Mechanics' team");
        mechanicLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 32));
        	
        JLabel mechanicsCountQ = new JLabel("Hányan vannak a szerelők csapatában?");
        mechanicsCountQ.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        
        var mechanicsCountA = new JComboBox(counts);
        mechanicsCountA.setSelectedIndex(0);
        mechanicsCountA.setEditable(true);
        mechanicsCountA.setBackground(color);
        mechanicsCountA.setPreferredSize(new Dimension(50, 25));
        
        JButton mOkButton = new JButton("Ok");
        mOkButton.setBackground(color);
        mOkButton.setBorder(BorderFactory.createDashedBorder(color, 5, 5, 5, false));
        mOkButton.addActionListener
    	(
    		new ActionListener() 
    		{
    			int mCount = 0;
    			@Override
    			public void actionPerformed(ActionEvent e) 
    			{
    				String[] names = new String[5];
    				
    				if(mCount != 0) 
    				{
    					westPanel.removeAll();
    					westPanel.add(mechanicLabel);
    					westPanel.add(mechanicsCountQ);
    					westPanel.add(mechanicsCountA);
    					westPanel.add(mOkButton);
    					
    					ArrayList<Mechanic> temp = GameManager.GetMechanics();
    					
    					for(int i = 0; i < temp.size(); i++)
    						names[i] = temp.get(i).GetName();
    					
    					temp.clear();
    					GameManager.SetMechanics(temp);
    					
    					westPanel.revalidate();
    					westPanel.repaint();
    				}
    				
    				mCount = mechanicsCountA.getSelectedIndex() + 2;
    				
    				for(int i = 0; i < mCount; i++)
    	           	{
    	          		var mechanic = new Mechanic();
    	          		
    	           		JLabel mechanicNameLabel = new JLabel("Add meg a karakter nevét!");
    	           		mechanicNameLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
    	           		
    	           		JTextField mechanicNameText = new JTextField();
    	           		mechanicNameText.setPreferredSize(new Dimension(100, 20));
    	           		mechanicNameText.setText(names[i] == null ? "Mechanic_" + (i + 1) : names[i]);
    	           		mechanicNameText.setPreferredSize(new Dimension(100, 20));
    	           		
    	           		JButton setNameButton = new JButton("Set");
    	           		setNameButton.setBackground(color);
    	           		setNameButton.setBorder(BorderFactory.createDashedBorder(color, 5, 5, 5, false));
    	           		setNameButton.addActionListener
    	        		(
    	        			new ActionListener() 
    	        			{
    	        				@Override
    	        				public void actionPerformed(ActionEvent e) 
    	        				{
    	        					mechanic.SetName(mechanicNameText.getText());
    	        				}
    	        			}
    	           		);
    	           		mechanic.SetName(mechanicNameText.getText());
    	           		
    	           		int pos = new Random().nextInt(map.size() - 1);
    	           		while(map.get(pos).AcceptPlayer(mechanic) == false)
    	          			pos = new Random().nextInt(map.size() - 1);
    	           		mechanic.SetCurrentPosition(map.get(pos));
    	           		
    	           		westPanel.revalidate();
    	           		westPanel.repaint();
    	          		
    	           		westPanel.add(mechanicNameLabel);
    	           		westPanel.add(mechanicNameText);
    	           		westPanel.add(setNameButton);
    	           	}
    			}
    		}
    	);
        
        westPanel.add(mechanicLabel);
        westPanel.add(mechanicsCountQ);
        westPanel.add(mechanicsCountA);
        westPanel.add(mOkButton);
        
        MenuFrame.SetWestPanel(westPanel);
        
       	JPanel eastPanel = MenuFrame.GetEastPanel();
       	eastPanel.setBackground(color);
       	JLabel saboteurLabel = new JLabel("Saboteurs' team");
       	saboteurLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 32));
      	
      	JLabel saboteursCountQ = new JLabel("Hányan vannak a szerelők csapatában?");
       	saboteursCountQ.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
       	
       	var saboteursCountA = new JComboBox(counts);
      	saboteursCountA.setSelectedIndex(0);
       	saboteursCountA.setEditable(true);
       	saboteursCountA.setBackground(color);
       	saboteursCountA.setPreferredSize(new Dimension(50, 25));
       	
       	JButton sOkButton = new JButton("Ok");
       	sOkButton.setBackground(color);
       	sOkButton.setBorder(BorderFactory.createDashedBorder(color, 5, 5, 5, false));
       	sOkButton.addActionListener
   		(
   			new ActionListener() 
   			{
   				int sCount = 0;
   				
   				@Override
   				public void actionPerformed(ActionEvent e) 
  				{
   					String[] names = new String[5];
   					
   					if(sCount != 0) 
    				{
    					eastPanel.removeAll();
    					eastPanel.add(saboteurLabel);
    					eastPanel.add(saboteursCountQ);
    					eastPanel.add(saboteursCountA);
    					eastPanel.add(sOkButton);
    					
    					ArrayList<Saboteur> temp = GameManager.GetSaboteurs();
    					
    					for(int i = 0; i < temp.size(); i++)
    						names[i] = temp.get(i).GetName();
    					
    					temp.clear();
    					GameManager.SetSaboteurs(temp);
    					
    					eastPanel.revalidate();
    					eastPanel.repaint();
    				}
   					
  					sCount = saboteursCountA.getSelectedIndex() + 2;
  					
  					for(int i = 0; i < sCount; i++)
   		           	{
  		           		var saboteur = new Saboteur();
    	           		JLabel saboteurNameLabel = new JLabel("Add meg a karakter nevét!");
    	           		saboteurNameLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
    	           		
    	           		JTextField saboteurNameText = new JTextField();
   		           		saboteurNameText.setText("Saboteur_" + (i + 1));
   		           		saboteurNameText.setPreferredSize(new Dimension(100, 20));
   		           		
   		           		JButton setNameButton = new JButton("Set");
   		           		setNameButton.setBackground(color);
   		           		setNameButton.setBorder(BorderFactory.createDashedBorder(color, 5, 5, 5, false));
   		           		setNameButton.addActionListener
   		        		(
   		        			new ActionListener() 
 		        			{
   		        				@Override
   		        				public void actionPerformed(ActionEvent e) 
   		        				{
   		        					saboteur.SetName(saboteurNameText.getText());
   		        				}
   		        			}
   		           		);
   		           		saboteur.SetName(saboteurNameText.getText());
   		           		
   		           		int pos = new Random().nextInt(map.size() - 1);
   		           		while(map.get(pos).AcceptPlayer(saboteur) == false)
   		           			pos = new Random().nextInt(map.size() - 1);
   		           		saboteur.SetCurrentPosition(map.get(pos));
   		           		
  		           		eastPanel.revalidate();
  		           		eastPanel.repaint();
   		           		
  		           		eastPanel.add(saboteurNameLabel);
   		           		eastPanel.add(saboteurNameText);
   		           		eastPanel.add(setNameButton);
   		           	}
   				}
   			}
   		);
       	
       	eastPanel.add(saboteurLabel);
       	eastPanel.add(saboteursCountQ);
       	eastPanel.add(saboteursCountA);
       	eastPanel.add(sOkButton);
       	
       	MenuFrame.SetEastPanel(eastPanel);
       	//*/
    }
}