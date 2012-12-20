package hdm.stuttgart.esell.Model;

import hdm.stuttgart.esell.errors.ESellException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class Category extends Persistence{
	
	private int id;
	private String title;
	
	public Category(String title){
		this.title = title;
	}
	
	private Category(ResultSet result) throws SQLException
	{
		this.setTitle(result.getString("title"));
		this.setID(result.getInt("id"));
	}
	
	
	public static void insert(String title) throws ESellException
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
                throw new ESellException(ESellException.ErrorCode.INSERT_ERR);
            }
        }
        else
        	throw new ESellException(ESellException.ErrorCode.DB_ERR);
	}
	
	
	
	public static CategoryList getCategoryList() throws ESellException{	
		
		ArrayList<Category> categoryList = new ArrayList<Category>();
		
		makeConnection();
    	PreparedStatement preparedStatement = null;
    	
        if(conn != null)
        {
            try {
            	//Statement vorbereiten
                String sql = "SELECT * from categories";
                preparedStatement = conn.prepareStatement(sql);
                
                //Statement absetzen
                ResultSet result = preparedStatement.executeQuery();
                
                //FŸr jeden Datensatz ein Objekt anlegen und in die Liste packen
                while(result.next())
                {
                	categoryList.add(new Category(result));
                }
                
                return new CategoryList(categoryList);
                
            } catch (SQLException e) {
            	// ToDo
                e.printStackTrace();
                throw new ESellException(ESellException.ErrorCode.DB_ERR);
            }
        }
        else
        	throw new ESellException(ESellException.ErrorCode.DB_ERR);
	}
	
	
	
	
	// Getter und Setter
	public int getID(){
		return this.id;
	}
	private void setID(int id){
		this.id = id;
	}
	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	
	public static class CategoryList{
		
		private ArrayList<Category> categoryList = new ArrayList<Category>();
		
		//Konstruktor
		private CategoryList(ArrayList<Category> categoryList)
		{
			this.categoryList = categoryList;
		}
		
		public String getJson(){
			Gson gson = new Gson();
			String json = gson.toJson(this.categoryList);
			return json; 
		}
	}

	
}
