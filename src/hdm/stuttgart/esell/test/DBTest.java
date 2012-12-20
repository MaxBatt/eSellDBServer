package hdm.stuttgart.esell.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import hdm.stuttgart.esell.Model.*;
import hdm.stuttgart.esell.Model.Category.CategoryList;
import hdm.stuttgart.esell.Model.Petition.PetitionList;
import hdm.stuttgart.esell.Model.Petition.State;
import hdm.stuttgart.esell.errors.ESellException;

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
		 */
		
//		User user = new User("Hansi12345", "Hans", "Wurst", "hans@wurst.de", "12345");
//		
//		
//		//User in DB speichern
//		try
//		{
//			user.insert();
//		}
//		catch(ErrorHandler e)
//		{
//			System.out.println(e.getMessage());
//			System.out.println(user.getJson());
//		}
		
		
		
		
		
		
		
		/*
		 * User updaten
		 * Param: userID
		 * 
		/*/
		
//		User user = null;
//		try {
//			user = User.getUserByID(3);
//		} catch (ErrorHandler e) {
//			System.out.println(e.getMessage());
//		}
//		
//		user.setUsername("Thomas");
//		user.setEmail("thomas@online.de");
//
//		try{
//			user.update();
//		}
//		catch (ErrorHandler e) {
//			System.out.println(e.getMessage());
//			System.out.println(user.getJson());
//		}
		
		
		
		/*
		 * Username und Email überprüfen
		 * Param: userID
		 * 
		/*/
		
		
//		try {
//			if(User.isUserFree("hans@wurst.de", "Thomas"))
//				System.out.println("User is free.");
//			else
//				System.out.println("User is NOT free.");
//		} catch (ErrorHandler e) {
//			System.out.println(e.getMessage());
//		}
		
		
		
		
		
		
		
		/*
		 * User lšschen
		 * Param: userID
		 * 
		 */
		
//		User user = null;
//		try {
//			user = User.getUserByLogin("hansi12345", "12345");
//		} catch (ErrorHandler e) {
//			System.out.println(e.getMessage());
//		}
//		
//		try{
//		user.delete();
//		}
//		catch( ErrorHandler e)
//		{
//			System.out.println(e.getMessage());
//		}
		
		
		
		
		
		
		/*
		 * Category hinzufügen
		 * Param: title
		 * 
		 */
		
//		try {
//			Category.insert("Sonstiges");
//		} catch (ErrorHandler e) {
//			System.out.println(e.getMessage());
//		}
		
//		try {
//			Category.insert("Auto");
//		} catch (ErrorHandler e) {
//			System.out.println(e.getMessage());
//		}
		
		
		
		
		
		
		
		
		/*
		 * CategoryList abrufen
		 * Param: -
		 * 
		 */
		
//		CategoryList list = null;
//		try {
//			list = Category.getCategoryList();
//		} catch (ErrorHandler e) {
//			System.out.println(e.getMessage());
//		}
//		
//		if (list != null)
//			System.out.println(list.getJson());
		
		
		
		
		
		
		
		
		/*
		 * Neues Kaufgesuch anlegen
		 * Params: userID, title, categoryID, amount, state
		 */
		 
//		Petition pet = new Petition(3, "MyPetition5", 1, 2, "Searching");
//		
//		//User in DB speichern
//
//		try{
//			pet.insert();
//		} catch (ErrorHandler e){
//			System.out.println(e.getMessage());
//			System.out.println(pet.getJson());
//		}
		
		
		
		
		
		
		
		/*
		 * Kaufgesuch updaten
		 * Param: petitionID
		 * 
		 */
//		try{
//			Petition pet= Petition.getPetition(8);
//			pet.setTitle("My Updated Petition");
//			pet.setDescription("a little description...");
//			pet.update();
//		}catch (ErrorHandler e){
//		System.out.println(e.getMessage());
//		}
		
		
		
		
		
		
		
		/*
		 * Kaufgesuch lšschen
		 * Param: petitionID
		 * 
		 */
		
//		try{
//			Petition pet= Petition.getPetition(4);
//			pet.delete();
//		}catch (ErrorHandler e) {
//			System.out.println(e.getMessage());
//		}

		
		
		
		
		
		/*
		 * Liste aller Kaufgesuche
		 * Params: order, start, limit
		 * 
		 */
		
//		PetitionList plist = null;
//		try {
//			plist = Petition.getPetitionList("created", 0,3);
//		} catch (ErrorHandler e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println(plist.getJson());
		
		
		
		
		
		
		
		/*
		 * Liste aller Kaufgesuche eines bestimmten Users
		 * Param: userID, order, start, limit
		 * 
		 */
		
//		PetitionList plist = null;
//		try {
//			plist = PetitionList.getPetitionListByUser(3, "created", 0,1);
//		} catch (ErrorHandler e) {
//			System.out.println(e.getMessage());
//		}
//		
//		System.out.println(plist.getJson());
		
		
		
		
		
		
		/*
		 * Liste aller Kaufgesuche einer bestimmten Kategorie
		 * Param: categoryID, order, start, limit
		 * 
		 */
		
//		PetitionList plist = null;
//		try {
//			plist = PetitionList.getPetitionListByCategory(1, "created", 1,2);
//		} catch (ErrorHandler e) {
//			System.out.println(e.getMessage());
//		}
//		
//		System.out.println(plist.getJson());
//		
		
		
		
	}
	
	

}

