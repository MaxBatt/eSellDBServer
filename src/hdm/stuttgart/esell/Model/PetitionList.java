package hdm.stuttgart.esell.Model;


import hdm.stuttgart.esell.errors.ErrorHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class PetitionList extends Persistence{
	
	private ArrayList<Petition> petitionList = new ArrayList<Petition>();
	
	//Konstruktor
	private PetitionList(ArrayList<Petition> petitionList)
	{
		this.petitionList = petitionList;
	}
	
	
	//Empfängt als Parameter Sortierung, Start-Zeile und Limit
	//für order einfach den Namen des jeweiligen Tabellenfelds benutzen
	public static PetitionList getPetitionList(String order, int start, int limit) throws ErrorHandler{
		
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	
        if(conn != null)
        {
            try {
            	//Statement vorbereiten
                String sql = "SELECT * from petitions ORDER BY " + order + " LIMIT " + start + ", " + limit;
                preparedStatement = conn.prepareStatement(sql);
                
                //Statement absetzen
                ResultSet result = preparedStatement.executeQuery();
                
                ArrayList<Petition> petitionList = new ArrayList<Petition>();
                
                //Für jeden Datensatz ein Objekt anlegen und in die Liste packen
                while(result.next())
                {
                	Petition petition = Petition.getPetition(result.getInt("id"));
                	petitionList.add(petition);
                }
                
                return new PetitionList(petitionList);
                
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ErrorHandler(ErrorHandler.ErrorCode.DB_ERR);
            }
        }
        else
        	throw new ErrorHandler(ErrorHandler.ErrorCode.DB_ERR);
	}
	
	
	
	//Gibt die PetitionList eines bestimmten Users zurück
	//Empfängt als Parameter UserID,  Sortierung, Start-Zeile und Limit
	public static PetitionList getPetitionListByUser(int userID, String order, int start, int limit) throws ErrorHandler{
		
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	
        if(conn != null)
        {
            try {
            	//Statement vorbereiten
                String sql = "SELECT * from petitions WHERE user_id = " + userID + " ORDER BY " + order + " LIMIT " + start + ", " + limit;
                preparedStatement = conn.prepareStatement(sql);
                
                //Statement absetzen
                ResultSet result = preparedStatement.executeQuery();
                
                ArrayList<Petition> petitionList = new ArrayList<Petition>();
                
                //Für jeden Datensatz ein Objekt anlegen und in die Liste packen
                while(result.next())
                {
                	Petition petition = Petition.getPetition(result.getInt("id"));
                	petitionList.add(petition);
                }
                
                return new PetitionList(petitionList);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ErrorHandler(ErrorHandler.ErrorCode.DB_ERR);
            }
        }
        else
        	throw new ErrorHandler(ErrorHandler.ErrorCode.DB_ERR);
	}
	
	
	
	
	//Json ausgeben
	public String getJson(){
		Gson gson = new Gson();
		String json = gson.toJson(this.petitionList);
		return json; 
	}
}
