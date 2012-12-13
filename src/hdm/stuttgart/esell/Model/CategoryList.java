package hdm.stuttgart.esell.Model;


import hdm.stuttgart.esell.errors.ErrorHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class CategoryList extends Persistence{
	
	private ArrayList<Category> categoryList = new ArrayList<Category>();
	
	//Konstruktor
	private CategoryList(ArrayList<Category> categoryList)
	{
		this.categoryList = categoryList;
	}
	
	
	public static CategoryList getCategoryList() throws ErrorHandler{	
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
                	// TODO hier dürfte die ID eigentlich nicht gesetzt werden können.
                	Category cat = new Category(result.getString("title"));
                	cat.setID(result.getInt("id"));
                	categoryList.add(cat);
                }
                
                return new CategoryList(categoryList);
            } catch (SQLException e) {
            	// ToDo
                e.printStackTrace();
                throw new ErrorHandler(ErrorHandler.ErrorCode.DB_ERR);
            }
        }
        else
        	throw new ErrorHandler(ErrorHandler.ErrorCode.DB_ERR);
	}
	
	public String getJson(){
		Gson gson = new Gson();
		String json = gson.toJson(this.categoryList);
		return json; 
	}
}
