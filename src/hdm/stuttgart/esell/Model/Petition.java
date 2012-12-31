package hdm.stuttgart.esell.Model;

import hdm.stuttgart.esell.errors.ESellException;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.mysql.jdbc.Statement;

public class Petition extends Persistence {

	private Integer id;
	private int userID;
	private int categoryID;
	private String title;
	private String description;
	private Integer price;

	private int amount;
	private URL imageURL;
	private String state;
	private Date created;

	// Konstruktor
	// Dieser wird benutzt, um ein neues Objekt zu erzeugen und in der DB zu
	// speichern
	public Petition(int userID, String title, int categoryID, int amount, String state) {
		setUserID(userID);
		setTitle(title);
		setCategoryID(categoryID);
		setAmount(amount);
		setState(state);
		setCreation(new Date(new java.util.Date().getTime()));
	}
	
	private Petition(ResultSet result) throws SQLException{
		setUserID(result.getInt("user_id"));
		setTitle(result.getString("title"));
		setCategoryID(result.getInt("category_id")); 
		setAmount(result.getInt("amount")); 
		setState(result.getString("state"));
		
		setID(result.getInt("id"));
		setDescription(result.getString("description"));
		setPrice(result.getInt("price"));

		if (result.getString("image_url") != null) {
			try {
				setImageURL( new URL(
						result.getString("image_url")));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		setCreation(result.getDate("created"));
	}


	// Ruft den Datensatz für eine gegebene PetitionID ab und mappt diesen auf
	// ein Objekt
	public static Petition getPetition(int id) throws ESellException {

		makeConnection();
		PreparedStatement preparedStatement = null;

		if (conn != null) {
			try {

				// Statement vorbereiten
				// Statement läuft mit INNER JOIN, damit man den Kategorienamen
				// gleich mitkriegt.
				String sql = "SELECT * FROM petitions WHERE (id=?)";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, id);

				// Statement absetzen
				ResultSet result = preparedStatement.executeQuery();

				// Objektmapping
				if (result.next()) {
					
					return new Petition(result);
							
				}
				else
					throw new ESellException(ESellException.ErrorCode.NO_ENTRY_ERR);
			} catch (SQLException e) {
				// ToDo
				e.printStackTrace();
				throw new ESellException(ESellException.ErrorCode.DB_ERR);
			}
		}
		else
			throw new ESellException(ESellException.ErrorCode.DB_ERR);
	}

	public void insert() throws ESellException 
	{
		makeConnection();
		PreparedStatement preparedStatement = null;
		ResultSet res;

		if (conn != null) {
			try {

				// Statement vorbereiten
				String sql = "INSERT INTO petitions (`user_id`,`title`,`description`,`price`,`category_id`,`amount`,`image_url`,`state`,`created`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

				preparedStatement = conn.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);

				preparedStatement.setInt(1, getUserID());
				preparedStatement.setString(2, getTitle());

				if (description != null)
					preparedStatement.setString(3, getDescription());
				else
					preparedStatement.setNull(3, Types.VARCHAR);

				if (price != null)
					preparedStatement.setInt(4, getPrice());
				else
					preparedStatement.setNull(4, Types.INTEGER);

				preparedStatement.setInt(5, getCategoryID());
				preparedStatement.setInt(6, getAmount());

				if (imageURL != null)
					preparedStatement.setString(7, getImageURL().toString());
				else
					preparedStatement.setNull(7, Types.VARCHAR);

				preparedStatement.setString(8, getState());
				preparedStatement.setDate(9, getCreation());
				// System.out.println(preparedStatement);
				// Statement absetzen
				preparedStatement.execute();

				// InsertID ermitteln
				// Die Methode ist effizienter als nochmal eine Abfrage
				// auszuführen :)
				res = preparedStatement.getGeneratedKeys();
				if (res.next()) {
					this.id = res.getInt(1);
					return;
				} else {
					throw new ESellException(ESellException.ErrorCode.INSERT_ERR);
				}

			} catch (SQLException e) {
				// ToDo
				e.printStackTrace();
				throw new ESellException(ESellException.ErrorCode.DB_ERR);
			}
		}
		else	
			throw new ESellException(ESellException.ErrorCode.DB_ERR);
	}

	// Updatet den Datensatz mit aktuellen Werten des Objekts
	public void update() throws ESellException {

		if (id == null) // Petition wurde noch nicht mit der DB abgeglichen.
			throw new ESellException(ESellException.ErrorCode.NO_ENTRY_ERR);
		
		makeConnection();
		PreparedStatement preparedStatement = null;

		if (conn != null) {
			try {
				// Statement vorbereiten
				String sql = "UPDATE petitions SET user_id = ?, title = ?, description = ?, price = ?, category_id = ?, amount = ?, image_url = ?, state = ?, created = ? WHERE id = ?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, this.userID);
				preparedStatement.setString(2, this.title);
				preparedStatement.setString(3, this.description);
				preparedStatement.setInt(4, this.price);
				preparedStatement.setInt(5, this.categoryID);
				preparedStatement.setInt(6, this.amount);
				if (this.imageURL != null)
					preparedStatement.setString(7, this.imageURL.toString());
				else
					preparedStatement.setString(7, null);
				preparedStatement.setString(8, this.state);
				preparedStatement.setDate(9, this.created);

				preparedStatement.setInt(10, this.id);

				// System.out.println(preparedStatement);

				// Statement absetzen
				int affectedRows = preparedStatement.executeUpdate();

				if (affectedRows > 0)
					return;
				else
					throw new ESellException(ESellException.ErrorCode.UPDATE_ERR);

			} catch (SQLException e) {
				// ToDo
				// e.printStackTrace();
				throw new ESellException(ESellException.ErrorCode.DB_ERR);
			}
		}
		throw new ESellException(ESellException.ErrorCode.DB_ERR);
	
	
	}

	// Petition-Datensatz löschen
	public void delete() throws ESellException {

		if (id == null) // Petition wurde noch nicht mit der DB abgeglichen.
			throw new ESellException(ESellException.ErrorCode.NO_ENTRY_ERR);
			
		makeConnection();
		PreparedStatement preparedStatement = null;

		if (conn != null) {
			try {
				// Statement vorbereiten
				String sql = "DELETE FROM petitions WHERE id = ?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, this.id);

				// Statement absetzen
				int affectedRows = preparedStatement.executeUpdate();

				if (affectedRows > 0)
					 return;
				else
					throw new ESellException(ESellException.ErrorCode.DELETE_ERR);

			} catch (SQLException e) {
				// ToDo
				// e.printStackTrace();
				throw new ESellException(ESellException.ErrorCode.DB_ERR);
			}
		}
		throw new ESellException(ESellException.ErrorCode.DB_ERR);
	}

	
	//Empfängt als Parameter Sortierung, Start-Zeile und Limit
	//für order einfach den Namen des jeweiligen Tabellenfelds benutzen
	public static PetitionList getPetitionList(String order, int start, int limit) throws ESellException{
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	
        if(conn != null)
        {
            try {
            	//Statement vorbereiten
                String sql = "SELECT * from petitions ORDER BY ? LIMIT ?, ?";
                preparedStatement = conn.prepareStatement(sql);
                
                preparedStatement.setString(1, order);
                preparedStatement.setInt(2, start);
                preparedStatement.setInt(3, limit);
                
                //Statement absetzen
                ResultSet result = preparedStatement.executeQuery();
                
                ArrayList<Petition> petitionList = new ArrayList<Petition>();
                
                //Für jeden Datensatz ein Objekt anlegen und in die Liste packen
                while(result.next())
                {
                	Petition petition = new Petition(result);
                	petitionList.add(petition);
                }
                
                return new PetitionList(petitionList);
                
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ESellException(ESellException.ErrorCode.DB_ERR);
            }
        }
        else
        	throw new ESellException(ESellException.ErrorCode.DB_ERR);
	}
	
	
	//Gibt die PetitionList eines bestimmten Users zurück
		//Empfängt als Parameter UserID,  Sortierung, Start-Zeile und Limit
		public static PetitionList getPetitionListByUser(int userID, String order, int start, int limit) throws ESellException{
			
			makeConnection();
	    	PreparedStatement preparedStatement = null;
	    	
	        if(conn != null)
	        {
	            try {
	            	//Statement vorbereiten
	                String sql = "SELECT * from petitions WHERE user_id = ? ORDER BY ? LIMIT ?, ?";
	                preparedStatement = conn.prepareStatement(sql);
	                
	                preparedStatement.setInt(1, userID);
	                preparedStatement.setString(2, order);
	                preparedStatement.setInt(3, start);
	                preparedStatement.setInt(4, limit);
	                
	                //Statement absetzen
	                ResultSet result = preparedStatement.executeQuery();
	                
	                ArrayList<Petition> petitionList = new ArrayList<Petition>();
	                
	                //Für jeden Datensatz ein Objekt anlegen und in die Liste packen
	                while(result.next())
	                {
	                	Petition petition = new Petition(result);
	                	petitionList.add(petition);
	                }
	                
	                return new PetitionList(petitionList);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                throw new ESellException(ESellException.ErrorCode.DB_ERR);
	            }
	        }
	        else
	        	throw new ESellException(ESellException.ErrorCode.DB_ERR);
		}
	
		
		//Gibt die PetitionList einer bestimmten Kategorie zurück
				//Empfängt als Parameter CategoryID,  Sortierung, Start-Zeile und Limit
				public static PetitionList getPetitionListByCategory(int categoryID, String order, int start, int limit) throws ESellException{
					
					makeConnection();
			    	PreparedStatement preparedStatement = null;
			    	
			        if(conn != null)
			        {
			            try {
			            	//Statement vorbereiten
			                String sql = "SELECT * from petitions WHERE category_id = ? ORDER BY ? LIMIT ?, ?";
			                preparedStatement = conn.prepareStatement(sql);
			                
			                preparedStatement.setInt(1, categoryID);
			                preparedStatement.setString(2, order);
			                preparedStatement.setInt(3, start);
			                preparedStatement.setInt(4, limit);
			                
			                //Statement absetzen
			                ResultSet result = preparedStatement.executeQuery();
			                
			                ArrayList<Petition> petitionList = new ArrayList<Petition>();
			                
			                //Für jeden Datensatz ein Objekt anlegen und in die Liste packen
			                while(result.next())
			                {
			                	Petition petition = new Petition(result);
			                	petitionList.add(petition);
			                }
			                
			                return new PetitionList(petitionList);
			            } catch (SQLException e) {
			                e.printStackTrace();
			                throw new ESellException(ESellException.ErrorCode.DB_ERR);
			            }
			        }
			        else
			        {
			        	throw new ESellException(ESellException.ErrorCode.DB_ERR);
			        }
				}
	
	// Getter / Setter

	public int getID() {
		return id;
	}
	
	private void setID(int id){
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int applicant) {
		this.userID = applicant;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public URL getImageURL() {
		return imageURL;
	}

	public void setImageURL(URL imageURL) {
		this.imageURL = imageURL;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreation() {
		return created;
	}

	public void setCreation(Date created) {
		this.created = created;
	}

	public enum State {
		Searching, Finished
	}
	
	public static class PetitionList{
		
		private ArrayList<Petition> petitionList = new ArrayList<Petition>();
		
		//Konstruktor
				private PetitionList(ArrayList<Petition> petitionList)
				{
					this.petitionList = petitionList;
				}
				
				public String getJson(){
					Gson gson = new Gson();
					String json = gson.toJson(this.petitionList);
					return json; 
				}
		
	}
}
