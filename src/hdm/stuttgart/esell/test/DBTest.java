package hdm.stuttgart.esell.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import hdm.stuttgart.esell.Model.*;
import hdm.stuttgart.esell.Model.Petition.State;
import hdm.stuttgart.esell.errors.ErrorHandler;

public class DBTest {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args){
		
		/*
		 * Neuen User anlegen
		 * Params: username, firstname, lastname, email, password
		 *
		
		//User in DB speichern
		ErrorHandler e = user.insert();
		System.out.println(e.getFlag());
		System.out.println(e.getMessage());
		System.out.println(e.getJson());
		System.out.println(user.getJson());
		
		*/
		
		
		
		
		
		/*
		 * User updaten
		 * Param: userID
		 * 
		/*
		
		User user = new User(1);
		user.setUsername("Thomas");
		user.setEmail("thomas@online.de");
		ErrorHandler e = user.update();
		System.out.println(e.getFlag());
		System.out.println(e.getMessage());
		System.out.println(e.getJson());
		System.out.println(user.getJson());
		
		*/
		
		
		
		
		
		/*
		 * User lšschen
		 * Param: userID
		 * 
		 *
		
		User user = new User(1);
		ErrorHandler e = user.delete();
		System.out.println(e.getFlag());
		System.out.println(e.getMessage());
		System.out.println(e.getJson());
		
		*/
		
		
		
		
		
		/*
		 * Neues Kaufgesuch anlegen
		 * Params: userID, title, categoryID, amount, state
		 *
		 
		Petition pet = new Petition(1, "MyPetition", 1, 2, "Searching");
		
		//User in DB speichern
		ErrorHandler e = pet.insert();
		System.out.println(e.getFlag());
		System.out.println(e.getMessage());
		System.out.println(e.getJson());
		System.out.println(pet.getJson());
		
		*/	
		
		
		
		
		
		/*
		 * Kaufgesuch updaten
		 * Param: petitionID
		 * 
		 *
		
		Petition pet= new Petition(1);
		pet.setTitle("My Updated Petition");
		pet.setDescription("a little description...");
		ErrorHandler e = pet.update();
		System.out.println(e.getFlag());
		System.out.println(e.getMessage());
		System.out.println(e.getJson());
		System.out.println(pet.getJson());
		
		*/
		
		
		
		
		
		/*
		 * Kaufgesuch lšschen
		 * Param: petitionID
		 * 
		 *
		
		Petition pet= new Petition(2);
		ErrorHandler e = pet.delete();
		System.out.println(e.getFlag());
		System.out.println(e.getMessage());
		System.out.println(e.getJson());
		
		*/
		
		
		
		
		
		/*
		 * Liste aller Kaufgesuche
		 * Params: order, start, limit
		 * 
		 *
		
		PetitionList plist= new PetitionList("created", 0,10);
		System.out.println(plist.getJson());
		
		*/
		
		
		
		
		
		/*
		 * Liste aller Kaufgesuche eines bestimmten Users
		 * Param: userID, order, start, limit
		 * 
		 *
		
		PetitionList plist= new PetitionList(1, "created", 0,10);
		System.out.println(plist.getJson());
		
		*/
		
		
		
	}
	
	

}

