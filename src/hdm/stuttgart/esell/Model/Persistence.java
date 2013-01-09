package hdm.stuttgart.esell.Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.gson.Gson;

public abstract class Persistence {

	protected static Connection conn = null;
	 
    // Hostname
    private static String dbHost = "localhost";
 
    // Port -- Standard: 3306
    private static String dbPort = "3306";
 
    // Datenbankname
    private static String database = "esell";
 
    // Datenbankuser
    private static String dbUser = "root";
 
    // Datenbankpasswort
    private static String dbPassword = "root";

     
    protected static void makeConnection()
    {
        if(conn == null)
 {
			try {
				// Datenbanktreiber f�r ODBC Schnittstellen laden.
				// F�r verschiedene ODBC-Datenbanken muss dieser Treiber
				// nur einmal geladen werden.
				Class.forName("com.mysql.jdbc.Driver");

				// Verbindung zur ODBC-Datenbank herstellen.
				// Es wird die JDBC-ODBC-Br�cke verwendet.
				conn = DriverManager.getConnection("jdbc:mysql://" + dbHost
						+ ":" + dbPort + "/" + database + "?" + "user="
						+ dbUser + "&" + "password=" + dbPassword);
			} catch (ClassNotFoundException e) {
				System.out.println("Treiber nicht gefunden");
			} catch (SQLException e) {
				System.out.println("Connect nicht moeglich: " + e);
			}
		}
    }
    
    
  //Gibt Objekt als JSON-String zur�ck
	public String getJson(){
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}
}

