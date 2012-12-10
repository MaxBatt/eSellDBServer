package hdm.stuttgart.esell.test;

import java.util.ArrayList;
import hdm.stuttgart.esell.Model.*;
import hdm.stuttgart.esell.Model.Petition.State;

public class DBTest {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args){


//		User user = User.getUser("beatmax@gmx.de", "max");
//		User user = new User("sdf", "Lustich", "peter@lufdstich.de", "peter");
//		user.insert();
//		user.setFirstname("Peter");
//		user.setEmailadress("beatmax@gmx.de");
//		user.update();
		
		
//		Petition petition = new Petition(1, "Blabla", 1, 1, "Searching");
//		petition.insert();
//		System.out.println(petition.getID());	
//		System.out.println(petition.getJson());
		
		//Petition p = new Petition(2);
		//System.out.println(p.getJson());
		
		
		
//		user.delete();
		
//		boolean bla1 = User.isEmailadressFree("hans@wurst.de");
//		boolean bla2 = User.isEmailadressFree("hans@wurst2.de");
		
//		Category.insert("Dreck");
		
//		CategoryList list = new CategoryList();
//		System.out.println(list.getJson());
		
		PetitionList list = new PetitionList(1, "created",0,10);
		System.out.println(list.getJson());
		
		

	}

}

