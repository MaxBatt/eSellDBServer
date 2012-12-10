package hdm.stuttgart.esell.errors;

import java.util.HashMap;

import com.google.gson.Gson;

public class ErrorHandler {

	private boolean flag;
	private String message;

	public ErrorHandler(boolean flag, String errorCode) {
		this.flag = flag;
		
		HashMap<String, String> errorMap = new HashMap<String, String>();
		errorMap = fillErrorMap(errorMap);
		
		if (errorMap.containsKey(errorCode)) {
			this.message = errorMap.get(errorCode).toString();
		} 
		else {
			this.message = "Fehler";
		}
	}


	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private HashMap<String, String> fillErrorMap(HashMap errorMap) {
		errorMap.put("INSERT_ERR", "Fehler beim Speichern in der Datenbank");
		errorMap.put("UPDATE_ERR", "Fehler beim Aktualisieren");
		errorMap.put("DELETE_ERR", "Fehler beim Lšschen");
		errorMap.put("USR_EXISTS", "Username oder Email schon vorhanden");
		errorMap.put("DB_ERR", "Fehler beim Verbinden zur Datenbank");
		errorMap.put("USR_SAVED", "Dein Account wurde erfolgreich angelegt");
		errorMap.put("USR_UPDATED", "Dein Profil wurde aktualisiert");
		errorMap.put("USR_DELETED", "Der Benutzer wurde erfolgreich gelšscht");
		errorMap.put("PET_SAVED", "Dein Kaufgesuch wurde gespeichert");
		errorMap.put("PET_UPDATED", "Dein Kaufgesuch wurde aktualisiert");
		errorMap.put("PET_DELETED", "Das Kaufgesuch wurde gelšscht");
		errorMap.put("NO_ENTRY_ERR", "Kein DB-Eintrag vorhanden");
		errorMap.put("USR_HAS_PET_ERR", "Der Benutzer kann nicht gelšscht werden, weil ihm noch Kaufgesuche zugewiesen sind");

		
		return errorMap;
	}
	
	
	//Json ausgeben
	public String getJson(){
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json; 
	}
	

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
