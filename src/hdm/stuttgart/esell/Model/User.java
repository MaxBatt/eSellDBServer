package hdm.stuttgart.esell.Model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.Statement;
import hdm.stuttgart.esell.errors.ESellException;


public class User extends Persistence{

	private Integer id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	
	private ArrayList<Petition> petitions;

	//Konstruktor zum neuen Anlegen eines Users in der DB
	public User(String username, String firstname, String lastname,String email, String password){
		setUsername(username);
		setFirstname(firstname);
		setLastname(lastname);
		setEmail(email);
		setPassword(password);
	}
	
	private User(ResultSet result) throws SQLException{
		setID(result.getInt("id"));
		setUsername(result.getString("username"));
    	setFirstname(result.getString("firstname")); 
    	setLastname(result.getString("lastname")); 
    	setEmail(result.getString("email")); 
    	setPassword(result.getString("password"));
	}
	

	//Ruft den Datensatz für eine gegebene UserID ab und mappt diesen auf das Objekt
	public static User getUserByID(int id) throws ESellException{
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
                	return new User(result);
                }
                else
                	throw new ESellException(ESellException.ErrorCode.NO_ENTRY_ERR);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ESellException(ESellException.ErrorCode.DB_ERR);
            }
        }
        else
        	throw new ESellException(ESellException.ErrorCode.DB_ERR);
	}
	
	public static User getUserByLogin(String username, String password) throws ESellException
	{
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	
        if(conn != null)
        {
            try {
            	//SQL Statement vorbereiten
                String sql = "SELECT * FROM users WHERE (username= ? AND password = ?)";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                
                //Statement absetzen
                ResultSet result = preparedStatement.executeQuery();
                
                //Objekt-Mapping
                if(result.next())
                {
                	return new User(result);
                }
                else
                	throw new ESellException(ESellException.ErrorCode.NO_ENTRY_ERR);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ESellException(ESellException.ErrorCode.DB_ERR);
            }
        }
        else
        	throw new ESellException(ESellException.ErrorCode.DB_ERR);
	
	}
	
	
	//Legt für das aktuelle User-Objekt einen Datensatz an
	public void insert() throws ESellException
	{
		//Prüfen, ob Email schon vorhanden ist
		if(!isUserFree(this.email, this.username)){
			throw new ESellException(ESellException.ErrorCode.USR_EXISTS);
		}
		
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	ResultSet res = null;
    	
        if(conn != null)
        {
            try {
            	//Statement vorbereiten
                String sql = "INSERT INTO users(username, firstname, lastname, email, password) VALUES(?, ?, ?, ?, ?)";
                
                preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Der 2. Parameter ist wichtig,damit man die InsertID sofort zurück erhält 
                
                preparedStatement.setString(1, getUsername());
                preparedStatement.setString(2, getFirstname());
                preparedStatement.setString(3, getLastname());
                preparedStatement.setString(4, getEmailadress());
                preparedStatement.setString(5, getPassword());
                
                //System.out.println(preparedStatement);
                //Statement absetzen
                preparedStatement.execute();
                
                //InsertID ermitteln
                //Die Methode ist effizienter als nochmal eine Abfrage auszuführen :)
                res = preparedStatement.getGeneratedKeys();
                if (res.next()) 
                {
                    this.id = res.getInt(1);
                    return;
                } 
                else 
                {
        			throw new ESellException(ESellException.ErrorCode.INSERT_ERR);
                }
            } 
            catch (SQLException SQLe) 
            {
            	//e.printStackTrace();
    			throw new ESellException(ESellException.ErrorCode.DB_ERR);
            }
        }
        else
        	throw new ESellException(ESellException.ErrorCode.DB_ERR);
	}
	
	//update()
	//Datensatz für User updaten
	public void update() throws ESellException
	{
		if (id == null) // User wurde noch nicht mit der DB abgeglichen.
		{
			throw new ESellException(ESellException.ErrorCode.NO_ENTRY_ERR);
		}
		
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	
        if(conn != null)
        {
            try {
            	//Statement vorbereiten
                String sql = "UPDATE users SET username = ?, firstname = ?, lastname = ?, email = ?, password = ? WHERE id = ?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, getUsername());
                preparedStatement.setString(2, getFirstname());
                preparedStatement.setString(3, getLastname());
                preparedStatement.setString(4, getEmailadress());
                preparedStatement.setString(5, getPassword());
                preparedStatement.setInt(6, id);
                
                //Statement absetzen
                int affectedRows = preparedStatement.executeUpdate();
                
                if(affectedRows > 0) 
                	return;
                else 
                {
        			throw new ESellException(ESellException.ErrorCode.UPDATE_ERR);
                }

            } catch (SQLException e) {
                //e.printStackTrace();
            	throw new ESellException(ESellException.ErrorCode.DB_ERR);
            }
        }
        else
        	throw new ESellException(ESellException.ErrorCode.DB_ERR);
	}
	
	
	//User-Datensatz löschen
	public void delete() throws ESellException
	{
		if (id == null) // User wurde noch nicht mit der DB abgeglichen.
			throw new ESellException(ESellException.ErrorCode.NO_ENTRY_ERR);
		
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
                int affectedRows = preparedStatement.executeUpdate();
                
                if(affectedRows > 0) 
                	return;
                else 
                	throw new ESellException(ESellException.ErrorCode.DELETE_ERR);

            } catch (SQLException e) {
            	throw new ESellException(ESellException.ErrorCode.USR_HAS_PET_ERR);
                //e.printStackTrace();
            }
        }
        else
        	throw new ESellException(ESellException.ErrorCode.DB_ERR);
	}
	
	
	//Prüfen, ob Benutzername und Emailadresse noch frei sind
	public static boolean isUserFree(String email, String username) throws ESellException
	{
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	
        if(conn != null)
        {
            try {
            	//Statement vorbereiten
                String sql = "SELECT * FROM users WHERE email = ? OR username = ?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, username);
                
                //Statement absetzen
                ResultSet result = preparedStatement.executeQuery();
                return !result.next();
                	
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ESellException(ESellException.ErrorCode.DB_ERR);
            }
        }
        else
        	throw new ESellException(ESellException.ErrorCode.DB_ERR);
	}
	
		
	
	// Getter/Setter
	
	public int getID() {
		return id;
	}
	private void setID(int id){
		this.id = id;
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public void setEmail(String email) {
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
