package game.IO;

import java.util.Scanner;

/**
 * Ez a java osztály felel a konzolon bekért parancsok értelmezéséért
 * A parancsok listájáért érdemes átnézni a dokumantáció 7.1.2-es fejezetét, ahol a Bemeneti nyelvet részletezzük
 * A parancsok a Commands osztályban találhatóak, azok hívódnak meg, miután az interpreter feldolhozta a beolvasott parancsot
 * A parancsok:
 * runTest
 * exit
 * start
 * createMap
 * playerMove
 * playerAction
 */
public class CommandInterpreter
{
    static Scanner sc;
    static Commands cmd;

    public CommandInterpreter()
    {
        sc = new Scanner(System.in);
        cmd = new Commands();
        System.out.println("\t~ Sivatagi vízhálózat ~\nEpsilon csapat protorípusa\n");
    }

    public void getInput()
    {
        //System.out.println("A lehetséges bemeneti parancsok: \n\tcreateMap\n\tstart\n\tplayerMove <név>\n\tplayerAction <név>\n\texit\n\trunTest <tesztnév> //az All lefuttatja egyben az összes parancsot");

        while (true)
        {
        	System.out.println("A lehetséges bemeneti parancsok: "
            		+ "\n\tcreateMap"
            		+ "\n\tstart"
            		+ "\n\texit"
            		+ "\n\trunTest <tesztnév> //az all lefuttatja egyben az összes parancsot");
            String commandLineInput = sc.nextLine();
            String[] interpreterArray = commandLineInput.split(" ");

            switch (interpreterArray[0])
            {
                case "" : System.out.println("\n"); break;
                case "exit":
                	cmd.Exit(); 
                	break;
                case "start": 
                	cmd.Start(); 
                	break;
                case "createMap": 
                	cmd.CreateMap(); 
                	break;/*
                case "playerMove": 
                	cmd.playerMove(interpreterArray); 
                	break;
                case "playerAction": 
                	cmd.playerAction(interpreterArray); 
                	break;*/
                case "runTest": 
                	cmd.RunTest(interpreterArray); 
                	break;
                default : 
                {
                    System.err.println("Hibás parancs!"); break;
                }
            }
        }
    }
}