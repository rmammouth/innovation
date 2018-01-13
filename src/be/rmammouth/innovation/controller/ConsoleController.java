package be.rmammouth.innovation.controller;

import java.io.*;
import java.util.*;

import be.rmammouth.innovation.model.moves.*;

public class ConsoleController extends PlayerController
{
	private BufferedReader console=new BufferedReader(new InputStreamReader(System.in));

	@Override
	public Move getNextMove(List<? extends Move> availableMoves)
	{
		int n=1;
		for (Move move : availableMoves)
		{
			System.out.println(n+" : "+move.getLabel());
			n++;
		}
		
		try
		{
			while (true)
			{
				System.out.print(">");
			  String strChoice=console.readLine();
			  try
			  {
			    int choice=Integer.parseInt(strChoice);
			    if ((choice>0) && (choice<=availableMoves.size()))
	    		{
			    	return availableMoves.get(choice-1);    	
	    		}			    
			  }
			  catch (NumberFormatException ex)
			  {			  	
			  }
			  System.err.println("Choose a number between 1 and "+(availableMoves.size()));
			}
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			return null;
		}		
	}

}
