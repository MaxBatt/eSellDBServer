package hdm.stuttgart.esell.Model;

import hdm.stuttgart.esell.errors.ErrorHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Category extends Persistence{
	
	private int id;
	private String title;
	
	public Category(String title){
		this.title = title;
	}
	
	
	public static void insert(String title) throws ErrorHandler
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
                throw new ErrorHandler(ErrorHandler.ErrorCode.INSERT_ERR);
            }
        }
        else
        	throw new ErrorHandler(ErrorHandler.ErrorCode.DB_ERR);
	}
	
	// TODO muss zunächst besprochen werden.
//	public static ArrayList<Category> getCategoryList() throws ErrorHandler{	
//		
//		ArrayList<Category> categoryList = new ArrayList<Category>();
//		
//		makeConnection();
//    	PreparedStatement preparedStatement = null;
//    	
//        if(conn != null)
//        {
//            try {
//            	//Statement vorbereiten
//                String sql = "SELECT * from categories";
//                preparedStatement = conn.prepareStatement(sql);
//                
//                //Statement absetzen
//                ResultSet result = preparedStatement.executeQuery();
//                
//                //FŸr jeden Datensatz ein Objekt anlegen und in die Liste packen
//                while(result.next())
//                {
//                	Category cat = new Category(result.getString("title"));
//                	cat.setID(result.getInt("id"));
//                	categoryList.add(cat);
//                }
//                
//                return categoryList;
//                
//            } catch (SQLException e) {
//            	// ToDo
//                e.printStackTrace();
//                throw new ErrorHandler(ErrorHandler.ErrorCode.DB_ERR);
//            }
//        }
//        else
//        	throw new ErrorHandler(ErrorHandler.ErrorCode.DB_ERR);
//	}
	
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
