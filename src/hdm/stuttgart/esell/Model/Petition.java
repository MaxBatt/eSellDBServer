package hdm.stuttgart.esell.Model;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import com.mysql.jdbc.Statement;

public class Petition extends Persistence {

	private int id;
	private int userID;
	private int categoryID;
	private String categoryName;
	private String title;
	private String description;
	private Integer price;
	
	private int amount;
	private URL imageURL;
	private String state;
	private Date Creation;

	
	
	//Konstruktor
	public Petition(int userID, String title, int categoryID, int amount,
			String state) {
		setUserID(userID);
		setTitle(title);
		setCategoryID(categoryID);
		setAmount(amount);
		setState(state);
		setCreation(new Date(new java.util.Date().getTime()));
	}

	
	//Ruft den Datensatz für eine gegebene PetitionID ab und mappt diesen auf ein Objekt
	public static Petition getPetition(int id)
	{
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	
        if(conn != null)
        {
            try {
            	
            	//Statement vorbereiten
            	//Statement läuft mit INNER JOIN, damit man den Kategorienamen gleich mitkriegt.
                String sql = "SELECT p.id, p.user_id, p.category_id, p.title, p.description, p.price, p.amount, p.image_url, p.state, p.created, c.title as category_title FROM petitions p INNER JOIN categories c ON p.category_id = c.id AND p.id = ?";
                
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                
                //Statement absetzen
                ResultSet result = preparedStatement.executeQuery();

                //Objektmapping
                if(result.next())
                {
	                Petition pet = new Petition (	result.getInt("user_id"),  result.getString("title"), result.getInt("category_id"), result.getInt("amount"), result.getString("state"));
	                pet.id = result.getInt("id");
	                pet.categoryName = result.getString("category_title");
	                pet.description = result.getString("description");
	                if(result.getString("image_url") != null){
	                	try {
							pet.imageURL = new URL(result.getString("image_url"));
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                }
	                return pet;
                }
            } catch (SQLException e) {
            	// ToDo
                e.printStackTrace();
            }
        }
		return null;
	}

	
	public void insert() {
		makeConnection();
		PreparedStatement preparedStatement = null;
		ResultSet res;

		if (conn != null) {
			try {	
				
				//Statement vorbereiten
				String sql = "INSERT INTO petitions (`user_id`,`title`,`description`,`price`,`category_id`,`amount`,`image_url`,`state`,`created`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
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

				//Statement absetzen
				preparedStatement.execute();
				
				//InsertID ermitteln
                //Die Methode ist effizienter als nochmal eine Abfrage auszuführen :)
                res = preparedStatement.getGeneratedKeys();
                if (res.next()) {
                    this.id = res.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no generated key obtained.");
                }

			} catch (SQLException e) {
				// ToDo
				e.printStackTrace();
			}
		}
	}

	public void update() {
		// ToDo
	}

	public void delete() {
		// ToDo
	}

	
	
	//Getter / Setter
	
	public int getID() {
		return id;
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
	
	public String getCategoryName() {
		return categoryName;
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
		return Creation;
	}

	public void setCreation(Date creation) {
		Creation = creation;
	}

	public enum State {
		Searching, Finished
	}
}
