package hdm.stuttgart.esell.Model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class PetitionList extends Persistence{
	
	private ArrayList<Petition> petitionList = new ArrayList<Petition>();
	
	//Konstruktor
	//Empfängt als Parameter Sortierung, Start-Zeile und Limit
	//für order einfach den Namen des jeweiligen Tabellenfelds benutzen
	public PetitionList(String order, int start, int limit){
		
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
                
                //Für jeden Datensatz ein Objekt anlegen und in die Liste packen
                while(result.next())
                {
                	Petition petition = Petition.getPetition(result.getInt("id"));
                	this.petitionList.add(petition);
                }
                
            } catch (SQLException e) {
            	// ToDo
                e.printStackTrace();
            }
        }
	}
	
	
	//Json ausgeben
	public String getJson(){
		Gson gson = new Gson();
		String json = gson.toJson(this.petitionList);
		return json; 
	}
}
