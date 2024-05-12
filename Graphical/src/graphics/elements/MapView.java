package graphics.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

import graphics.*;
import graphics.elements.*;
import graphics.players.*;

/**Ez az osztály felel a játéktér megjelenítéséért.
 */
public class MapView extends JPanel
{
	//private BufferedImage mapImage;
	//private static int SQUARE_SIZE = 100;
	
	//private IElement[][] map;
	
	private Color color = Color.decode("#c9a77d");													//a háttérszín
	
	private ArrayList<IElement> map = new ArrayList<IElement>();									//a modell pályája
	private ArrayList<Mechanic> mechanics = new ArrayList<Mechanic>();
	private ArrayList<Saboteur> saboteurs = new ArrayList<Saboteur>();
	
	private Map mapView = new HashMap();
	
	private MechanicView currentMechanic;															//az aktuális szerelő megjelenítése
	private SaboteurView currentSaboteur;															//az aktuális szabotőr megjenenítése
	private ArrayList<CisternView> cisternsView = new ArrayList<CisternView>();						//a ciszternák megjelenítésere szolgáló lista
	private ArrayList<PipeView> pipesView = new ArrayList<PipeView>();								//a csövek megjelenítésére szolgáló lista
	private ArrayList<PumpView> pumpsView = new ArrayList<PumpView>();								//a pumpák megjelenítésére szolgáló lista
	private ArrayList<WaterSpringView> springsView = new ArrayList<WaterSpringView>();				//a vízforrások megjelenítésére szolgáló lista
	private ArrayList<MechanicView> mechanicsView = new ArrayList<MechanicView>();					//a szerelők megjelenítésére szolgáló lista
	private ArrayList<SaboteurView> saboteursView = new ArrayList<SaboteurView>();					//a szabotőrök megjelenítésére szolgáló lista
	//valami itt kell csinálni, hogy megjelenjenek az elemek
	
	public MapView()
	{
		//map = new IElement[10][10];
		map = GameManager.GetMap();
		mechanics = GameManager.GetMechanics();
		saboteurs = GameManager.GetSaboteurs();
		
		DrawMap();
		
		MouseListener();
	}
	
	//egyelőre csak ráfrissít a nézetekre...
	public void ReDraw()
	{
		for(int i = 0; i < pipesView.size(); i++)
		{
			this.add(pipesView.get(i).LoadImage());
		}
		
		for(int i = 0; i < pumpsView.size(); i++)
		{
			this.add(pumpsView.get(i).LoadImage());
		}
		
		for(int i = 0; i < cisternsView.size(); i++)
		{
			this.add(cisternsView.get(i).LoadImage());
		}
		
		for(int i = 0; i < springsView.size(); i++)
		{
			this.add(springsView.get(i).LoadImage());
		}	
	}
	
	//egyelőre csak felhelyezi a modellben inicializált elemeket, de nem adott helyre és nem is adott szomszédokhoz
	public void DrawMap()
	{
		this.setBackground(color);
		this.setPreferredSize(new Dimension(1000, 900));
		//this.setLayout(null);
		/* így lehet pozícióra rakni egy elemet
		PipeView testpiV = new PipeView(100, 100, 1);
		JLabel testPipe = testpiV.LoadImage();
		this.add(testPipe);
		Dimension size = testPipe.getPreferredSize();
		testPipe.setBounds(100, 100, size.width, size.height);
		*/
		for(int i = 0; i < GameManager.GetPipes().size(); i++)
		{
			PipeView piV = new PipeView(10, 10, i);
			pipesView.add(piV);
			this.add(piV.LoadImage());
		}
		
		for(int i = 0; i < GameManager.GetPumps().size(); i++)
		{
			PumpView puV = new PumpView(50, 50, i);
			pumpsView.add(puV);
			this.add(puV.LoadImage());
		}
		
		for(int i = 0; i < GameManager.GetCisterns().size(); i++)
		{
			CisternView cV = new CisternView(10, 10, i);
			cisternsView.add(cV);
			this.add(cV.LoadImage());
		}
		
		for(int i = 0; i < GameManager.GetWaterSprings().size(); i++)
		{
			WaterSpringView sV = new WaterSpringView(10, 10, i);
			springsView.add(sV);
			this.add(sV.LoadImage());
		}
		/*
		for(int i = 0; i < GameManager.GetMechanics().size(); i++)
		{
			GameManager.GetMechanics().get(i).GetCurrentPosition();
			MechanicView mV = new MechanicView(, i);
			mechanicsView.add(mV);
			this.add(mechanicsView.get(i).LoadImage());
		}
		
		for(int i = 0; i < GameManager.GetSaboteurs().size(); i++)
		{
			GameManager.GetSaboteurs().get(i).GetCurrentPosition();
			SaboteurView sV = new SaboteurView(, i);
			saboteursView.add(sV);
			this.add(saboteursView.get(i).LoadImage());
		}
		*/
	}
	
	//TODO
	private void MouseListener() 
	{
		 addMouseListener(new MouseAdapter()
		 { 
	         public void mousePressed(MouseEvent me)
	         {
	        	 int tempX = me.getX();
	        	 int tempY = me.getY();
	        	 //if((board[tempX][tempY] == null && selected == null) || !enabled)
	        		 //return;
	        	 
	        	 /*else if(selected == null)
	        	 {
	            	 selected = board[tempX][tempY];
	            	 if(selected.getColor() == SideColor.WHITE && !whiteMove) 
	            	 {
	            		 selected = null;
	            		 return;
	            	 }
	            	 
	            	 else if(selected.getColor() == SideColor.BLACK && whiteMove) 
	            	 {
	            		 selected = null;
	            		 return;
	            	 }
	            	 
	                 possibleMoves = preventCheck(selected.GetMoves(board), board, selected);
	                 repaint();
	        	 }*/
	        	 
	        	 /*else if(selected != null) 
	        	 {
	        		 if(board[tempX][tempY]!= null && board[tempX][tempY].getColor() == selected.getColor())
	        		 {
	                	 selected = board[tempX][tempY];
	                     possibleMoves = preventCheck(selected.GetMoves(board), board, selected);
	                     repaint();
	        		 }
	        		 
	        		 else
	        		 {	        			 
	        			 checkChess_pieceMove(new Position(tempX, tempY));
	        			 selected = null;
	        			 possibleMoves.clear();
	        			 repaint();
	        		 }	        		 
	        	 }*/
	         }
	     }); 
		 
		 addMouseMotionListener(new MouseMotionListener() 
		 {
			 @Override
			public void mouseMoved(MouseEvent me) 
			{
	        	 //focus.setX(me.getX() / SQUARE_SIZE);
	        	 //focus.setY(me.getY() / SQUARE_SIZE);
	        	 repaint();
			}
	
			@Override
			public void mouseDragged(MouseEvent arg0) {}
		 });		
	}
}