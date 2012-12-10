package hdm.stuttgart.esell.Model;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.Statement;

public class Petition extends Persistence {

	private int id;
	private int userID;
	private String username;
	private int categoryID;
	private String categoryTitle;
	private String title;
	private String description;
	private Integer price;
	
	private int amount;
	private URL imageURL;
	private String state;
	private Date created;

	
	
	//Konstruktor
	//Dieser wird benutzt, um ein neues Objekt zu erzeugen und in der DB zu speichern
	public Petition(int userID, String title, int categoryID, int amount, String state) {
		setUserID(userID);
		setTitle(title);
		setCategoryID(categoryID);
		setAmount(amount);
		setState(state);
		setCreation(new Date(new java.util.Date().getTime()));
	}
	
	//Konstruktor ueber id
	//Ruft den Datensatz f�r eine gegebene PetitionID ab und mappt diesen auf das Objekt
	public Petition(int id){
		
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	
        if(conn != null)
        {
            try {
            	
            	//Statement vorbereiten
            	//Statement l�uft mit INNER JOIN, damit man den Kategorienamen gleich mitkriegt.
                String sql = "SELECT p.id, p.user_id, u.username, p.category_id, p.title, p.description, p.price, p.amount, p.image_url, p.state, p.created, c.title as category_title FROM petitions p INNER JOIN categories c ON p.category_id = c.id AND p.id = ? INNER JOIN users u ON p.user_id = u.id";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, id);

                //Statement absetzen
                ResultSet result = preparedStatement.executeQuery();

                //Objektmapping
                if(result.next())
                {
	                this.id 			= result.getInt("id");
	                this.userID 		= result.getInt("user_id");
	                this.username 		= result.getString("username");
	                this.categoryID 	= result.getInt("category_id");
	                this.categoryTitle 	= result.getString("category_title");
	                this.title 			= result.getString("title");
	                this.description 	= result.getString("description");
	                this.price 			= result.getInt("price");
	                this.amount 		= result.getInt("amount");
	                
	                if(result.getString("image_url") != null){
	                	try {
							this.imageURL = new URL(result.getString("image_url"));
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                }
	                this.state 			= result.getString("state");
	                this.created 		= (result.getDate("created"));
                }
            } catch (SQLException e) {
            	// ToDo
                e.printStackTrace();
            }
        }
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
				//System.out.println(preparedStatement);
				//Statement absetzen
				preparedStatement.execute();
				
				//InsertID ermitteln
                //Die Methode ist effizienter als nochmal eine Abfrage auszuf�hren :)
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

	
	//Updatet den Datensatz mit aktuellen Werten des Objekts
	public void update() {
		
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	
        if(conn != null)
        {
            try {
            	//Statement vorbereiten
                String sql = "UPDATE petitions SET user_id = ?, title = ?, description = ?, price = ?, category_id = ?, amount = ?, image_url = ?, state = ?, created = ? WHERE id = ?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, this.userID);
                preparedStatement.setString(2, this.title);
                preparedStatement.setString(3, this.description);
                preparedStatement.setInt(4, this.price);
                preparedStatement.setInt(5, this.categoryID);
                preparedStatement.setInt(6, this.amount);
                preparedStatement.setString(7, this.imageURL.toString());
                preparedStatement.setString(8, this.state);
                preparedStatement.setDate(9, this.created);
                
                preparedStatement.setInt(10, this.id);
                
                //System.out.println(preparedStatement);
                
                //Statement absetzen
                preparedStatement.execute();

            } catch (SQLException e) {
            	// ToDo
                e.printStackTrace();
            }
        }
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
	
	public String getCategoryTitle() {
		return categoryTitle;
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
}
