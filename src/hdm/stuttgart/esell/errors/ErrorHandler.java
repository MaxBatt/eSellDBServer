package hdm.stuttgart.esell.errors;

import java.util.HashMap;

import com.google.gson.Gson;

/*
 * ErrorHandler
 * F�hrt einen Katalog mit Fehler- und Erfolgsmeldungen und stellt ein Objekt mit den Feldern flag und message.
 * Je nach errorCode, mit dem instanziiert wird, wird flag auf true/false gesetzt und message mit 
 * der entsprechenden Fehlermeldung belegt. 
 * 
 */

public class ErrorHandler {

	private boolean flag;
	private String message;
	
	/*
	 * Konstruktor
	 * Params: int flag, String errorCode
	 * 
	 */
	public ErrorHandler(boolean flag, String errorCode) {
		this.flag = flag;
		
		//HashMap f�r Meldungen erzeugen
		HashMap<String, String> errorMap = new HashMap<String, String>();
		//HashMap mit Eintr�gen f�llen
		errorMap = fillErrorMap(errorMap);
		
		//�berpr�fen, ob der �bergebene Fehlercode in der HashMap liegt.
		//Wenn ja, entsprechende Meldung ausgeben
		if (errorMap.containsKey(errorCode)) {
			this.message = errorMap.get(errorCode).toString();
		} 
		//Wenn nein, Standardfehlermeldung
		else {
			this.message = "Fehler";
		}
	}


	
	//KAtalog f�r Fehler- und Erfolgsmeldungen
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private HashMap<String, String> fillErrorMap(HashMap errorMap) {
		errorMap.put("DB_ERR", "Fehler beim Verbinden zur Datenbank");
		errorMap.put("DELETE_ERR", "Fehler beim L�schen");
		errorMap.put("INSERT_ERR", "Fehler beim Speichern in der Datenbank");
		errorMap.put("NO_ENTRY_ERR", "Kein DB-Eintrag vorhanden");
		errorMap.put("PET_SAVED", "Dein Kaufgesuch wurde gespeichert");
		errorMap.put("PET_UPDATED", "Dein Kaufgesuch wurde aktualisiert");
		errorMap.put("PET_DELETED", "Das Kaufgesuch wurde gel�scht");
		errorMap.put("UPDATE_ERR", "Fehler beim Aktualisieren");
		errorMap.put("USR_EXISTS", "Username oder Email schon vorhanden");
		errorMap.put("USR_SAVED", "Dein Account wurde erfolgreich angelegt");
		errorMap.put("USR_UPDATED", "Dein Profil wurde aktualisiert");
		errorMap.put("USR_DELETED", "Der Benutzer wurde erfolgreich gel�scht");
		errorMap.put("USR_HAS_PET_ERR", "Der Benutzer kann nicht gel�scht werden, weil ihm noch Kaufgesuche zugewiesen sind");

		return errorMap;
	}
	
	
	//Json ausgeben
	public String getJson(){
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json; 
	}
	

	
	//Getter und Setter
	public boolean getFlag() {
		return this.flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String flag) {
		this.message = message;
	}
}
