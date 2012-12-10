package hdm.stuttgart.esell.Model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.Statement;


public class User extends Persistence{

	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	
	private ArrayList<Petition> petitions;

	//Konstruktor
	public User(String firstname, String lastname,String email, String password){
		setFirstname(firstname);
		setLastname(lastname);
		setEmailadress(email);
		setPassword(password);
	}
	
	//Ruft den Datensatz für eine gegebene UserID ab und mappt diesen auf ein Objekt
	public static User getUser(int id)
	{
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	
        if(conn != null)
        {
            try {
            	//SQL Statement vorbereiten
                String sql = "SELECT * FROM users WHERE (id= ?)";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                //Statement absetzen
                ResultSet result = preparedStatement.executeQuery();
                //Objekt-Mapping
                if(result.next())
                {
	                User user = new User(result.getString("firstname"), result.getString("lastname"), result.getString("email"), result.getString("password"));
	                user.id = result.getInt("id");
	                return user;
                }
            } catch (SQLException e) {
            	// ToDo
                e.printStackTrace();
            }
        }
		return null;
	}
	
	//Legt für das aktuelle User-Objekt einen Datensatz an
	public void insert()
	{
		//Prüfen, ob Email schon vorhanden ist
		if(!isEmailadressFree(this.email)){
			//TODO
			System.out.println("Email-Adresse schon vorhanden");
			return;
		}
		
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	ResultSet res = null;
    	
        if(conn != null)
        {
            try {
            	//Statement vorbereiten
                String sql = "INSERT INTO users(firstname, lastname, email, password) VALUES(?, ?, ?, ?)";
                
                preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Der 2. Parameter ist wichtig,damit man die InsertID sofort zurück erhält 
                
                preparedStatement.setString(1, getFirstname());
                preparedStatement.setString(2, getLastname());
                preparedStatement.setString(3, getEmailadress());
                preparedStatement.setString(4, getPassword());
                
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
	
	//update()
	//Datensatz für User updaten
	public void update()
	{
		if (id == null) // User wurde noch nicht mit der DB abgeglichen.
			return;
		
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	
        if(conn != null)
        {
            try {
            	//Statement vorbereiten
                String sql = "UPDATE users SET firstname = ?, lastname = ?, email = ?, password = ? WHERE id = ?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, getFirstname());
                preparedStatement.setString(2, getLastname());
                preparedStatement.setString(3, getEmailadress());
                preparedStatement.setString(4, getPassword());
                preparedStatement.setInt(5, id);
                
                //Statement absetzen
                preparedStatement.execute();

            } catch (SQLException e) {
            	// ToDo
                e.printStackTrace();
            }
        }
	}
	
	
	//User-Datensatz löschen
	public void delete()
	{
		if (id == null) // User wurde noch nicht mit der DB abgeglichen.
			return;
		
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	
        if(conn != null)
        {
            try {
            	//Statement vorbereiten
                String sql = "DELETE FROM users WHERE id = ?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                //Statement absetzen
                preparedStatement.execute();

            } catch (SQLException e) {
            	// ToDo
                e.printStackTrace();
            }
        }
	}
	
	//Prüfen, ob Emailadresse noch frei ist
	public static boolean isEmailadressFree(String emailAdress)
	{
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	
        if(conn != null)
        {
            try {
            	//Statement vorbereiten
                String sql = "SELECT * FROM users WHERE email = ?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, emailAdress);
                
                //Statement absetzen
                ResultSet result = preparedStatement.executeQuery();
                return !result.next();
                	
            } catch (SQLException e) {
            	// ToDo
                e.printStackTrace();
            }
        }
        
     // temp 
        return false;
	}
	
	
	
	
	
	
	// Getter/Setter
	
	public int getID() {
		return id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmailadress() {
		return email;
	}
	public void setEmailadress(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Petition> getPetitions() {
		return petitions;
	}
	
	
	
}
