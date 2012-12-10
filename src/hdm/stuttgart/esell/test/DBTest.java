package hdm.stuttgart.esell.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import hdm.stuttgart.esell.Model.*;
import hdm.stuttgart.esell.Model.Petition.State;

public class DBTest {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args){


//		User user = new User(1);
//		user.setFirstname("Andreas");
//		user.update();
		
//		PetitionList p = new PetitionList(1, "created", 0, 10);
//		System.out.println(p.getJson());
		
		
		//User user = new User("bruder", "Bruder", "Pan", "bruder@pan.de", "pan");
		//user.insert();
//		user.insert();
//		user.setFirstname("Peter");
//		user.setEmailadress("beatmax@gmx.de");
//		user.update();
		
		
//		Petition petition = new Petition(1, "Blabla", 1, 1, "Searching");
//		petition.insert();
//		System.out.println(petition.getID());	
//		System.out.println(petition.getJson());
		
		Petition p = new Petition(7);
		p.setAmount(50);
		p.setPrice(100);
		p.setDescription("Garbage");
		try {
			p.setImageURL(new URL("http://www.online.de"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.update();
		p.delete();
		//System.out.println(p.getJson());
		
		
		
//		user.delete();
		
//		boolean bla1 = User.isEmailadressFree("hans@wurst.de");
//		boolean bla2 = User.isEmailadressFree("hans@wurst2.de");
		
//		Category.insert("Dreck");
		
//		CategoryList list = new CategoryList();
//		System.out.println(list.getJson());
		
//		PetitionList list = new PetitionList(1, "created",0,10);
//		System.out.println(list.getJson());
		
		

	}

}

