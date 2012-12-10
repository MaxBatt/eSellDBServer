package hdm.stuttgart.esell.Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Category extends Persistence{
	
	private int id;
	private String title;
	
	public Category(String title){
		this.title = title;
	}
	
	
	public static void insert(String title)
	{
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	
        if(conn != null)
        {
            try {
            	//Statement vorbereiten
                String sql = "INSERT INTO categories(`title`) VALUES(?)";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, title);
                
                //Statement absetzen
                preparedStatement.execute();

            } catch (SQLException e) {
            	// ToDo
                e.printStackTrace();
            }
        }
	}
	
	
//	public static void delete(int id)
//	{
//		//ToDo
//	}
	
	
	
	// Getter und Setter
	public int getID(){
		return this.id;
	}
	public void setID(int id){
		this.id = id;
	}
	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	
}
